<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>의사 회원가입 페이지</title>
<style>
/* 동일한 스타일 */
@font-face {
	font-family: 'GangwonEduPowerExtraBoldA';
	src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/GangwonEduPowerExtraBoldA.woff')
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
	background-color: #0056b3;
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
<script>
function check() {
	var fm = document.frm;

	if (fm.doctorId.value == "") {
		alert("아이디를 입력해주세요.");
		fm.doctorId.focus();
		return false;
	} else if (fm.doctorPw.value == "") {
		alert("비밀번호를 입력해주세요.");
		fm.doctorPw.focus();
		return false;
	} else if (fm.doctorName.value == "") {
		alert("이름을 입력해주세요.");
		fm.doctorName.focus();
		return false;
	} else if (fm.doctorLicense.value == "") {
		alert("의사면허번호를 입력해주세요.");
		fm.doctorLicense.focus();
		return false;
	} else if (fm.doctorMajor.value == "") {
		alert("전공을 선택해주세요.");
		fm.doctorMajor.focus();
		return false;
	}

	return true;
}
</script>
</head>
<body>
    <div class="container">
        <div class="title">
            <h2>의사 회원가입</h2>
        </div>
        <form name="frm" action="registerAction.aws" method="post" onsubmit="return check();">
            <div class="form-group">
                <label for="doctorId">아이디</label>
                <input type="text" id="doctorId" name="doctorId" placeholder="아이디를 입력하세요">
            </div>
            <div class="form-group">
                <label for="doctorPw">비밀번호</label>
                <input type="password" id="doctorPw" name="doctorPw" placeholder="비밀번호를 입력하세요">
            </div>
            <div class="form-group">
                <label for="doctorName">이름</label>
                <input type="text" id="doctorName" name="doctorName" placeholder="이름을 입력하세요">
            </div>
            <div class="form-group">
                <label for="doctorMajor">전공</label>
                <select id="doctorMajor" name="doctorMajor">
                    <option value="">전공을 선택하세요</option>
                    <option value="내과">내과</option>
                    <option value="치과">치과</option>
                    <option value="피부과">피부과</option>
                    <option value="정형외과">정형외과</option>
                    <option value="소아과">소아과</option>
                    <option value="산부인과">산부인과</option>
                </select>
            </div>
            <div class="form-group">
                <label for="doctorLicense">의사면허번호</label>
                <input type="text" id="doctorLicense" name="doctorLicense" placeholder="의사면허번호를 입력하세요">
            </div>
            <div class="form-actions">
                <button type="submit" class="submit-btn">회원가입</button>
            </div>
            <div class="links">
                <a href="login.jsp">이미 계정이 있으신가요? 로그인</a>
            </div>
        </form>
    </div>
</body>
</html>