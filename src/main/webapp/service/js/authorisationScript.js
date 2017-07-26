
$(document).ready(function () {
    $("#button1").click(function () {
        $("#form1").toggle(200);
        $("#form2").hide(200);
        $("#input1").focus();
    });
    $("#button2").click(function () {
        $("#form2").toggle(200);
        $("#form1").hide(200);
        $("#formReg").hide(200);
        $("#button1").show(200);
        $("#input2").focus();
    });
    $("#buttonReg").click(function () {
        $("#button1").hide(200);
        $("#form1").hide(200);
        $("#formReg").show(200);
        $("#input3").focus();
    });
    $("#return").click(function () {
        $("#formReg").hide(200);
        $("#button1").show(200);
        $("#form1").show(200);
    });
    
    $("#JFFButton").click(function () {
        document.location.href = "application.html";
    })
});
function checkLogin(bool){
    if(bool === false){
        document.getElementById("isLogin").style.display = "block";
    }
}