<html>
<head>
    <title>Chat</title>
    <link rel="stylesheet" href="service/styles/workStyle.css"/>
    <link rel="stylesheet" href="service/styles/helpStyles/w3.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
</head>
<body>
<div class="w3-top w3-teal">
    <div class="w3-bar w3-left-align">
        <a href="#" class="w3-bar-item w3-button w3-padding-large w3-hover-blue"><i class="fa fa-bars"></i></a>
        <a href="#" class="w3-bar-item w3-button w3-padding-large w3-hover-blue"><i class="fa fa-globe"></i></a>
        <a href="#" class="w3-bar-item w3-button w3-padding-large w3-hover-blue"><i class="fa fa-user"></i></a>
        <a href="#" class="w3-bar-item w3-button w3-padding-large w3-hover-blue"><i class="fa fa-envelope"></i></a>
        <div class="w3-dropdown-hover">
            <a class="w3-button w3-padding-large"><i class="fa fa-bell"></i></a>
            <div class="w3-dropdown-content w3-card-4 w3-bar-block">
                <a href="#" class="w3-bar-item w3-button">Gjimbo want be your friend</a>
                <a href="#" class="w3-bar-item w3-button">Your birthday today</a>
                <a href="#" class="w3-bar-item w3-button">It is your day</a>
            </div>
        </div>
    </div>
</div>

<div class="w3-container w3-padding-64 w3-third">
    <h5>Online User(s)</h5>
    <p id="all" class="hoverable">All</p>
    <ul id="userList"></ul>
</div>

<div class="w3-container w3-third w3-padding-64">
    <div id="dialog">
        <span>Chat to <span id="chatTo">All</span></span>
        <div id="message-board"></div>
        <hr>
        <textarea id="message" placeholder="Message"></textarea>
        <button id="send">Send</button>
    </div>
</div>

<div class="w3-container w3-white w3-padding-64 w3-third">
    <input id="username" placeholder="Username" class="w3-input" autofocus>
    <button id="connect" class="w3-button">Connect</button>
    <button id="disconnect" class="w3-button" disabled>Disconnect</button>
</div>

<script src="service/js/helpScript/jquery-3.2.1.min.js"></script>
<%--<script src="service/js/workScript.js"></script>--%>
<script src="testScript.js"></script>
</body>
</html>