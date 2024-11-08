<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String bidx = request.getAttribute("bidx").toString();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>글 삭제</title>
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

            if (fm.password.value == "") {
                alert("비밀번호를 입력해주세요");
                fm.password.focus();
                return;
            }
            fm.action = "<%=request.getContextPath()%>/board/boardDeleteAction.aws";
            fm.method = "post";
            fm.submit();
        }
    </script>
</head>
<body>
    <div class="container">
        <h2>글 삭제</h2>
        <form name="frm">
            <input type="hidden" name="bidx" value="<%= bidx %>">
            <table>
                <tr>
                    <th>비밀번호</th>
                    <td><input type="password" id="password" name="password" required></td>
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