let usersTableBody;

let newUserForm;
let newUserUsername;
let newUserPassword;
let newUserEmail;
let newUserRoles;

let newUserFormResetButton;

let editUserForm;
let editUserId;
let editUserUsername;
let editUserEmail;
let editUserRoles;
let editUserModalHeader;

let editUserCloseButton;
let editUserResetButton;

let allRoles = [];


$(function () {
    usersTableBody = $("#usersTableBody");

    newUserForm = $("#newUserForm");
    newUserUsername = $("#newUserUsername");
    newUserPassword = $("#newUserPassword");
    newUserEmail = $("#newUserEmail");
    newUserRoles = $("#newUserRoles");

    newUserFormResetButton = $("#newUserFormResetButton");

    editUserForm = $("#editUserForm");
    editUserId = $("#editUserId");
    editUserUsername = $("#editUserUsername");
    editUserEmail = $("#editUserEmail");
    editUserRoles = $("#editUserRoles");

    editUserModalHeader = $("#editUserModalHeader");

    editUserCloseButton = $("#editUserCloseButton");
    editUserResetButton = $("#editUserResetButton");

    getAllUsers();
    loadAllRolesToForms();

    newUserForm.on("submit", function (event) {
        event.preventDefault();
        let newUser = newUserForm.serialize();
        $.post(newUserForm.attr("action"), newUser, function () {
            getUserByUsernameAndFillWithHimTable(newUserUsername.val());
            $(newUserFormResetButton).trigger("click");
        });
        alert("User " + newUserUsername.val() + " saved");
    });

    editUserForm.submit(function (event) {
        event.preventDefault();
        $.ajax({
            url: editUserForm.attr("action"),
            method: "PUT",
            data: editUserForm.serialize()
        });
        let rolesString = [];
        for (let role of allRoles) {
            if ($("#editUserRole" + role.role).prop("checked")) {
                rolesString += role.role + " ";
            }
        }
        console.log("roles : " + rolesString);
        let id = editUserId.val();
        let currentTr = $("tr#tr" + id);
        currentTr.empty();
        let newTr = "";
        newTr += "<td>" + id + "</td>";
        newTr += "<td id='td" + id + "username'>" + editUserUsername.val() + "</td>";
        newTr += "<td>**********</td>";
        newTr += "<td id='td" + id + "email'>" + editUserEmail.val() + "</td>";
        newTr += "<td id='td" + id + "roles'>" + rolesString + "</td>";
        newTr += "<td><div class='btn-group mr-sm-1'><butto n class='btn btn-primary' data-toggle='modal' data-target='#editUserModal' onclick='openEditUserModal(" + id + ")' data-userId='" + id + "'>Edit</button></div>" +
            "<div class='btn-group'><button class='btn btn-danger' data-userId='" + id + "' onclick='removeUser(" + id + ")'>Delete</button></div></td>";
        currentTr.html(newTr);
        editUserCloseButton.trigger("click");
    })
});

function getAllUsers() {
    $.get("/admin/users", function (data) {
        for (let i = 0; i < data.length; i++) {
            let user = data[i];
            let userRoles = user.roles;
            let rolesString = "";
            for (let j = 0; j < userRoles.length; j++) {
                rolesString += userRoles[j].role + " ";
            }
            var newTr = $("<tr id='tr" + user.id + "'></tr>");
            var newTrTd = "";
            newTrTd += "<td>" + user.id + "</td>";
            newTrTd += "<td id='td" + user.id + "username'>" + user.username + "</td>";
            newTrTd += "<td>**********</td>";
            newTrTd += "<td id='td" + user.id + "email'>" + user.email + "</td>";
            newTrTd += "<td id='td" + user.id + "roles'>" + rolesString + "</td>";
            newTrTd += "<td><div class='btn-group mr-sm-1'><button class='btn btn-primary' data-toggle='modal' data-target='#editUserModal' onclick='openEditUserModal(" + user.id + ")' data-userId='" + user.id + "'>Edit</button></div>" +
                "<div class='btn-group'><button class='btn btn-danger' data-userId='" + user.id + "' onclick='removeUser(" + user.id + ")'>Delete</button></div></td>";
            newTr.html(newTrTd);
            usersTableBody.append(newTr);
        }
    });
}

function getUserByUsernameAndFillWithHimTable(username) {
    $.get("/admin/users/byUsername/" + username, function (user) {
        let userRoles = user.roles;
        let rolesString = "";
        for (let j = 0; j < userRoles.length; j++) {
            rolesString += userRoles[j].role + " ";
        }
        var newTr = $("<tr id='tr" + user.id + "'></tr>");
        var newTrTd = "";
        newTrTd += "<td>" + user.id + "</td>";
        newTrTd += "<td id='td" + user.id + "username'>" + user.username + "</td>";
        newTrTd += "<td>**********</td>";
        newTrTd += "<td id='td" + user.id + "email'>" + user.email + "</td>";
        newTrTd += "<td id='td" + user.id + "roles'>" + rolesString + "</td>";
        newTrTd += "<td><div class='btn-group mr-sm-1'><button class='btn btn-primary' data-toggle='modal' data-target='#editUserModal' onclick='openEditUserModal(" + user.id + ")' data-userId='" + user.id + "'>Edit</button></div>" +
            "<div class='btn-group'><button class='btn btn-danger' data-userId='" + user.id + "' onclick='removeUser(" + user.id + ")'>Delete</button></div></td>";
        newTr.html(newTrTd);
        usersTableBody.append(newTr);
    })
}


function removeUser(id) {
    $("tr#tr" + id).remove();
    $.get("/admin/users/delete/" + id);
}

function loadAllRolesToForms() {
    $.get("/admin/users/roles", function (data) {
        for (let i = 0; i < data.length; i++) {
            allRoles[i] = data[i];
            let roleString = allRoles[i].role;
            newUserRoles.append("<div class='custom-control-inline custom-control custom-switch'>" +
                "<input type='checkbox' class='custom-control-input' name='rolesString' id='newUserRole" + roleString + "' value='" + roleString + "'>" +
                "<label for='newUserRole" + roleString + "' class='font-weight-bold custom-control-label'>" + roleString + "</label></div>");

            editUserRoles.append("<div class='custom-control-inline custom-control custom-switch'>" +
                "<input type='checkbox' class='custom-control-input' name='rolesString' id='editUserRole" + roleString + "' value='" + roleString + "'>" +
                "<label for='editUserRole" + roleString + "' class='font-weight-bold custom-control-label'>" + roleString + "</label></div>");
        }
    });
}

function openEditUserModal(id) {
    editUserResetButton.trigger("click");
    for (let role of allRoles) {
        $("#editUserRole" + role.role).attr("checked", false);
    }
    editUserModalHeader.text("Edit user " + $("#td" + id + "username").text());
    editUserId.attr("value", id);
    editUserUsername.val($("#td" + id + "username").text());
    editUserEmail.val($("#td" + id + "email").text());
    for (let role of allRoles) {
        if ($("#td" + id + "roles").text().includes(role.role)) {
            $("#editUserRole" + role.role).attr("checked", true);
        }
    }
}