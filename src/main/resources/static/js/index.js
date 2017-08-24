function sendDataUserRequest() {
    var action = "http://" +  document.location.host + document.location.pathname +  "/login";
    var name = document.getElementById("username").value;
    $.ajax({
        url: action,
        method: "POST",
        async: true,
        data: {username:name},
        dataType: 'json',
        success: function (data) {
            if (data === null) {
                alert("Uncown username/password")
            } else {
               alert("well done")
            }
        }
    });
}

$(document).ready(function () {
    /*$("#form2").submit(function () {
        sendDataUserRequest()
    });*/
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
