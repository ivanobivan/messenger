<html>
<head>
    <title>Chat</title>
    <link rel="stylesheet" href="../service/styles/workStyle.css"/>
    <link rel="stylesheet" href="../service/styles/helpStyles/w3.css"/>
    <link rel="stylesheet" href="../service/styles/helpStyles/font-awesome.min.css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
</head>
<body>
<div class="header col-12">
    <input id="username" placeholder="Username..." autofocus>
    <button id="connect">Connect</button>
    <button id="disconnect" disabled>Disconnect</button>
</div>
<div class="row">
    <div class="col-9">
        <article>
            <div id="dialog">
                <span>Chat to <span id="chatTo">All</span></span>
                <div id="message-board"></div>
                <hr>
                <textarea id="message" placeholder="message.."></textarea>
                <button id="send">Send</button>
            </div>
        </article>
    </div>
    <div class="col-3">
        <aside>
            <h5>Online User(s)</h5>
            <ul>
                <li id="all" class="hoverable">All</li>
            </ul>
            <ul id="userList"></ul>
        </aside>
    </div>
</div>
<script src="../service/js/helpScript/jquery-3.2.1.min.js"></script>
<script src="../service/js/workScript.js"></script>
</body>
</html>