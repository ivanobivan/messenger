$(document).ready(function () {
    $("#buttonDataUser").click(function () {
        $('#dataUserPanel').toggle();
        $("#messagePanel").toggleClass("m8");
        $("#messagePanel").toggleClass("m6");
    });
    $("#btn1").click(function () {
        $("#input1").prop("disabled",false);
    });
    $("#buttonDataUser").hover(function () {
        $(this).find("> i").toggleClass("fa-spin");
    })
    $("#message").focus();
});