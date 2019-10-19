import json
import time
import pandas as pd

from mlq.queue import MLQ

import numpy as np
from flask import Flask, request, jsonify, render_template
import pickle


app = Flask(__name__)
model = pickle.load(open('lm.pkl', 'rb'))

@app.route('/')
def home():
    return render_template('index.html')

@app.route('/predict', methods=['GET'])
def predict():
    paramaters = request.args.to_dict()
    row = pd.DataFrame(paramaters, index=[0])
    row = row.apply(pd.to_numeric)
    y_pred = model.predict(row)
    print(y_pred[0][0])
    response = 'Prediction is ' + str(y_pred[0][0])
    return response, 200

if __name__ == "__main__":
    app.run(debug=True)
