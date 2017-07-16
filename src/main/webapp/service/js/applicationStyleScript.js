
$(document).ready(function () {
    $("#avatar").hover(function () {
        $(this).attr("src", "service/pic/change_avatar.png");
    });

    $("#buttonDataUser").click(function () {
        $('#dataUserPanel').animate({width:'toggle'}, 0);
        $("#messagePanel").toggleClass("m8", "m6");
    });
});
