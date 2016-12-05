from sklearn.externals import joblib
import json

order = ['atp1', 'atp2']
model = joblib.load('model.pkl')

while True:
    match_json = input()
    match_info = json.load(match_json)
    features = []
    for feature_name in order:
        features.append(match_info[feature_name])

    probability = model.predict_proba([features])

    print(probability[0])

