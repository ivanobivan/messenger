'use strict';
var URL = "ws://" + document.location.host + document.location.pathname + "/test";
var websocket = undefined;
var message;
var username;

function connectClient() {
    websocket = new WebSocket(URL);
    websocket.onopen = onOpen;
    websocket.onmessage = onMessage;
    websocket.onclose = onClose;
    websocket.onerror = onError;
}

function onOpen(evt) {
    alert("Connection successful");
}

function onMessage(event) {
    alert("Data has got : " + event.data);
    var eventName = event.data.substr(0, event.data.indexOf("|"));
    username = event.data.substr(event.data.indexOf("|") + 1);
    // var customFunction;
    // if (eventName === 'newUser') {
    //     customFunction = newUser;
    // } else if (eventName === 'removeUser') {
    //     customFunction = removeUser;
    // } else if (eventName === 'message') {
    //     customFunction = getMessage;
    // }
    // customFunction.apply(null, data.split('|'));
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
    alert("Your message" + " : " + message);
    message !== null ? websocket.send(message) : false;
    /*if (message === '') {
        return;
    }
    websocket.send(message);*/


    //var sender = usernameInputEl.value;
    //getMessage(sender, message, destination);
    //messageBoardEl.scrollTop = messageBoardEl.scrollHeight;
}

$(document).ready(function () {
    // After download document create connection
    connectClient();
    $("#send").click(function () {
        sendMessage();
    })
});
/*function newUser() {
    if (arguments.length === 1 && arguments[0] === "") {
        return;
    }
    var usernameList = arguments;
    //var documentFragment = document.createDocumentFragment();
    for (var i = 0; i < usernameList.length; i++) {
        var username = usernameList[i];
        alert("username : " + username + "(3)");
        //var liEl = document.createElement("li");
        //liEl.id = username;
        //liEl.textContent = username;
        // if (username !== usernameInputEl.value) {
        //     liEl.onclick = chatToFn(username);
        //     liEl.classList.add('hoverable');
        // }
        //documentFragment.appendChild(liEl);
    }
    //usernameListEl.appendChild(documentFragment);
}*/

/*function getMessage(sender, message) {

    if (destination === destination) {
        var newChatEl = createNewChat(sender, message);
        //messageBoardEl.appendChild(newChatEl);
    } else {
        //var toEl = usernameListEl.querySelector('#' + destination);
        //addCountMessage(toEl);
    }

    // if (chatRoom[destination]) chatRoom[destination].push(newChatEl);
    // else chatRoom[destination] = [newChatEl];
}*/
/*
function removeUser(removedUsername) {
    //usernameListEl.querySelector('#' + removedUsername).remove();
    alert(removedUsername);
}*/

/*function createNewChat(sender, message) {
    //var newChatDivEl = document.createElement('div');
    //var senderEl = document.createElement('span');
    //var messageEl = document.createElement('span');

    //if (sender === usernameInputEl.value) sender = 'me';

    //senderEl.textContent = sender;
    //messageEl.textContent = message;

    //newChatDivEl.appendChild(senderEl);
    //newChatDivEl.appendChild(messageEl);
    alert(sender + " : " + message);
    //return newChatDivEl;
}*/

/*function addCountMessage(toEl) {
    var countEl = toEl.querySelector('.count');
    if (countEl) {
        var count = countEl.textContent;
        count = +count;
        countEl.textContent = count + 1;
    } else {
        var countEl = document.createElement('span');
        countEl.classList.add('count');
        countEl.textContent = '1';
        toEl.appendChild(countEl);
    }
}*/




/*function chatToFn(username) {
    return function (e) {
        var countEl = usernameListEl.querySelector('#' + username + '>.count');
        if (countEl) countEl.remove();

        chatToLabelEl.textContent = username;
        //destination = username;
        messageBoardEl.innerHTML = '';

        var conversationList = chatRoom[destination];
        if (!conversationList) return;
        var df = document.createDocumentFragment();
        conversationList.forEach(function (conversation) {
            df.appendChild(conversation);
        });
        messageBoardEl.appendChild(df);
    }
}*/









