
$(document).ready(function () {
    $("#buttonDataUser").click(function () {
        $('#dataUserPanel').animate({width:'toggle'}, 0);
        $("#messagePanel").toggleClass("m8", "m6");
    });
    $("#btn1").click(function () {
        $("#input1").prop("disabled",false);
    })
});
