var usernameListEl = document.querySelector("#userList");
var messageBoardEl = articleEl.querySelector('#message-board');
var messageInputEl = articleEl.querySelector('#message');
var sendBtnEl = articleEl.querySelector('#send');
var chatToLabelEl = articleEl.querySelector('#destination');
var chatToAllEl = document.querySelector('#all');
var destination = 'all';
var chatRoom = {'all': []};
var socket = undefined;

connectBtnEl.onclick = connect;
disconnectBtnEl.onclick = disconnect;

function connect() {
    socket = new WebSocket("ws://"+ location.host + location.pathname +"chat?username=" + usernameInputEl.value);
    socket.onopen = socketOnOpen;
    socket.onmessage = socketOnMessage;
    socket.onclose = socketOnClose;
    window.onclose = socketOnClose;
}

function disconnect() {
    socket.close();
    socket = undefined;
}

function socketOnOpen(e) {
    usernameInputEl.disabled = true;
    connectBtnEl.disabled = true;
    disconnectBtnEl.disabled = false;
}

function socketOnMessage(e) {
    var eventName = e.data.substr(0, e.data.indexOf("|"));
    var data = e.data.substr(e.data.indexOf("|") + 1);
    var fn;
    if(eventName == 'newUser') fn = newUser;
    else if(eventName == 'removeUser') fn = removeUser;
    else if(eventName == 'message') fn = getMessage;

    fn.apply(null, data.split('|'));
}

function socketOnClose(e) {
    usernameInputEl.disabled = false;
    connectBtnEl.disabled = false;
    disconnectBtnEl.disabled = true;
    usernameInputEl.value = '';
    messageBoardEl.innerHTML = '';
    chatToLabelEl.innerHTML = 'All';
    usernameListEl.innerHTML = '';
}

function newUser() {
    if(arguments.length == 1 && arguments[0] == "") return;
    var usernameList = arguments;
    var documentFragment = document.createDocumentFragment();
    for(var i = 0; i < usernameList.length; i++) {
        var username = usernameList[i];
        var liEl = document.createElement("li");
        liEl.id = username;
        liEl.textContent = username;
        if(username != usernameInputEl.value) {
            liEl.onclick = chatToFn(username);
            liEl.classList.add('hoverable');
        }
        documentFragment.appendChild(liEl);
    }
    usernameListEl.appendChild(documentFragment);
}

function getMessage(sender, message, destination) {
    destination = destination || sender;
    if(destination == destination) {
        var newChatEl = createNewChat(sender, message);
        messageBoardEl.appendChild(newChatEl);
    } else {
        var toEl = usernameListEl.querySelector('#' + destination);
        addCountMessage(toEl);
    }

    if(chatRoom[destination]) chatRoom[destination].push(newChatEl);
    else chatRoom[destination] = [newChatEl];
}

function removeUser(removedUsername) {
    usernameListEl.querySelector('#' + removedUsername).remove();
}
function createNewChat(sender, message) {
    var newChatDivEl = document.createElement('div');
    var senderEl = document.createElement('span');
    var messageEl = document.createElement('span');

    if(sender == usernameInputEl.value) sender = 'me';

    senderEl.textContent = sender;
    messageEl.textContent = message;

    newChatDivEl.appendChild(senderEl);
    newChatDivEl.appendChild(messageEl);
    return newChatDivEl;
}

function addCountMessage(toEl) {
    var countEl = toEl.querySelector('.count');
    if(countEl) {
        var count = countEl.textContent;
        count = +count;
        countEl.textContent = count + 1;
    } else {
        var countEl = document.createElement('span');
        countEl.classList.add('count');
        countEl.textContent = '1';
        toEl.appendChild(countEl);
    }
}

sendBtnEl.onclick = sendMessage;
chatToAllEl.onclick = chatToFn('all');

function sendMessage() {
    var message = messageInputEl.value;
    if(message == '') return;
    socket.send(destination + '|' + message );
    messageInputEl.value = '';


    var sender = usernameInputEl.value;
    getMessage(sender, message, destination);
    messageBoardEl.scrollTop = messageBoardEl.scrollHeight;
}

function chatToFn(username) {
    return function(e) {
        var countEl = usernameListEl.querySelector('#' + username + '>.count');
        if(countEl) countEl.remove();

        chatToLabelEl.textContent = username;
        destination = username;
        messageBoardEl.innerHTML = '';

        var conversationList = chatRoom[destination];
        if(!conversationList) return;
        var df = document.createDocumentFragment();
        conversationList.forEach(function (conversation) {
            df.appendChild(conversation);
        });
        messageBoardEl.appendChild(df);
    }

}