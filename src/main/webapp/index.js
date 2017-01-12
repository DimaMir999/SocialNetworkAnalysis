window.onload = function () {
    if (localStorage.players === null || localStorage.players === undefined) {
        localStorage.players = '';
    }
    else {
        var str = localStorage.getItem('players');
        if (str !== '') {
            localeStoragePlayers = JSON.parse(str);
        }
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

var prediction = undefined;
var playersResponse = [];

function slideContentContainers() {
    $('#aboutContent').slideToggle();
    $('#instructionsContent').slideToggle();
}

function onInputChange() {
    $('#player1Prediction').text('');
    $('#player2Prediction').text('');
    prediction = undefined;
}

function getPlayers(startsWith) {
    var options = {
        url: '/api/players?startsWith=' + startsWith,
        type: 'GET',
        contentType: "application/x-www-form-urlencoded"
    };

    makeReq(100 + Math.random() * 10, options, function (result) {
        playersResponse = result;
    });
}

function makeReq(delay, options, callback) {
    $.ajax({
        url: options.url,
        type: options.type,
        data: options.data,
        async: false,
        contentType: options.contentType,
        error: function (xhr, status, err) {
            console.log(err.message);
            setTimeout(function () {
                var maxDelay = 15 * 60 * 1000, factor = 2, jitter = 0.1;
                delay = Math.min(delay * factor, maxDelay);
                delay = delay + Math.random() * delay * jitter;
                makeReq(delay, options, callback);
            }, delay);
        },
        success: function (result, status, xhr) {
            callback(result);
        }
    })
}

function makePrediction(player1, player2, options) {
    var reqData = {
        player1: player1.id,
        player2: player2.id,
        tournament: {
            court: "INDOOR",
            surface: options,
            name: "fantasy"
        }
    };

    var reqOptions = {
        url: '/api/prediction',
        data: JSON.stringify(reqData),
        type: 'POST',
        contentType: 'application/json'
    };

    makeReq(100 + Math.random() * 10, reqOptions, function (result) {
        prediction = Math.round(result.winFirstProbability * 100);
    });
}

function onPlayerInput(number) {
    if (number == 1) {
        player1 = undefined;
    }
    else player2 = undefined;
    var input = document.getElementById('inputPlayer' + number);
    getPlayers(input.value);
//var unicPlayers = [];
//players.forEach(function (item, index, arr) {
// if (localeStoragePlayers.indexOf(item) == -1) {
// unicPlayers.push(item);
// }
//});
//var selectPlayer = localeStoragePlayers.concat(unicPlayers);
    buildSelectPlayer(playersResponse, number);
}

function buildSelectPlayer(players, number) {
    $('#selectPlayer' + number).empty();
    var parent = document.getElementById('selectPlayer' + number);
    players.map(function (item, index) {
        var div = document.createElement('div');
        div.className = 'div-player';
        div.appendChild(document.createTextNode(item.name + " " + item.surname));
        div.addEventListener('click', function () {
            onDivPlayerClick(number, item)
        });
        parent.appendChild(div);
    })
}

function onDivPlayerClick(number, playerObj) {
    if (number == 1) {
        player1 = playerObj;
        $('#player1-error').fadeOut(200);
        $('#inputPlayer1').change();
    }
    else {
        player2 = playerObj;
        $('#player2-error').fadeOut(200);
        $('#inputPlayer2').change();
    }
    playersResponse = [];
    $('#inputPlayer' + number).val(playerObj.name + " " + playerObj.surname);
    if (!localeStoragePlayers.some(function (elem) {
            return elem.id === playerObj.id;
        })) {
        localeStoragePlayers.push(playerObj);
        localStorage.setItem('players', JSON.stringify(localeStoragePlayers));
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
    if (player1 === undefined) {
        $('#player1-error').css('display', 'block');
        return;
    }
    if (player2 === undefined) {
        $('#player2-error').css('display', 'block');
        return;
    }

    var typeOfCort = $('#typeOfCort').val();
    makePrediction(player1, player2, typeOfCort);
    console.log(prediction);
    setTimeout(function(){}, 1000);
    if (prediction === undefined) {
        return;
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