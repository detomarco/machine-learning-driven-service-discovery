#!flask/bin/python
from time import time

from flask import Flask
from flask import json
from flask import request
from pyfolder import PyFolder
from pyzip import PyZip

from prediction import time_series_engine, linear_regression_engine, load_files

app = Flask(__name__)

BASE_ROOT_FILE = "resources"

LINEAR_REGRESSION_PREDICTION = 'linear-regression'
TIME_SERIES_PREDICTION = 'time-series'

prediction_engines = [LINEAR_REGRESSION_PREDICTION, TIME_SERIES_PREDICTION]


@app.route('/model/<prediction_strategy>', methods=['POST'])
def model(prediction_strategy):
    data = request.get_data()
    PyZip(PyFolder(BASE_ROOT_FILE + '/' + prediction_strategy, interpret=False, allow_override=True,
                   allow_remove_folders_with_content=True, auto_create_folder=True)) \
        .from_bytes(data)
    load_files(BASE_ROOT_FILE, prediction_strategy)

    return '', 204


@app.route('/predict/<prediction_strategy>/<service_type>/<context>', methods=['POST'])
def predict(prediction_strategy, service_type, context):
    start_time = time()
    data = request.get_json()

    if prediction_strategy == LINEAR_REGRESSION_PREDICTION:
        engine = linear_regression_engine
    elif prediction_strategy == TIME_SERIES_PREDICTION:
        engine = time_series_engine
    else:
        raise NameError('prediction strategy not found')

    output = engine(service_type, data)

    response = app.response_class(
        response=json.dumps({'values': output}),
        status=200,
        mimetype='application/json'
    )
    end_time = time()
    print("End prediction: " + str(end_time - start_time))
    print("Output" + str(output))
    return response


if __name__ == '__main__':
    for engine in prediction_engines:
        load_files(BASE_ROOT_FILE, engine)
    app.run(debug=True, port=8764)
