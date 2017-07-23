<html>
<head>
    <title>Chat</title>
    <link rel="stylesheet" href="service/styles/applicationStyle.css"/>
    <link rel="stylesheet" href="service/styles/help/w3.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <%@ page extends="ru.messenger.servlets.SecureJSP" %>
</head>
<body>
<div class="w3-top">
    <div class="w3-bar w3-left-align w3-blue-gray">
        <a id="buttonDataUser" class="w3-bar-item w3-button w3-padding-large"><i
                class="fa fa-cog"></i></a>
        <a class="w3-bar-item w3-button w3-padding-large "><i class="fa fa-vk"></i></a>
        <a class="w3-bar-item w3-button w3-padding-large "><i class="fa fa-twitter"></i></a>
        <a class="w3-bar-item w3-button w3-padding-large "><i class="fa fa-facebook"></i></a>
        <a class="w3-bar-item w3-button w3-padding-large "><i class="fa fa-phone"></i></a>
        <a class="w3-bar-item w3-button w3-padding-large "><i class="fa fa-skype"></i></a>
        <div class="w3-dropdown-hover">
            <a class="w3-button w3-padding-large "><i class="fa fa-bell"></i></a>
            <div class="w3-dropdown-content w3-card-4 w3-bar-block">
                <a class="w3-bar-item w3-button">Dima want to be your friend</a>
                <a class="w3-bar-item w3-button">Your birthday today</a>
                <a class="w3-bar-item w3-button">It is your day</a>
            </div>
        </div>
        <div class="w3-dropdown-click w3-right">
            <button class="w3-button w3-padding-large" id = "settingsUser"><i class="fa fa-plus" id = "rootPlus"></i></button>
            <div class="w3-dropdown-content w3-card-4 w3-bar-block" id = "settingsPanel" style="right:0">
                <button class="w3-bar-item w3-button w3-border-bottom" id="clearButton">&#9760; Clear this dialog</button>
                <a class="w3-bar-item w3-button">&#9741; Create new chat room</a>
            </div>
        </div>
        <div class="w3-bar-item w3-right customFind">
            <form action="#" method="get" class="customSearch">
                <input type="search" name="" class="w3-input" id="customInput" placeholder="Search...">
                <input type="submit" name="" value="" id="customSubmit">
            </form>
        </div>

    </div>
</div>
<div class="w3-content" style="max-width: 2000px;">
    <div class="w3-row">

        <div class="w3-col m2 w3-padding-48" id="dataUserPanel">
            <div class="w3-card-2 w3-round w3-white">
                <div class="w3-container">
                    <h4 class="w3-center">Ivan</h4>
                    <div class="w3-round-xlarge" id="avatar" style="height:106px;width:106px"></div>
                    <hr>
                    <form action="#" method="post">
                        <div class="w3-margin-top">
                            <input type="text" class="customMargin" id="input1" value="Nickname" style="width: 80%"
                                   disabled>
                            <i class="fa fa-pencil-square-o  w3-right w3-margin-right cursorHand" id="btn1"
                               title="Edit"></i>
                        </div>
                        <div class="w3-margin-top">
                            <input type="text" class="customMargin " value="Name" style="width: 80%" disabled>
                            <i class="fa fa-pencil-square-o  w3-right w3-margin-right cursorHand"
                               title="Edit"></i>
                        </div>
                        <div class="w3-margin-top">
                            <input type="text" class="customMargin" value="Surname" style="width: 80%" disabled>
                            <i class="fa fa-pencil-square-o  w3-right w3-margin-right cursorHand"
                               title="Edit"></i>
                        </div>
                        <div class="w3-margin-top">
                            <input type="text" class="customMargin" value="Mobile number" style="width: 80%" disabled>
                            <i class="fa fa-pencil-square-o  w3-right w3-margin-right cursorHand"
                               title="Edit"></i>
                        </div>
                        <div class="w3-margin-top">
                            <input type="text" class="customMargin" value="E-mail" style="width: 80%" disabled>
                            <i class="fa fa-pencil-square-o  w3-right w3-margin-right cursorHand"
                               title="Edit"></i>
                        </div>
                        <input type="file" name="file" id="changeAvatar" hidden>
                        <input type="submit" value="Save"
                               class="w3-button w3-teal w3-round w3-margin w3-right">
                    </form>
                </div>
            </div>
        </div>

        <div class="w3-col m8 w3-padding-48" id="messagePanel">
            <div class="w3-row-padding">
                <div class="w3-col m12">
                    <div class="w3-card-2 w3-round w3-white">
                        <div class="w3-container w3-padding">
                            <div class="w3-row w3-left-align">
                                <a class="w3-col m3  w3-light-gray w3-padding-small customBorderRooms"><i
                                        class="fa fa-users"></i> Common Room <span class="w3-right">&#9747;</span></a>
                                <a class="w3-col m3  w3-light-gray w3-padding-small customBorderRooms"><i
                                        class="fa fa-user"></i> Private Room</a>
                                <a class="w3-col m3  w3-light-gray w3-padding-small customBorderRooms"><i
                                        class="fa fa-user"></i> Private Room</a>
                                <a class="w3-col m3  w3-light-gray w3-padding-small customBorderRooms w3-border-right"><i
                                        class="fa fa-user"></i> Private Room</a>
                            </div>
                            <div class="w3-border" id="customMessageField">
                                <%--<div class="w3-container">
                                    <div class="customSprite1 w3-circle w3-left"></div>
                                    <div class="w3-padding-small customMarginSpan"><span class="customSpan1">Username</span><span
                                            class="w3-right customSpan2">21.01.2016</span></div><div
                                        class="w3-margin-left customSpan3">text</div>
                                </div>--%>
                            </div>
                            <br>
                            <div class="w3-row">
                                <button
                                        class="w3-btn w3-black w3-right w3-round w3-col m1 w3-margin-right" id="send">
                                    SEND
                                </button>
                                <div type="text" id="message" contenteditable="true"
                                     class="w3-input w3-border w3-left w3-col m10" title="Message"
                                     onkeyup="prevSendMes()">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="w3-col m4" id="customUserPanel">
            <p class="w3-center w3-border-bottom">Contact list</p>
            <%--<div class="w3-container customBorder w3-border-bottom w3-hover-gray">
                <div class="customSprite1 w3-circle w3-left"></div>
                <p class="customMarginUserMes"><span class="username">Sara</span>
                    <i class="fa fa-volume-up w3-right fa-lg" title="Mute"></i>
                    <i class="fa fa-address-card w3-right fa-lg" title="Info about user"></i>
                    <i class="fa fa-comments-o w3-right fa-lg" title="Create private chat"></i></p>
            </div>--%>
        </div>
    </div>
</div>

<script src="service/js/help/jquery-3.2.1.min.js"></script>
<script src="service/js/applicationScript.js"></script>
<script src="service/js/applicationStyleScript.js"></script>
</body>
</html>