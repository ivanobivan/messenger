'use strict';
var URL = "ws://" + document.location.host + document.location.pathname + "/test";
var webSocket = undefined;
var message;
var userName;
var sprite;

function connectClient() {
    webSocket = new WebSocket(URL);
    webSocket.onopen = onOpen;
    webSocket.onmessage = onMessage;
    webSocket.onclose = onClose;
    webSocket.onerror = onError;
}

function onOpen(evt) {
}

function onMessage(event) {
    //alert(event.data);
    var dataArray = event.data.split(".");
    var eventName = dataArray[0];
    userName = dataArray[1];

    //var customFunction;
    if (eventName === 'newClient') {
        newClient(userName);
    } else if (eventName === "message") {
        newMessage(userName, dataArray[2])
    }
    /*else if (eventName === 'removeUser') {
        customFunction = removeUser;
    }*/
    //customFunction.apply(null, data.split('.'));
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
    message = document.getElementById("message").value;
    message !== "" ? webSocket.send(message) : false;

    //var sender = usernameInputEl.value;
    //getMessage(sender, message, destination);
    //messageBoardEl.scrollTop = messageBoardEl.scrollHeight;
}

function newClient(userName) {
    sprite = getCustomSprite();
    var panel = document.createElement("div");
    panel.className = "w3-container customBorder w3-row w3-hover-light-gray";
    panel.innerHTML = "<div class=\"" + sprite + " w3-circle  w3-left w3-margin\"></div>\n" +
        "                    <p class=\"customMarginUserMes\"><span class=\"username\">" + userName + "</span></p>"
    document.getElementById("customUserPanel").appendChild(panel);
}

function newMessage(username, message) {
    var date = new Date();
    var options = {
        year: 'numeric',
        month: 'numeric',
        day: 'numeric',
        timezone: 'UTC',
        hour: 'numeric',
        minute: 'numeric',
        second: 'numeric'
    };
    var panel = document.createElement("div");
    panel.className = "w3-container w3-row";
    panel.innerHTML = "<div class=\"" + sprite + " w3-circle w3-left w3-margin\"></div>\n" +
        "        <p class=\"w3-padding-small\"><span id=\"customSpan1\">" + username +
        "</span><span class=\"w3-right\" id=\"customSpan2\">" + date.toLocaleString("ru", options)  + "</span><br><span\n" +
        "    id=\"customSpan3\">" + message + "</span></p>";
    document.getElementById("customMessageField").appendChild(panel);

}

function getCustomSprite() {
    var number = Math.floor(Math.random() * 9) + 1;
    return "customSprite" + number;
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









