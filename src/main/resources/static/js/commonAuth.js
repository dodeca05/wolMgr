function authCheck() {

    let Authorization = localStorage.getItem("Authorization");

    let data = { Authorization }

    ajaxForm('POST', '/auth/duplication', data);

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