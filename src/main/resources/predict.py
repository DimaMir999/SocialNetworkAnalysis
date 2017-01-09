from sklearn.externals import joblib
import json

order = ['atp1', 'atp2']
model = joblib.load('model.pkl')

while True:
    match_json = input()
    match_info = json.loads(match_json)
    features = []
    for feature_name in order:
        features.append(match_info[feature_name])

    probability = model.predict_proba([features])

    prediction = json.dumps({
        'winFirstProbability': probability[0][1],
        'winSecondProbability': probability[0][0]
    })
    print(prediction)
