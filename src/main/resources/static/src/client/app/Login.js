$(document).ready(function() {
    var id = window.account.id;
    var username = window.account.username;
    $.ajax({
        url: "http://localhost:8080/user/create?id=" + id + "&name=" + username
    });
});