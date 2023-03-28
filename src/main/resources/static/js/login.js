function loginInfo(){
    let loginId = $('.login-id').val();
    let loginPass = $('.login-pass').val();

    if((loginId.trim() == '')||(loginId.length > 15)){
        alert('아이디가 비어있거나 15글자를 넘어갑니다.');
        $('.login-id').focus();
        return false;
    }else if((loginPass.trim() == '')||(loginPass.length > 15)){
        alert('비밀번호가 비어있거나 15글자를 넘어갑니다.');
        $('.login-pass').focus();
        return false;
    }else{

        let data = {
            loginId,
            loginPass
        }
        $.ajax({
            type : 'POST',           // 타입 (get, post, put 등등)
            url : '/login',           // 요청할 서버url
            dataType : 'json',       // 데이터 타입 (html, xml, json, text 등등)
            contentType : 'application/json;charset=UTF-8', //콘텐츠 타입 json...
            data : JSON.stringify(data),  // 보낼 데이터 (Object , String, Array)
            success : function(result) { // 결과 성공 콜백함수
                console.log(result);
            },
            error : function(request, status, error) { // 결과 에러 콜백함수
                console.log(error)
            }
        })
    }
}