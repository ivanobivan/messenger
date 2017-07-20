'use strict';
var URL = "ws://" + document.location.host + document.location.pathname + "/test";
var webSocket = undefined;
var message;
var userName;
var sprite;
var options = {
    year: 'numeric',
    month: 'numeric',
    day: 'numeric',
    timezone: 'UTC',
    hour: 'numeric',
    minute: 'numeric',
    second: 'numeric'
};

function connectClient() {
    webSocket = new WebSocket(URL);
    webSocket.onopen = onOpen;
    webSocket.onmessage = onMessage;
    webSocket.onclose = onClose;
    webSocket.onerror = onError;
}

function onOpen(event) {

}

function onMessage(event) {
    var dataArray = event.data.split(".");
    var eventName = dataArray[0];
    userName = dataArray[1];

    //var customFunction;
    if (eventName === 'newClient') {
        newClient(userName);
    } else if (eventName === "message") {
        newMessage(userName, dataArray[2])
    } else if (eventName === 'removeUser') {
        removeUser(userName);
    }
}

function onClose(evt) {
    if (event.wasClean) {
        alert('Connection was close clean');
    } else {
        alert('Connection failed');
    }
    alert('Key: ' + event.code + ' Reason: ' + event.reason);
}

function onError(evt) {
    alert("Error" + evt.message);
}

function sendMessage() {
    message  = document.getElementById("message").value;
    document.getElementById("message").value = "";
    message !== "" ? webSocket.send(message) : false;
}

function newClient(userName) {
    sprite = getCustomSprite();
    var panel = document.createElement("div");
    panel.className = "w3-container customBorder w3-row w3-hover-light-gray";
    panel.id = userName;
    panel.innerHTML = "<div class=\"" + sprite + " w3-circle  w3-left w3-margin\"></div>\n" +
        "                    <p class=\"customMarginUserMes\"><span class=\"username\">" + userName + "</span></p>";
    document.getElementById("customUserPanel").appendChild(panel);
}

function newMessage(username, message) {
    var date = new Date();
    var panel = document.createElement("div");
    panel.className = "w3-container w3-row";
    panel.innerHTML = "<div class=\"" + sprite + " w3-circle w3-left w3-margin\"></div>\n" +
        "        <p class=\"w3-padding-small\"><span id=\"customSpan1\">" + username +
        "</span><span class=\"w3-right\" id=\"customSpan2\">" + date.toLocaleString("ru", options)  + "</span><br><span\n" +
        "    id=\"customSpan3\">" + message + "</span></p>";
    document.getElementById("customMessageField").appendChild(panel);

}

function removeUser(userName) {
    document.getElementById("customUserPanel").removeChild(document.getElementById(userName));
}

function getCustomSprite() {
    var number = Math.floor(Math.random() * 9) + 1;
    return "customSprite" + number;
}

function key(event) {return ('which' in event) ? event.which : event.keyCode;}

$(document).ready(function () {
    // After download document create connection
    connectClient();
    $("#send").click(function () {
        sendMessage();
    })
});









