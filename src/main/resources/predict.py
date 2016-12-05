from sklearn.externals import joblib

model = joblib.load('model.pkl')

while True:
    player1_info = input()
    player2_info = input()

    atp1 = 1206
    atp2 = 3497

    probability = model.predict_proba([[atp1, atp2]])

    print(probability)

