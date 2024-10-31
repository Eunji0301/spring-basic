<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
response.setContentType("text/html; charset=UTF-8");
%>

<!DOCTYPE HTML>
<HTML>
<HEAD>
<TITLE>Login</TITLE>
<script>
	// 아이디 비밀번호 유효성 검사
	function check() {
		// 이름으로 객체찾기
		let id = document.getElementsByName("id");
		let pw = document.getElementsByName("pw");
		// alert(id[0].value);
		// alert(pw[0].value); 
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

		fm.action = "<%=request.getContextPath()%>/member/memberLoginAction.aws"; // 가상경로지정 action은 처리하는 의미
		fm.method = "post";
		fm.submit();
		return;
	}
</script>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f0f2f5;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

/* Styling the table */
table {
	background-color: white;
	border-radius: 8px;
	padding: 20px;
	box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
	width: 400px;
}

td {
	padding: 10px;
}

/* Styling the input fields */
input[type="text"], input[type="password"] {
	width: 100%;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	font-size: 14px;
}

/* Styling the buttons */
input[type="button"], input[type="submit"] {
	width: 100%;
	padding: 10px;
	background-color: #4CAF50;
	color: white;
	border: none;
	border-radius: 5px;
	font-size: 16px;
	cursor: pointer;
}

input[type="button"]:hover, input[type="submit"]:hover {
	background-color: #45a049;
}

/* Header styling */
header {
	font-size: 24px;
	text-align: center;
	margin-bottom: 20px;
	color: #333;
}
</style>
</HEAD>

<BODY>
	<header>로그인 페이지</header>
	<form name="frm">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id" maxlength="100"
					style="width: 250px;" placeholder="아이디를 입력하세요"></input></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pw" maxlength="100"
					style="width: 250px;" placeholder="비밀번호를 입력하세요"></input></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><input
					type="button" name="okay" value="확인" onclick="check()"></td>
			</tr>
			<tr>
				<td style="text-align: center;"><input type="button"
					name="searchId" value="아이디찾기"></td>
				<td style="text-align: center;"><input type="button"
					name="searchPw" value="비밀번호찾기"></td>
			</tr>
		</table>
	</form>
</BODY>
</HTML>
