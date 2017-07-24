<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="service/styles/authorisationStyle.css"/>
    <link rel="stylesheet" href="service/styles/help/w3.css"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Authorisation</title>
</head>
<body onload="checkLogin(<%request.getParameter("response");%>)">
<p class="w3-display-topmiddle w3-pink w3-xxlarge" id="isLogin">Invalid password</p>
<div class="w3-row invis">
    <div class="w3-col w3-container w3-quarter"></div>
    <div class="w3-quarter w3-center w3-animate-opacity">
        <button id="button1"><span>Sign In</span></button>
            <br><br>
            <form action="MainServlet" method="post">
                <div class="invisForm" id="form1">
                    <input type="hidden" name="isLogin" value="true">
                    <input type="text" name="username" placeholder="Username" class="w3-input" id="input1"><br>
                    <input type="password" name="password" placeholder="Password" class="w3-input"><br>
                    <input type="submit" value="OK" class="w3-btn w3-round customButton"><br>
                    <p>If you don't own account, then create it</p>
                    <div class="w3-btn w3-border w3-round" id="buttonReg"><span>Log in</span></div>
                </div>
            </form>
        <form action="MainServlet" class="invisForm" id="formReg" method="post">
            <p>Enter information for authorisation</p>
            <input type="hidden" name="isLogin" value="false">
            <input type="text" placeholder="NIckname" class="w3-input" id="input3"><br>
            <input type="text" placeholder="Username" class="w3-input"><br>
            <input type="password" placeholder="Password" class="w3-input"><br>
            <i class="fa fa-vk fa-2x"></i>
            <i class="fa fa-twitter fa-2x"></i>
            <i class="fa fa-facebook fa-2x"></i>
            <i class="fa fa-google fa-2x"></i><br>
            <input type="submit" value="Log in" class="w3-btn w3-black w3-round">
            <span id="return" class="w3-padding-large">&#8634;</span>
        </form>
    </div>
    <div class="w3-quarter w3-center w3-animate-opacity">
        <button id="button2"><span>Just For Fun</span></button>
        <form action="MainServlet" method="get">
            <br><br>
            <div class="invisForm" id="form2">
                <input type="text" name="nick" placeholder="Nickname..." class="w3-input" id="input2" disabled><br><br>
                <input type="submit" value="Come in" class="w3-btn w3-round customButton">
            </div>
        </form>
    </div>
</div>
<div class="w3-col w3-container w3-quarter"></div>
<div class="w3-container w3-display-bottomright"><p>Welcome to our chat &#169;</p></div>
</body>
<script src="service/js/help/jquery-3.2.1.min.js"></script>
<script src="service/js/authorisationScript.js"></script>
</html>