<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String msg = "";
if (request.getAttribute("msg") != null) {
	msg = (String) request.getAttribute("msg");
}
%>

<!DOCTYPE HTML>
<html>
<head>
<title>의사 로그인 페이지</title>
<script>
<%if (msg != "") {
	out.println("alert('" + msg + "')");
}%>	

	// 아이디 비밀번호 유효성 검사
	function check() {
		// 이름으로 객체찾기
		let id = document.getElementsByName("doctorId");
		let pw = document.getElementsByName("doctorPassword");
		if (id[0].value == "") {
			alert("아이디를 입력해주세요.");
			id[0].focus();
			return;
		} else if (pw[0].value == "") {
			alert("비밀번호를 입력해주세요.");
			pw[0].focus();
			return;
		}
		
		var fm = document.frm;

		fm.action = "${pageContext.request.contextPath}/doctor/doctorLoginAction.aws"; // 가상경로지정 action은 처리하는 의미
		fm.method = "post";
		fm.submit();
		return;
	}
</script>
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

/* 컨테이너 */
.container {
	width: 100%;
	max-width: 1200px;
	margin: 0 auto;
	padding: 50px 20px;
}

/* 제목 */
.title {
	text-align: center;
	margin-bottom: 50px;
}

.title h1 {
	font-size: 3rem;
	color: #333;
}

/* 로그인 폼 */
.login-form {
	background-color: white;
	padding: 40px;
	border-radius: 8px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	max-width: 400px;
	margin: 0 auto;
}

/* 폼 그룹 */
.form-group {
	margin-bottom: 20px;
}

.form-group label {
	display: block;
	font-size: 1.2rem;
	margin-bottom: 8px;
}

.form-group input {
	width: 100%;
	padding: 10px;
	font-size: 1.1rem;
	border: 1px solid #ddd;
	border-radius: 4px;
}

/* 버튼 */
.login-btn {
	width: 100%;
	padding: 10px;
	background-color: #007bff; /* 파란색 버튼 */
	color: white;
	border: none;
	font-size: 1.2rem;
	cursor: pointer;
	border-radius: 4px;
}

.login-btn:hover {
	background-color: #0056b3; /* Hover 시 더 짙은 파란색 */
}

/* 링크 */
.links {
	text-align: center;
	margin-top: 20px;
}

.forgot-password {
	font-size: 1rem;
	color: #007bff;
	text-decoration: none;
}

.forgot-password:hover {
	text-decoration: underline;
}

.signin-btn {
	font-size: 1rem;
	color: #007bff;
	text-decoration: none;
	margin-left: 20px;
}

.signin-btn:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
    <div class="container">
        <div class="title">
            <h2>의사 - 로그인</h2>
        </div>

        <!-- 로그인 폼 -->
        <div class="login-form">
            <form name="frm">
                <div class="form-group">
                    <label for="username">아이디</label>
                    <input type="text" id="username" name="doctorId" placeholder="아이디 입력" required>
                </div>
                <div class="form-group">
                    <label for="password">비밀번호</label>
                    <input type="password" id="password" name="doctorPassword" placeholder="비밀번호 입력" required>
                </div>
                <div class="form-actions">
                    <button type="submit" class="login-btn" onclick="check();">로그인</button>
                    <div class="links">
                        <a href="#" class="forgot-password">Forgot Password?</a>
                        <a href="${pageContext.request.contextPath}/doctor/doctorSignin.aws" class="signin-btn">회원가입</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>
</html>