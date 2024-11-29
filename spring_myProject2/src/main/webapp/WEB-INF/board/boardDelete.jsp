<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>글 삭제</title>
<style>
@font-face {
	font-family: 'GangwonEduPowerExtraBoldA';
	src:
		url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/GangwonEduPowerExtraBoldA.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

* {
  font-family: GangwonEduPowerExtraBoldA;
}

body {
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.container {
	width: 800px;
	margin: 0 auto;
	background-color: #fff;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

h2 {
	border-bottom: 2px solid #007bff;
	color: black;
	padding-bottom: 10px;
	margin-bottom: 20px;
}

table {
	width: 100%;
	margin-bottom: 20px;
}

th {
	text-align: left;
	font-weight: bold;
	padding: 10px 0;
}

input[type="password"] {
	width: 100%;
	padding: 10px;
	margin-bottom: 20px;
	border: 1px solid #ccc;
	border-radius: 5px;
	font-size: 14px;
}

.button-group {
	text-align: right;
}

.button-group button {
	padding: 10px 20px;
	margin-left: 10px;
	border: none;
	border-radius: 5px;
	background-color: #007bff;
	color: white;
	cursor: pointer;
	font-size: 14px;
	transition: background-color 0.3s ease;
}

.button-group button:hover {
	background-color: #0056b3;
}
</style>
<script>
	function check() {
		var fm = document.frm;

		if (fm.password.value == "") {
			alert("비밀번호를 입력해주세요");
			fm.password.focus();
			return;
		}
		fm.action = "${pageContext.request.contextPath}/board/boardDeleteAction.aws";
		fm.method = "post";
		fm.submit();
	}
</script>
</head>
<body>
	<div class="container">
		<h2>글 삭제</h2>
		<form name="frm">
			<input type="hidden" name="bidx" value="${bidx }">
			<table>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" id="password" name="password"></td>
				</tr>
			</table>
			<div class="button-group">
				<button type="button" onclick="check()">삭제</button>
				<button type="button" onclick="history.back()">취소</button>
			</div>
		</form>
	</div>
</body>
</html>
