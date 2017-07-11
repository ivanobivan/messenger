<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="service/styles/authorisationStyle.css"/>
    <link rel="stylesheet" href="service/styles/helpStyles/w3.css"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Authorisation</title>
</head>
<body>
<div class="w3-row invis">
    <div class="w3-col w3-container w3-quarter"></div>
    <div class="w3-quarter w3-center w3-animate-opacity">
        <form action="#">
            <button id="button1"><span>Sign In</span></button>
            <br><br>
            <div class="invisForm" id="form1">
                <input type="text" placeholder="Username" class="w3-input w3-animate-input" style="width: 96%;" ><br>
                <input type="password" placeholder="Password" class="w3-input w3-animate-input" style="width: 96%;" ><br>
                <input type="submit" value="Sign in" class="w3-btn w3-indigo"><br>
                <p>If you don't own account, then create it</p>
                <button  class="w3-teal w3-btn" id="buttonReg"><span>Log in</span></button>
            </div>
        </form>
        <form action="workApp/workApp.jsp" class="invisForm" id="formReg">
            <p>Enter information for authorisation</p>
            <input type="text" placeholder="Username" class="w3-input w3-animate-input" style="width: 96%;" ><br>
            <input type="password" placeholder="Password" class="w3-input w3-animate-input" style="width: 96%;" ><br>
            <input type="password" placeholder="Confirm password" class="w3-input w3-animate-input" style="width: 96%;" ><br>
            <i class="fa fa-vk fa-2x"></i>
            <i class="fa fa-twitter fa-2x" ></i>
            <i class="fa fa-facebook-square fa-2x" ></i>
            <i class="fa fa-google fa-2x"></i><br>
            <input type="submit" value="Log in" class="w3-btn w3-indigo">
            <span id="return">&#8634;</span>
        </form>
    </div>
    <div class="w3-quarter w3-center w3-animate-opacity">
        <form action="#">
            <button id="button2"><span>Just For Fun</span></button>
            <br><br>
            <div class="invisForm" id="form2">
                <input type="text" placeholder="Nickname..." class="w3-input w3-animate-input"
                       style="width: 90%;" ><br><br>
                <input  type="submit" value="Come in" class="w3-btn w3-indigo">
            </div>
        </form>
    </div>
</div>
<div class="w3-col w3-container w3-quarter"></div>
<div class="w3-container w3-display-bottomright"><p>Welcome to our chat	&#169;</p></div>
</body>
<script src="service/js/helpScript/jquery-3.2.1.min.js"></script>
<script src="service/js/authorisationScript.js"></script>
</html>