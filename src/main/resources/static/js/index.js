//TODO Refresh authorisation page with React involve
//TODO Create modal structure with npm without templates
$(document).ready(function () {
    //TODO This part of code need to be refactor
    if(window.location.href.indexOf("error") >= 0) {
        $("#errorAuthorisation").fadeIn().fadeOut(4000);
    }
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
