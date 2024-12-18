<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.myaws.myapp.domain.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
/* BoardVo bv = (BoardVo) request.getAttribute("bv"); */
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>글 답변</title>
<style>
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
	border-bottom: 2px solid #ff7a00;
	padding-bottom: 10px;
	margin-bottom: 20px;
}

form {
	display: flex;
	flex-direction: column;
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

input[type="text"], input[type="password"], input[type="file"], textarea {
	width: 100%;
	padding: 10px;
	margin-bottom: 20px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

textarea {
	height: 200px;
	resize: none;
}

.button-group {
	text-align: right;
}

.button-group button {
	padding: 10px 20px;
	margin-left: 10px;
	border: none;
	border-radius: 5px;
	background-color: #333;
	color: white;
	cursor: pointer;
}

.button-group button:hover {
	background-color: #ff7a00;
}
</style>
<script>
	function check() {
		var fm = document.frm;

		if (fm.subject.value == "") {
			alert("제목을 입력해주세요");
			fm.subject.focus();
			return;
		} else if (fm.contents.value == "") {
			alert("내용을 입력해주세요");
			fm.contents.focus();
			return;
		} else if (fm.writer.value == "") {
			alert("작성자를 입력해주세요");
			fm.writer.focus();
			return;
		} else if (fm.password.value == "") {
			alert("비밀번호를 입력해주세요");
			fm.password.focus();
			return;
		}
		fm.action = "${pageContext.request.contextPath}/board/boardReplyAction.aws";
		fm.method = "post";
		fm.enctype = "multipart/form-data";
		fm.submit();
	}
</script>
</head>
<body>
	<div class="container">
		<h2>글 답변</h2>
		<form name="frm">
			<input type="hidden" name="originbidx" value="${bv.originbidx }">
			<input type="hidden" name="depth" value="${bv.depth }"> 
			<input type="hidden" name="level_" value="${bv.level_ }">
			<table>
				<tr>
					<th>제목</th>
					<td><input type="text" id="title" name="subject"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea id="content" name="contents"></textarea></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" id="author" name="writer"></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" id="password" name="password"></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><input type="file" id="attachfile" name="attachfile"></td>
				</tr>
			</table>
			<div class="button-group">
				<button type="button" onclick="check()">저장</button>
				<button type="button" onclick="history.back()">취소</button>
			</div>
		</form>
	</div>
</body>
</html>
