function suspendUser(userId) {
    $.ajax({
        url: "/admin/suspend-user",
        type: "POST",
        data: "userId="+userId,
        dataType : "json",
        success: updatePage,
        error: updateError
    });
}

function activateUser(userId) {
    $.ajax({
        url: "/admin/activate-user",
        type: "POST",
        data: "userId=" + userId,
        dataType: "json",
        success: updatePage,
        error: updateError
    });
}

function updatePage(response) {
    let idLabel;
    if (response.hasOwnProperty('error')) {
        displayError(response.error)
    } else {
        idLabel = "user-" + response.id + "-status"
        $("#" + idLabel).empty()
        if (response.active) {
            $("#" + idLabel).append("Yes")
        } else {
            $("#" + idLabel).append("No")
        }
        idLabel = "user-" + response.id + "-operation"
        $("#" + idLabel).empty()
        if (response.active) {
            $("#" + idLabel).append('<button onclick="suspendUser('+response.id+')">Suspend</button>')
        } else {
            $("#" + idLabel).append('<button onclick="activateUser('+response.id+')">Activate</button>')
        }
    }
}
