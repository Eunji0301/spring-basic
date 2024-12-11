<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>이메일쓰기</title>
<style>
@font-face {
    font-family: 'GangwonEduPowerExtraBoldA';
    src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/GangwonEduPowerExtraBoldA.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

* {
    font-family: 'GangwonEduPowerExtraBoldA', sans-serif;
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

body {
    background-color: #f4f4f4;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.container {
    width: 800px;
    background-color: #fff;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

h2 {
    border-bottom: 2px solid #007bff;
    padding-bottom: 10px;
    margin-bottom: 20px;
    color: black;
    font-size: 24px;
}

form {
    display: flex;
    flex-direction: column;
}

table {
    width: 100%;
    margin-bottom: 20px;
    border-collapse: collapse;
}

th, td {
    padding: 10px;
}

th {
    text-align: left;
    font-weight: bold;
}

input[type="text"], textarea {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    margin-top: 5px;
}

textarea {
    height: 150px;
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
    background-color: #007bff;
    color: white;
    cursor: pointer;
    font-size: 16px;
}

.button-group button:hover {
    background-color: #0056b3;
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
        } else if (fm.senderemail.value == "") {
            alert("보내는 메일주소를 입력해주세요");
            fm.senderemail.focus();
            return;
        } else if (fm.receiveremail.value == "") {
            alert("받는 메일주소를 입력해주세요");
            fm.receiveremail.focus();
            return;
        }
        fm.action = "${pageContext.request.contextPath}/email/emailWriteAction.aws";
        fm.method = "post";
        fm.submit();
    }
</script>
</head>
<body>
    <div class="container">
        <h2>메일쓰기</h2>
        <form name="frm">
            <table>
                <tr>
                    <th>메일 제목</th>
                    <td><input type="text" id="title" name="subject"></td>
                </tr>
                <tr>
                    <th>메일 내용</th>
                    <td><textarea id="content" name="contents"></textarea></td>
                </tr>
                <tr>
                    <th>보내는 메일</th>
                    <td><input type="text" id="senderemail" name="senderemail"></td>
                </tr>
                <tr>
                    <th>받는 메일</th>
                    <td><input type="text" id="receiveremail" name="receiveremail"></td>
                </tr>
            </table>
            <div class="button-group">
                <button type="button" onclick="check()">보내기</button>
                <button type="button" onclick="history.back()">취소</button>
            </div>
        </form>
    </div>
</body>
</html>
