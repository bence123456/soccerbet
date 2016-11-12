$(document).ready(function() {
    var id = window.account.id;
    var username = window.account.username;
    $.ajax({
        url: window.backendHost + "/user/create?id=" + id + "&name=" + username
    });
});