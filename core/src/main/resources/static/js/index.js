//TODO Refresh authorisation page with React involve
//TODO Create modal structure with npm without templates
$(document).ready(function () {
    //TODO This part of code need to be refactor
    if(window.location.href.indexOf("error") >= 0) {
        $("#errorAuthorisation").fadeIn().fadeOut(6000);
    }
});
function checkMode() {
    $("#forfunmode").prop('checked') ? $("#password").attr('disabled',true):$("#password").attr('disabled',false)

}