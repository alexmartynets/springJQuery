$(function () {
    var userNameSpan = $("#username");
    var userRoleSpan = $("#roles")
    var linkForAdminPage = $("#linkForAdminPage");
    getUsername();
    getUserRoles();
    showLinkForAdminPage();

    function getUsername() {
        $.get("/user/getUsername", function (data) {
            userNameSpan.text(data);
        });
    }

    function getUserRoles() {
        $.get("/user/getUserRoles", function (data) {
            for (let i = 0; i < data.length; i++) {
                allRoles[i] = data[i];
                let roleString = allRoles[i].role;
                userRoleSpan.text(roleString);
            }
        });
    }

    function showLinkForAdminPage() {
        $.get("/user/hasAdminRole", function (data) {
            if (data === true) {
                linkForAdminPage.show();
            } else {
                linkForAdminPage.hide();
            }
        })
    }
});
