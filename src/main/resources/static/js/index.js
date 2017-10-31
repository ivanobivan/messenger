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
function getCookie(name) {
    var matches = document.cookie.match(new RegExp(
        "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
    ));
    return matches ? decodeURIComponent(matches[1]) : undefined;
}
function sendRequest() {
    let token = getCookie('XSRF-TOKEN');
    const form = $('#formIn').serializeArray();
    $.ajax({
        url: "/login",
        type: "POST",
        dataType: 'json',
        async:false,
        data: {
            username: form[0].value,
            password: form[1].value,
        },
        beforeSend: function(request) {
            request.setRequestHeader("X-XSRF-TOKEN", token);
        },
        success: function (data) {
            console.log(data);
            window.location.href = window.host + window.port + "/chat.html"
        },
        fail: function (err) {
            console.log(err);
        },
        always: function (err) {
            console.log(err);
            window.location.href = window.host + window.port + "/chat.html"
        }
    });
}