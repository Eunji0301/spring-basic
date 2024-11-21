<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.myaws.myapp.domain.*"%>
<%
// ArrayList 객체를 화면까지 가져왔다.
ArrayList<MemberVo> alist = (ArrayList<MemberVo>) request.getAttribute("alist");
//
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록 보기</title>
<style>
table {
	border: 1px solid blue;
	border-collapse: collapse;
}

td, th {
	border: 1px dotted green;
	padding: 10px;
	text-align: center;
}

th {
	width: 100px;
	height: 50px;
}

td {
	width: 100px;
	height: 25px;
}

thead, tfoot {
	background: darkgray;
	color: yellow;
}

tfoot {
	border-bottom: 1px solid lightgray;
}

tbody tr:nth-child(even) {
	background: aliceblue;
}

tbody tr:hover {
	background: pink;
}
</style>
</head>
<body>
	<h3>회원 목록</h3>
	<hr>
	<table>
		<thead>
			<tr>
				<th>회원번호</th>
				<th>회원아이디</th>
				<th>회원이름</th>
				<th>성별</th>
				<th>가입일</th>
			</tr>

		</thead>
		<tbody>
			<%-- <% for(int i = 0; i < alist.size(); i++) { %>
			<tr>
				<td><%=alist.get(i).getMidx() %></td>
				<td><%=alist.get(i).getMemberId() %></td>
				<td><%=alist.get(i).getMemberName() %></td>
				<td><%=alist.get(i).getMemberGender() %></td>
				<td><%=alist.get(i).getWriteDay() %></td>
			</tr>
			<% } %> --%>
			<%
			int num = 0;
			for (MemberVo mv : alist) {
			%>
			<tr>
				<td><%=mv.getMidx()%></td>
				<td><%=mv.getMemberId()%></td>
				<td><%=mv.getMemberName()%></td>
				<td><%=mv.getMemberGender()%></td>
				<td><%=mv.getWriteDay().substring(0, 10)%></td>
			</tr>
			<%
			num += 1;
			}
			%>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5">총 <%=num%>명입니다.
				</td>
			</tr>

		</tfoot>
	</table>
</body>
</html>