// создать подключение
var URL = "ws://" + document.location.host + document.location.pathname + "/test";
var websocket = new WebSocket(URL);

websocket.onerror = function(evt) { onError(evt) };

function onError(evt) {
    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
}
// For testing purposes
var output = document.getElementById("output");
websocket.onopen = function(evt) { onOpen(evt) };

function writeToScreen(message) {
    output.innerHTML += message + "<br>";
}

function onOpen() {
    writeToScreen("Connected to " + URL);
}
// End test functions
/*
// отправить сообщение из формы publish
document.forms.publish.onsubmit = function() {
    var outgoingMessage = this.message.value;
    socket.send(outgoingMessage);
    return false;
};

// обработчик входящих сообщений
socket.onmessage = function(event) {
    var incomingMessage = event.data;
    showMessage(incomingMessage);
};

// показать сообщение в div#subscribe
function showMessage(message) {
    var messageElem = document.createElement('div');
    messageElem.appendChild(document.createTextNode(message));
    document.getElementById('subscribe').appendChild(messageElem);
}*/
