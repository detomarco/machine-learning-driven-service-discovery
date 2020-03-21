import pandas as pd
from sklearn.preprocessing import MinMaxScaler
from keras.models import Sequential
from keras.layers import Dense
from keras.layers import LSTM
from sys import argv
import json
import joblib


# convert series to supervised learning
def series_to_supervised(data, n_in=1, n_out=1, dropnan=True):
    n_vars = 1 if type(data) is list else data.shape[1]
    dfi = pd.DataFrame(data)
    cols, names = list(), list()
    # input sequence (t-n, ... t-1)
    for i in range(n_in, 0, -1):
        cols.append(dfi.shift(i))
        names += [('var%d(t-%d)' % (j + 1, i)) for j in range(n_vars)]
    # forecast sequence (t, t+1, ... t+n)
    for i in range(0, n_out):
        cols.append(dfi.shift(-i))
        if i == 0:
            names += [('var%d(t)' % (j + 1)) for j in range(n_vars)]
        else:
            names += [('var%d(t+%d)' % (j + 1, i)) for j in range(n_vars)]
    # put it all together
    agg = pd.concat(cols, axis=1)
    agg.columns = names
    # drop rows with NaN values
    if dropnan:
        agg.dropna(inplace=True)
    return agg


input_file_path = argv[1]

file = open(input_file_path).read()
data = json.loads(file)
df = pd.DataFrame(data)

# Rename columns
df.rename(columns={
    'serviceType': 'Service Type',
    'serviceInstance': 'Instance',
    'responseTime': 'Response Time',
    'timestamp': 'Timestamp'
}, inplace=True)

# Remove useless column
df['Timestamp'] = pd.to_datetime(df['Timestamp'], unit='ms')
df.set_index('Timestamp', inplace=True)
df = df[['Service Type', 'Instance', 'Response Time']]

# group dataframe by service type and context
dfs = {service_name: df_service for service_name, df_service in df.groupby(['Service Type'])}

file_names = []
# for each service and context
for key in dfs:
    # retrieve sub dataset
    sub_df = dfs[key]

    # retrieve number instances
    n_instances = sub_df["Instance"].nunique()
    # convert dataset in a compliant time series format
    grouper = sub_df.groupby([pd.Grouper(freq='1T'), 'Instance'])
    sub_df_ts = grouper.mean().unstack().fillna(0)

    ## DEFINE VARIABLES
    num_forecasts = 1
    # specify the number of lag hours
    prev_steps = 10

    values = sub_df_ts.values
    # normalize features
    scaler = MinMaxScaler(feature_range=(0, 1))
    scaled = scaler.fit_transform(values)
    # frame as supervised learning
    reframed = series_to_supervised(scaled, prev_steps, num_forecasts)
    values = reframed.values
    # split into train and test sets
    n_train_hours = int(len(values) * 0.7)
    train = values[:n_train_hours, :]
    test = values[n_train_hours:, :]
    # split into input and outputs
    n_obs = prev_steps * n_instances
    train_X, train_y = train[:, :n_obs], train[:, n_obs:]
    test_X, test_y = test[:, :n_obs], test[:, n_obs:]
    # reshape input to be 3D [samples, timesteps, features]
    train_X = train_X.reshape((train_X.shape[0], prev_steps, n_instances))
    test_X = test_X.reshape((test_X.shape[0], prev_steps, n_instances))
    # design network
    model = Sequential()
    model.add(LSTM(110, input_shape=(train_X.shape[1], train_X.shape[2]), return_sequences=True))
    model.add(LSTM(units=55))
    model.add(Dense(n_instances * num_forecasts))
    model.compile(loss='mean_squared_error', optimizer='adam')
    # fit network
    model.fit(train_X, train_y, epochs=50, batch_size=72, validation_data=(test_X, test_y), verbose=0, shuffle=False)

    # save model and scaler
    model_file_name = key + '.model.sav'
    joblib.dump(model, model_file_name)
    file_names.append(model_file_name)

print(json.dumps(file_names))
