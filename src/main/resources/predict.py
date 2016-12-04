from sklearn.externals import joblib
import numpy as np

model = joblib.load('model.pkl')

while True:
    player1 = input()
    player2 = input()

    atp1 = 1206
    atp2 = 3497

    probability = model.predict_proba(np.array([atp1, atp2]).reshape((1, -1)))

    print(probability)

