const signUpBox = document.querySelector(".sign-up-box");

let isCheckId = false;
let isCheckEmail = false;
let isEmailNumberCheck = false;
let authNum = null;

signUpBox.addEventListener("click", handleClick, false);

// 회원가입 창으로 이동하는 로직
function handleClick(event) {
    if (signUpBox.classList.contains("active")) return;
    let str = `
        <span onclick="event.stopPropagation(); removedActive();">X</span>
        <input id="joinUsername" type="text" name="username" placeholder="Username"/>
        <input id="joinUserid" type="text" name="userid" placeholder="userid"/>
        <button onclick="idCheck()" type="button" class="btn btn-info mb">아이디 확인</button>
        <input id="joinPassword" type="password" name="password" placeholder="Password"/>
        <input id="joinEmail" type="email" name="email" placeholder="Email"/>
        <button onclick="emailCheck();" type="button" class="btn btn-info mb">이메일 확인</button>
        <input id="joinEmailNum" type="text" name="emailNum" placeholder="Authentication number"/>
        <button onclick="emailNumCheck();" type="button" class="btn btn-info mb">인증번호 확인</button>
        <button onclick="event.stopPropagation(); handleSignUp();" >Sign up</button>
    `;
    this.classList.toggle("active");
    this.innerHTML = "";
    setTimeout(() => (this.innerHTML = str), 500);
}

// 로그인 창으로 이동하는 로직
function removedActive() {
    signUpBox.classList.remove("active");
    signUpBox.innerHTML = `<i class="material-icons">create</i>`;
}

function handleSignUp() {
    // active 클래스를 가진 요소를 선택
    let signUpBoxActive = $(".active");
    // 선택된 요소에서 input 태그들을 선택
    let inputs = signUpBoxActive.find("input");
    // 각 input 태그에서 값을 가져옴
    let username = inputs[0].value;
    let userId = inputs[1].value;
    let password = inputs[2].value;
    let email = inputs[3].value;

    // 입력값이 비어있는지 확인
    if (username.trim() === "" || userId.trim() === "" || password.trim() === "" || email.trim() === "") {
        alert('필수입력란이 비어있습니다.');
        return;
    }

    if (!isCheckId) {
        alert('아이디 중복확인을 해주세요.');
    }

    if (!isCheckEmail) {
        alert('이메일 중복확인을 해주세요.');
    }

    if (!isEmailNumberCheck) {
        alert('이메일 인증번호 입력을 해주세요');
    }

    // 회원가입에 필요한 데이터 생성
    let data = { userId, password, username, email }

    // 서버로 데이터 전송
    ajaxForm('POST', '/join', data, function (result) {
        console.log('회원가입 완료');
    });

    // 회원가입 폼 닫기
    removedActive();
}

// 아이디 중복체크 하는 로직
function idCheck() {

    let userId = $('#joinUserid').val();
    let url = '/id/duplication?userId=' + userId

    ajaxForm('GET', url, null, function (result) {
        if (result.result === 'noContain') {
            isCheckId = true;
            alert('아이디가 사용가능 합니다.');
            $('#joinUserid').prop('readonly', true);
            $('#joinUserid').css('background-color', 'gray');
        } else {
            alert('아이디가 중복입니다.');
            $('#joinUserid').val('');
            $('#joinUserid').focus();
        }
    });
}

// 이메일 중복체크 하는 로직
function emailCheck() {

    let email = $('#joinEmail').val();
    let url = '/email/duplication?email=' + email
    let mailSendNumUrl = '/mailSendNum?email=' + email

    ajaxForm('GET', url, null, function (result) {
        if (result.result === 'noContain') {
            isCheckEmail = true;
            alert('이메일이 사용가능 합니다. 이 이메일로 인증번호를 발송했습니다. 확인해주세요.');
            ajaxForm('GET', mailSendNumUrl, null, function (result) { authNum = result.checkNumber; });
        } else {
            alert('이메일이 중복입니다.');
            $('#joinEmail').val('');
            $('#joinEmail').focus();
        }
    });
}

// 이메일 랜덤번호 체크로직
function emailNumCheck() {

    let emailNum = $('#joinEmailNum').val();

    let checkNumber = authNum;

    if (emailNum == checkNumber) {
        isEmailNumberCheck = true;
        alert('인증되었습니다!');
        $('#joinEmail').css('background-color', 'gray');
        $('#joinEmailNum').css('background-color', 'gray');
        $('#joinEmail').prop('readonly', true);
        $('#joinEmailNum').prop('readonly', true);

    } else {
        alert('인증번호가 다릅니다. 다시 입력해주세요.');
        $('#joinEmailNum').val('');
        $('#joinEmailNum').focus();
    }

}

// 로그인 로직
function login() {

    let userId = $('#loginUserid').val();
    let password = $('#loginPassword').val();
    let url = '/id/duplication?userId=' + userId

    // 로컬 스토리지 초기화
    let init = localStorage.setItem("Authorization", "");
    // 로컬 스토리지 에서 가져옴
    let auth = localStorage.getItem("Authorization");

    let data = { userId, password }

    ajaxForm('GET', url, null, function (result) {
        if (result.result === 'noContain') {
            alert('아이디가 존재하지 않습니다.');
            $('#loginUserid').val('');
            $('#loginPassword').val('');
            $('#loginUserid').focus();
            return;
        } else {
            ajaxForm('POST', '/login', data,
                function (result) {
                    // 로그인 성공시 로컬스토리지에 토큰 저장함
                    localStorage.setItem("Authorization", result.Authorization);
                    window.location.href = '/web/main'

                },
                function (error) {
                    alert('비밀번호가 일치하지 않습니다.');
                    $('#loginPassword').val('');
                    $('#loginPassword').focus();
                    return;
                });
        }
    });


}

// 공통 ajax폼을 틀로 만듬
function ajaxForm(type, url, data, successCallback, errorCallback) {
    $.ajax({
        type: type,
        url: url,
        dataType: 'JSON',
        contentType: 'application/json; charset=UTF-8',
        data: JSON.stringify(data),
        success: successCallback,
        error: errorCallback
    });
}
