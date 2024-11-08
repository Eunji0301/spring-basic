<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ include file="/common/loginCheck.jsp"%> --%>
<%
String msg = "";
if (request.getAttribute("msg") != null) {
	msg = (String) request.getAttribute("msg");
}

if (msg != "") {
	out.println("<script>alert('" + msg + "');</script>");
}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>글쓰기</title>
<style>
body {
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh; /* 브라우저창의 100 view height. 부모 태그와 상관없이 꽉 채워짐*/
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

input[type="text"], input[type="password"], input[type="file"], textarea
	{
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
	cursor: pointer; /* 커서모양 : 포인터(손가락) */
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
		// fm.submit();
		fm.action = "<%=request.getContextPath()%>/board/boardWriteAction.aws";
		fm.method = "post";
		fm.enctype = "multipart/form-data";
		fm.submit();

		return;
	}
</script>
</head>
<body>
	<div class="container">
		<h2>글쓰기</h2>
		<form name="frm">
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