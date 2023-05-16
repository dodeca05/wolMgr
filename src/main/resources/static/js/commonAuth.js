function authCheck() {
    let Authorization = localStorage.getItem("Authorization");
    ajaxForm('GET', '/auth/duplication/'+Authorization);
}

function logout() {
    localStorage.setItem("Authorization", "");
}

function ajaxForm(type, url, data, successCallback) {
    $.ajax({
        type: type,
        url: url,
        dataType: 'JSON',
        contentType: 'application/json; charset=UTF-8',
        data: JSON.stringify(data),
        success: successCallback,
        error: function (request, status, error) {
            console.log(error)
        }
    });
}

$(document).ready(function () {
    authCheck();
});