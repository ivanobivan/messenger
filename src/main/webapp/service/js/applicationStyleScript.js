$(document).ready(function () {
    $("#buttonDataUser").click(function () {
        $('#dataUserPanel').toggle();
        $("#messagePanel").toggleClass("m8");
        $("#messagePanel").toggleClass("m6");
    });
    $("#btn1").click(function () {
        $("#input1").prop("disabled",false);
    });
    $("#test:first-child").hover(function () {
        $("#sideBar").toggleClass("w3-light-blue");
        $("#sideBar").toggleClass("w3-sand");
    });

});

