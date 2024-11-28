<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>환자 회원가입 페이지</title>
<style>
@font-face {
	font-family: 'GangwonEduPowerExtraBoldA';
	src:
		url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/GangwonEduPowerExtraBoldA.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

body {
	font-family: GangwonEduPowerExtraBoldA;
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
}

.container {
	width: 100%;
	max-width: 600px;
	margin: 50px auto;
	background-color: white;
	padding: 30px;
	border-radius: 8px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.title {
	text-align: center;
	margin-bottom: 20px;
}

.title h2 {
	font-size: 2rem;
	color: #333;
}

.form-group {
	margin-bottom: 15px;
}

.form-group label {
	display: block;
	font-size: 1rem;
	margin-bottom: 5px;
	color: #555;
}

.form-group input, .form-group select {
	width: 100%;
	padding: 10px;
	font-size: 1rem;
	border: 1px solid #ddd;
	border-radius: 4px;
	box-sizing: border-box;
}

.form-group input[type="radio"] {
	width: auto;
	margin-right: 5px;
}

.form-group .gender-label {
	display: inline-block;
	margin-right: 15px;
	font-size: 0.9rem;
}

.form-actions {
	text-align: center;
	margin-top: 20px;
}

.submit-btn {
	width: 100%;
	padding: 10px;
	background-color: #007bff; /* 파란색 */
	color: white;
	border: none;
	font-size: 1.2rem;
	cursor: pointer;
	border-radius: 4px;
}

.submit-btn:hover {
	background-color: #0056b3; /* Hover 시 더 짙은 파란색 */
}

.links {
	text-align: center;
	margin-top: 15px;
}

.links a {
	font-size: 0.9rem;
	color: #007bff;
	text-decoration: none;
}

.links a:hover {
	text-decoration: underline;
}
</style>
<!-- CDN주소 -->
<script src="https://code.jquery.com/jquery-latest.min.js"></script>

<script>
	const email = /[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]$/i;
	// alert(email.test("hello@email.com"));
	// 버튼을 눌렀을 때 check함수 작동
	function check() {
		var fm = document.frm;

		if (fm.patientId.value == "") {
			alert("아이디를 입력해주세요");
			fm.patientId.focus();
			return;
		}  else if (fm.patientPassword.value == "") {
			alert("비밀번호를 입력해주세요");
			fm.patientPassword.focus();
			return;
		} else if (fm.patientPasswordIsRight.value == "") {
			alert("비밀번호 확인을 입력해주세요.");
			fm.patientPwIsRight.focus();
			return;
		} else if (fm.patientPassword.value != fm.patientPasswordIsRight.value) {
			alert("비밀번호가 일치하지 않습니다.");
			fm.patientPasswordIsRight.focus();
			return;
		} else if (fm.patientName.value == "") {
			alert("이름을 입력해주세요.");
			fm.patientName.focus();
			return;
		} else if (fm.patientEmail.value == "") {
			alert("이메일을 입력해주세요.");
			fm.patientEmail.focus();
			return;
		} else if (email.test(fm.patientEmail.value) == false) {
			alert("이메일 형식이 올바르지 않습니다.");
			fm.patientEmail.value = "";
			fm.patientEmail.focus();
			return;
		} else if (fm.patientPhone.value == "") {
			alert("연락처를 입력해주세요.");
			fm.patientPhone.focus();
			return;
		} else if (fm.patientBirth.value == "") {
			alert("생년월일을 입력해주세요.");
			fm.patientBirth.focus();
			return;
		} else if (fm.patientAddress.value == "") {
			alert("주소를 입력해주세요.");
			fm.patientAddress.focus();
			return;
		}
		
		var ans = confirm("저장하시겠습니까 ?");
		if(ans == true) {
			// html 홈태그 기능을 자바스크립트로 제어

			// 가상경로를 사용해서 쓸 예정. 가짜경로 형식은 /기능/세부기능.aws
			fm.action="<%=request.getContextPath()%>/patient/patientSigninAction.aws";
			fm.method = "post";
			fm.submit();
		}

		return; // 리턴에 값을 쓰지 않으면 그냥 멈춤 종료
	}
</script>
</head>
<body>
    <div class="container">
        <div class="title">
            <h2>환자 회원가입</h2>
        </div>
        	<form name="frm">
            <div class="form-group">
                <label for="patientId">아이디</label>
                <input type="text" id="patientId" name="patientId" placeholder="아이디를 입력하세요">
            </div>
            <div class="form-group">
                <label for="password">비밀번호</label>
                <input type="password" id="patientPassword" name="patientPassword" placeholder="비밀번호를 입력하세요">
            </div>
            <div class="form-group">
                <label for="confirmPassword">비밀번호 확인</label>
                <input type="password" id="patientPasswordIsRight" name="patientPasswordIsRight" placeholder="비밀번호를 다시 입력하세요">
            </div>
            <div class="form-group">
                <label for="name">이름</label>
                <input type="text" id="patientName" name="patientName" placeholder="이름을 입력하세요">
            </div>
            <div class="form-group">
                <label for="birthDate">생년월일</label>
                <input type="date" id="patientBirth" name="patientBirth" required>
            </div>
            <div class="form-group">
                <label>성별</label>
                <input type="radio" name="patientGender" id="select1"value="M"><label for="select1">남성</label> 
                <input type="radio" name="patientGender" id="select2" value="F"><label for="select2">여성</label>
            </div>
            <div class="form-group">
                <label for="address">주소</label>
                <input type="text" id="patientAddress" name="patientAddress" placeholder="주소를 입력하세요">
            </div>
            <div class="form-group">
                <label for="phone">전화번호</label>
                <input type="tel" id="patientPhone" name="patientPhone" placeholder="전화번호를 입력하세요 (예: 010-1234-5678)">
            </div>
            <div class="form-group">
                <label for="email">이메일</label>
                <input type="email" id="patientEmail" name="patientEmail" placeholder="이메일을 입력하세요">
            </div>
    		<div class="form-actions">
        		<button type="button" class="submit-btn" onclick="check();">회원가입</button>
    		</div>
            <div class="links">
                <a href="${pageContext.request.contextPath}/patient/patientLogin.aws">이미 계정이 있으신가요? 로그인</a>
            </div>
            </form>
    </div>
</body>
</HTML>