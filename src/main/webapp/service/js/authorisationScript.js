function sendDataUserRequest() {
    var servletName = "/testMVC";
    var person = { "userName":document.getElementById("username").value
             , "password":document.getElementById("password").value };
    var personJSON = JSON.stringify(person);
    $.ajax({
        url: servletName,
        method: "POST",
        async: false,
        data: personJSON,
        contentType: "application/json",
        success: function (data) {
            if (data === "NO") {
                alert("Uncown username/password")
            } else if (data === "YES") {
                document.location.href = "../../WEB-INF/lib/application.html";
            }
        }
    });
}

function checkLogin(bool) {
    if (bool === false) {
        document.getElementById("isLogin").style.display = "block";
    }
}

$(document).ready(function () {
    $("#loginForm").submit(function () {
        sendDataUserRequest()
    });
    $("#button1").click(function () {
        $("#loginForm").toggle(200);
        $("#form2").hide(200);
        $("#input1").focus();
    });
    $("#button2").click(function () {
        $("#form2").toggle(200);
        $("#loginForm").hide(200);
        $("#formReg").hide(200);
        $("#button1").show(200);
        $("#input2").focus();
    });
    $("#buttonReg").click(function () {
        $("#button1").hide(200);
        $("#loginForm").hide(200);
        $("#formReg").show(200);
        $("#input3").focus();
    });
    $("#return").click(function () {
        $("#formReg").hide(200);
        $("#button1").show(200);
        $("#loginForm").show(200);
    });
});
