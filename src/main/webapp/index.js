window.onload = function () {
    if (localStorage.players === null || localStorage.players === undefined) {
        localStorage.players = '';
    }
    else {
        var str = localStorage.getItem('players');
        localeStoragePlayers = str.split(',');
    }
    var inputPlayer1Pos = $('#inputPlayer1').offset();
    var inputPlayer2Pos = $('#inputPlayer2').offset();
    var selectPlayer1 = document.getElementById('selectPlayer1');
    var selectPlayer2 = document.getElementById('selectPlayer2');
    selectPlayer1.style.left = Math.round(inputPlayer1Pos.left) + 'px';
    selectPlayer1.style.top = Math.round(inputPlayer1Pos.top) + $('#inputPlayer1').outerHeight() + 'px';
    selectPlayer2.style.left = Math.round(inputPlayer2Pos.left) + 'px';
    selectPlayer2.style.top = Math.round(inputPlayer2Pos.top) + $('#inputPlayer2').outerHeight() + 'px';

    $('.button-fight').click(function () {
        onFightButtonClick();
    });

    $('#inputPlayer1').change(function () {
        onInputChange();
    });

    $('#inputPlayer2').change(function () {
        onInputChange();
    });

    $('#aboutTitle').click(function () {
        slideContentContainers();
    });

    $('#instructionsTitle').click(function () {
        slideContentContainers();
    });

    $('#aboutContent').slideToggle(600);
};

var localeStoragePlayers = [];
var player1 = undefined, player2 = undefined;

function slideContentContainers() {
    $('#aboutContent').slideToggle();
    $('#instructionsContent').slideToggle();
}

function onInputChange() {
    $('#player1Prediction').text('');
    $('#player2Prediction').text('');
}

function getPlayers(startsWith) {
    var options = {
        url: '',
        data: startsWith
    };
    var players;
    makePostReq(100 + Math.random() * 10, options, function (result) {
        players = result;
    });

    return ['Andy Murray', 'Novak', 'Milos Raonic']
}

function makePostReq(delay, options, callback) {
    $.ajax({
        url: options.url,
        type: 'POST',
        data: options.data,
        error: function (xhr, status, err) {
            console.log(err.message);
            setTimeout(function () {
                var maxDelay = 15 * 60 * 1000, factor = 2, jitter = 0.1;
                delay = Math.min(delay * factor, maxDelay);
                delay = delay + Math.random() * delay * jitter;
                makePostReq(delay, options);
            }, delay);
        },
        success: function (result, status, xhr) {
            callback(result);
        }
    })
}

function makePrediction(player1, player2, options) {
    var reqData = {
        player1: player1,
        player2: player2
    };
    if (options) {
        reqData.options = options;
    }

    var reqOptions = {
        url: '',
        data: reqData
    };

    var predictResult;
    makePostReq(100  + Math.random() * 10, reqOptions, function (result) {
        predictResult = result;
    });

    return 73;
}

function onPlayerInput(number) {
    if (number == 1) {
        player1 = undefined;
    }
    else player2 = undefined;
    var input = document.getElementById('inputPlayer' + number);
    var players = getPlayers(input.value);
    //var unicPlayers = [];
    //players.forEach(function (item, index, arr) {
    //    if (localeStoragePlayers.indexOf(item) == -1) {
    //        unicPlayers.push(item);
    //    }
    //});
    //var selectPlayer = localeStoragePlayers.concat(unicPlayers);
    buildSelectPlayer(players, number);
}

function buildSelectPlayer(players, number) {
    $('#selectPlayer' + number).empty();
    var parent = document.getElementById('selectPlayer' + number);
    players.map(function (item, index) {
        var div = document.createElement('div');
        div.className = 'div-player';
        div.appendChild(document.createTextNode(item));
        div.addEventListener('click', function () {
            onDivPlayerClick(number, item)
        });
        parent.appendChild(div);
    })
}

function onDivPlayerClick(number, item) {
    if (number == 1) {
        player1 = item;
        $('#player1-error').fadeOut(200);
        $('#inputPlayer1').change();
    }
    else {
        player2 = item;
        $('#player2-error').fadeOut(200);
        $('#inputPlayer2').change();
    }
    $('#inputPlayer' + number).val(item);
    if (!(localeStoragePlayers.indexOf(item) > -1)) {
        localStorage.setItem('players', localStorage.players + ',' + item);
        localeStoragePlayers.push(item);
    }
}

function onPlayerInputFocus(number) {
    buildSelectPlayer(localeStoragePlayers, number);
    $('#selectPlayer' + number).slideDown();
}

function onPlayerInputBlur(number) {
    $('#selectPlayer' + number).slideUp();
}

function onFightButtonClick() {
    console.log(player1, player2);
    if (player1 === undefined) {
        $('#player1-error').css('display', 'block');
        return;
    }
    if (player2 === undefined) {
        $('#player2-error').css('display', 'block');
        return;
    }

    var prediction;
    var typeOfCort = $('#typeOfCort').val();
    if (typeOfCort != 'none') {
        var options = {typeOfCort: typeOfCort};
        prediction = makePrediction(player1, player2, options);
    }
    else {
        prediction = makePrediction(player1, player2);
    }

    var player1Pred = $('#player1Prediction').addClass('player1-Prediction');
    var player2Pred = $('#player2Prediction').addClass('player2-Prediction');

    if (prediction < 50) {
        player1Pred.css('color', 'red');
    } else if (prediction > 50) {
        player2Pred.css('color', 'red');
    }
    player1Pred.text(prediction);
    player2Pred.text(100 - prediction);
}


