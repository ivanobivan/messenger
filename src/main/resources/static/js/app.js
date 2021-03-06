//TODO Create  modal structure with simple display users panel and users message
let webSocket = undefined;
let stompClient = null;
let sprite;
let greyColor = false;
const options = {
    year: 'numeric',
    month: 'numeric',
    day: 'numeric',
    timezone: 'UTC',
    hour: 'numeric',
    minute: 'numeric',
    second: 'numeric'
};
function logOutFromApp() {
    let token = getCookie('XSRF-TOKEN');
    $.ajax({
        url: "/logout",
        method: "POST",
        beforeSend: function(request) {
            request.setRequestHeader("X-XSRF-TOKEN", token);
        },
        success: function () {
            window.location.href = window.host + window.port
        }
    });
}
function getCookie(name) {
    var matches = document.cookie.match(new RegExp(
        "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
    ));
    return matches ? decodeURIComponent(matches[1]) : undefined;
}
function connect() {
    //TODO need overview Socket.IO for this workflow, because app need auto-reconnection
    webSocket = new SockJS('/ws');
    stompClient = Stomp.over(webSocket);
    stompClient.connect({}, function (frame) {
        newClient(frame.headers['user-name']);
        stompClient.subscribe('/topic/communion', function (chatMessage) {
            newMessage(JSON.parse(chatMessage.body).sender,JSON.parse(chatMessage.body).message);
        });
        stompClient.subscribe('app/chat.persons',function (persons) {
            console.log(persons);
        })
    });
}

function sendMessage() {
    var message = document.getElementById("message").textContent;
    if (message.length > 0 && message !== "\n" && message.trim() !== "") {
        stompClient.send("/app/chat.message",{},JSON.stringify({'message' : message,"recipient" : "All"}));
    }
    document.getElementById("message").textContent = "";
}

function newClient(userName) {
    var panel = document.createElement("div");
    panel.className = "w3-container customBorder w3-hover-light-gray w3-border-bottom";
    panel.id = userName;
    sprite = getCustomSprite();
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
    connect();
    $("#send").click(function () {
        sendMessage();
    });
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









