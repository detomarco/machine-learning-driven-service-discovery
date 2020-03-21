import fnmatch
import json
from time import time
from os import listdir
import os
import joblib
import numpy as np
import pandas as pd
import tensorflow as tf
from tensorflow.python.keras.backend import set_session

sess = None
graph = None

files = {}


def load_files(BASE_ROOT_FILE, prediction_strategy):
    global sess
    global graph
    sess = tf.Session()
    graph = tf.get_default_graph()

    print("loading file")
    dir_path = BASE_ROOT_FILE + '/' + prediction_strategy

    if os.path.exists(dir_path):
        start = time()
        # init files
        files[prediction_strategy] = {}

        # load model
        files_name = fnmatch.filter(listdir(dir_path), '*.sav')
        for file_name in files_name:
            set_session(sess)
            full_name = dir_path + '/' + file_name
            files[prediction_strategy, file_name] = joblib.load(full_name)

        # load input files
        files_name = fnmatch.filter(listdir(dir_path), '*.json')
        for file_name in files_name:
            full_name = dir_path + '/' + file_name
            files[prediction_strategy, file_name] = json.loads(open(full_name).read())

        end = time()
        print("Model loaded in " + str(end - start) + " ms for strategy " + prediction_strategy)
    print("file loaded")


def linear_regression_engine(service_type, values):
    values = values['serviceInstances']

    ## read files

    model = files['linear-regression', 'linear_regression_model.sav']
    scaler = files['linear-regression', 'linear_regression_scaler.sav']
    norm = files['linear-regression', 'linear_regression_norm.sav']

    df = pd.DataFrame(values)

    # Rename columns
    df.rename(columns={
        'name': 'Service Type',
        'url': 'Instance',
        'clientContext': 'Client Context',
        'serviceContext': 'Service Context',
        'timestamp': 'Timestamp',
        'meanResponseTime': 'Mean Response Time',
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
    df = df[['Day', 'Day of Week', 'Hour', 'Minute', 'Second', 'Client Context', 'Service Context', 'Instance',
             'Service Type', 'Mean Response Time']]

    # Normalize data
    df = df.replace(norm)

    # Scale data
    scaled_data = scaler.fit_transform(df.values)

    # predict values
    forecast = model.predict(scaled_data)

    # return output
    return forecast.tolist()


def time_series_engine(service_type, data):
    input_file_names = data['inputFileNames']

    ## read files
    model = files['time-series', service_type + '.model.sav']

    input = []
    for input_file_name in input_file_names:
        input.extend(files['time-series', input_file_name])

    data = [row['responseTime'] for row in input]

    # reshape values
    predict_array = np.array(data).reshape(1, 10, 3)

    # predict values
    global sess
    global graph
    with graph.as_default():
        set_session(sess)
        forecast = model.predict(predict_array)

    # return output
    return forecast.tolist()[0]
