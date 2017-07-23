$(document).ready(function () {
    var bool = true;
    $("#buttonDataUser").click(function () {
        $('#dataUserPanel').toggle();
        $("#messagePanel").toggleClass("m8")
            .toggleClass("m6");
    })
        .hover(function () {
            $(this).find("> i").toggleClass("fa-spin");
    });

    $("#btn1").click(function () {
        $("#input1").prop("disabled",false);
    });

    $("#message").focus();
    $("#send").focus();
    $("#settingsUser").click(function () {
        if(bool){
            $("#rootPlus").css("transform","rotate(90deg)")
                .css("transition","0.2s");
            bool = false;
        }else{
            $("#rootPlus").css("transform","rotate(-90deg)")
                .css("transition","0.2s");
            bool = true;
        }

        $("#settingsPanel").animate({height:'toggle'}, 200);
    });
    $("#clearButton").click(function () {
        $("#customMessageField").empty();
    })
});