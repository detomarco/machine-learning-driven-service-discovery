import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from sklearn.preprocessing import MinMaxScaler
import joblib
from sys import argv
import json
import os

input_file_path = argv[1]

file = open(input_file_path).read()
data = json.loads(file)

df = pd.DataFrame(data)

# Rename columns
df.rename(columns={
    'id': 'ID',
    'clientContext': 'Client Context',
    'serviceContext': 'Service Context',
    'serviceType': 'Service Type',
    'serviceInstance': 'Instance',
    'timestamp': 'Timestamp',
    'responseTime': 'Response Time',
    'meanResponseTime': 'Mean Response Time',
    'status': 'Status'
}, inplace=True)

# Split Timestamp
df['Timestamp'] = pd.to_datetime(df['Timestamp'], unit='ms')
df['Month'] = df['Timestamp'].dt.month
df['Day'] = df['Timestamp'].dt.day
df['Hour'] = df['Timestamp'].dt.hour
df['Minute'] = df['Timestamp'].dt.minute
df['Second'] = df['Timestamp'].dt.second
df['Day of Week'] = df['Timestamp'].dt.dayofweek

# Sort column
df = df[
    ['Day', 'Day of Week', 'Hour', 'Minute', 'Second', 'Client Context', 'Service Context', 'Instance', 'Service Type',
     'Response Time', 'Mean Response Time']]

# normalize columns
norm_dict = {}
fields = ['Instance', 'Service Type', 'Client Context', 'Service Context']
for field in fields:
    field_values = df[field].unique()
    field_values = dict(zip(field_values, range(len(field_values))))
    df = df.replace({field: field_values})
    norm_dict[field] = field_values
# scale number values from 0 to 1
cols_to_norm = ['Day', 'Day of Week', 'Hour', 'Minute', 'Second', 'Service Type', 'Instance', 'Client Context',
                'Service Context', 'Response Time', 'Mean Response Time']
scaler = MinMaxScaler(feature_range=(0, 1))
df[cols_to_norm] = scaler.fit_transform(df[cols_to_norm].values)

#############################
## MACHINE LEARNING
#############################

# define attributes
X = df[
    ['Day', 'Day of Week', 'Hour', 'Minute', 'Second', 'Instance', 'Service Type', 'Client Context', 'Service Context',
     'Mean Response Time']].values
# X = df[['Hour']].values
# define labels
y = df['Response Time'].values

# create test and training set
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=0)

model = LinearRegression()
model.fit(X_train, y_train)

############################
## SAVE MODEL
############################

dir = os.path.join("temp")
if not os.path.exists(dir):
    os.mkdir(dir)

model_output = 'temp/linear_regression_model.sav'
joblib.dump(model, model_output)

scaler_output = 'temp/linear_regression_scaler.sav'
joblib.dump(scaler, scaler_output)

norm_output = 'temp/linear_regression_norm.sav'
joblib.dump(norm_dict, norm_output)

print(json.dumps([model_output, scaler_output, norm_output]))
