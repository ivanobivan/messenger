'use strict';
var webSocket = undefined;
var userName;
var sprite = getCustomSprite();
var greyColor = false;
var options = {
    year: 'numeric',
    month: 'numeric',
    day: 'numeric',
    timezone: 'UTC',
    hour: 'numeric',
    minute: 'numeric',
    second: 'numeric'
};

function connectClient(URL) {
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
    if (eventName === 'newClient') {
        newClient(userName);
    } else if (eventName === "message") {
        dataArray.length === 4 ? newMessage(userName, dataArray[2], dataArray[3]) : newMessage(userName, dataArray[2], null);
    } else if (eventName === 'removeUser') {
        removeUser(userName);
    }
}

function onClose(event) {
    var response;
    var panel = document.createElement("div");
    panel.className = "w3-display-middle w3-pink w3-border";

    if (event.wasClean) {
        response = 'Connection was close clean';
    } else {
        response = 'Connection failed';
    }
    panel.innerHTML = response + "\n" + "Key: " + event.code + " Reason: " + event.reason;
    document.body.appendChild(panel);
}

function onError(event) {
    var panel = document.createElement("div");
    panel.className = "w3-display-bottomleft w3-pink w3-border";
    panel.innerHTML = "Error: " + event.message;
    document.body.appendChild(panel);
}

function sendMessage() {
    var message = document.getElementById("message").textContent;
    if (message.length > 0 && message !== "\n" && message !== "") {
        webSocket.send(message);
    }
    document.getElementById("message").textContent = "";
}

function newClient(userName) {
    var panel = document.createElement("div");
    panel.className = "w3-container customBorder w3-row w3-hover-light-gray w3-border-bottom";
    panel.id = userName;
    panel.innerHTML = "<div class=\"" + sprite + " w3-circle  w3-left\"></div>\n" +
        "                    <p class=\"customMarginUserMes\"><span class=\"username\">" + userName + "</span>" +
        "                    <i class=\"fa fa-volume-up w3-right fa-lg customMarginIcons\" title=\"Mute\"></i>\n" +
        "                    <i class=\"fa fa-address-card w3-right fa-lg customMarginIcons\" title=\"Info about user\"></i>\n" +
        "                    <i class=\"fa fa-comments-o w3-right fa-lg customMarginIcons\" title=\"Create private chat\"></i></p>";
    document.getElementById("customUserPanel").appendChild(panel);
}

function newMessage(username, message, color) {
    var ownerColor = color !== null ? "customSpanOwner" : "customSpan1";
    var date = new Date();
    var messageField = document.getElementById("customMessageField");
    var panel = document.createElement("div");
    if (greyColor === false) {
        panel.className = "w3-container";
        greyColor = true;
    } else {
        panel.className = "w3-container w3-light-gray";
        greyColor = false;
    }
    panel.innerHTML = "<div class=\"" + sprite + " w3-circle w3-left\"></div>\n" +
        "                                    <div class=\"w3-padding-small customMarginSpan\"><span class=\"" + ownerColor + "\">" + username + "</span><span\n" +
        "                                            class=\"w3-right customSpan2\">" + date.toLocaleString("ru", options) + "</span></div><div\n" +
        "                                            class=\"w3-margin-left customSpan3\">" + message + "</div>";
    messageField.appendChild(panel);
    messageField.scrollTop = messageField.scrollHeight;

}

function removeUser(userName) {
    document.getElementById("customUserPanel").removeChild(document.getElementById(userName));
}

function getCustomSprite() {
    var number = Math.floor(Math.random() * 9) + 1;
    return "customSprite" + number;
}

function prevSendMes(e) {
    e = e || window.event;
    if (e.keyCode === 13 && e.ctrlKey) {
        sendMessage();
    }
}


$(document).ready(function () {
    var bool = true;

    $("#annoyButtonConnect").click(function () {
        connectClient("ws://" + document.location.host + document.location.pathname + "/test?username=" + document.getElementById("annoyInput").value);
        $("#annoyButtonDisconnect").show();
        $("#annoyButtonConnect").hide();
        $("#annoyInput").hide();
    });
    $("#annoyButtonDisconnect").click(function () {

    });
    $("#send").click(function () {
        sendMessage();
    })
        .after(this.focus());

    $("#buttonDataUser").click(function () {
        $('#dataUserPanel').toggle();
        $("#messagePanel").toggleClass("m8")
            .toggleClass("m6");
    })
        .hover(function () {
            $(this).find("> i").toggleClass("fa-spin");
        });

    $("#btn1").click(function () {
        $("#input1").prop("disabled", false);
    });

    $("#settingsUser").click(function () {
        if (bool) {
            $("#rootPlus").css("transform", "rotate(90deg)")
                .css("transition", "0.2s");
            bool = false;
        } else {
            $("#rootPlus").css("transform", "rotate(-90deg)")
                .css("transition", "0.2s");
            bool = true;
        }

        $("#settingsPanel").animate({height: 'toggle'}, 200);
    });
    $("#clearButton").click(function () {
        $("#customMessageField").empty();
    })
});









