import requests

URL_BASE = 'http://localhost:8080/api/numismatic'
URL_AUTH = URL_BASE + '/oauth/token'
URL_NEARBY = URL_BASE + '/v1/users/nearby?radius=50'
URL_COUNTRY = URL_BASE + '/v1/countries'
URL_COINS = URL_BASE + '/v1/coins'

## AUTHENTICATION
credentials = 'username=admin&password=admin&grant_type=password'
headers_auth = {
    'Authorization': 'Basic bnVtaXNtYXRpYy1jbGllbnQ6bnVtaXNtYXRpYy1zZWNyZXQ=',
    'Content-type': 'application/x-www-form-urlencoded;charset=utf-8'
}


while True:
    try:
        # AUTHENTICATION
        auth_res = requests.post(url=URL_AUTH, data=credentials, headers=headers_auth)
        print(auth_res.content)

        access_token = auth_res.json()["access_token"]
        print(access_token)

        headers = {
            'Authorization': 'Bearer ' + access_token,
            'Content-type': 'application/json'
        }

        nearby_users = requests.get(url=URL_NEARBY, headers=headers).json()["users"]

        for nearby_user in nearby_users:
            username = nearby_user["username"]
            print( username)
            countries = requests.get(url=f'{URL_COUNTRY}/{username}', headers=headers).json()["countries"]
            for country in countries:
                print("   " +country)
                coins = requests.get(url=f'{URL_COINS}?country={country}&username={username}', headers=headers).json()["coins"]
                for coin in coins:
                    print("      " + str(coin))
    except Exception as e:
        print(e)
