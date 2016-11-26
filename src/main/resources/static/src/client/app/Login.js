function saveNewUsers() {
    var id = window.account.id;
    var username = window.account.username;

    fetch(window.backendHost + "/user/create?id=" + id + "&name=" + username);
}