function authCheck() {

    let Authorization = localStorage.getItem("Authorization");

    $.ajax({
        type: 'GET',
        url: '/auth/check',
        dataType: 'JSON',
        contentType: 'application/json; charset=UTF-8',
        headers: {
            'Authorization': Authorization
        },
        success: function (result){},
        error: function (request, status, error) {
            window.location.href = '/'
        }
    });
}

function logout() {
    localStorage.setItem("Authorization", "");
}

$(document).ready(function () {
    authCheck();
});