<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.myaws.myapp.domain.*"%>
<%
// ArrayList 객체를 화면까지 가져왔다.
ArrayList<AppointmentVo> blist = (ArrayList<AppointmentVo>) request.getAttribute("blist");
//
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>예약 현황</title>
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
	margin: 0;
	padding: 0;
	box-sizing: border-box;                    
	font-family: GangwonEduPowerExtraBoldA;
}

body {
	font-family: GangwonEduPowerExtraBoldA;
	background-color: #f4f4f9;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	height: 100vh;
}

.container {
	background-color: white;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	width: 100%;
	max-width: 600px;
}

h2 {
	text-align: center;
	margin-bottom: 20px;
	color: #333;
}

.nav-buttons {
	display: flex;
	justify-content: center; /* 버튼들을 가운데 정렬 */
	align-items: center;
	padding: 20px;
	color: #007bff;
}

button.nav-btn {
	background-color: #007bff;
	color: white;
	border: 2px solid #007bff;
	padding: 10px 20px;
	border-radius: 8px;
	cursor: pointer;
	font-weight: bold;
	margin: 0 10px; /* 버튼 사이 간격 */
}

button.nav-btn:hover {
	background-color: #0056b3;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

table th, table td {
	padding: 10px;
	text-align: center;
	border: 1px solid #ddd;
}

table th {
	background-color: #007BFF;
	color: white;
}

table tr:nth-child(even) {
	background-color: #f9f9f9;
}

table tr:hover {
	background-color: #f1f1f1;
}
</style>
</head>
<body>
	<!-- 상단 버튼들 -->
	<div class="nav-buttons">
		<button class="nav-btn" onclick="window.location.href='${pageContext.request.contextPath}/index.jsp'">홈</button>
		<button class="nav-btn" onclick="window.location.href='${pageContext.request.contextPath}/appointment/makeAppointment.aws'">예약하기</button>
		<button class="nav-btn" onclick="window.location.href='${pageContext.request.contextPath}/examination/examinationResult.aws'">진료결과조회</button>
	</div>

	<!-- 예약 현황 -->
	<div class="container">
		<h2>예약 현황</h2>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>진료 과목</th>
					<th>진료 의사</th>
					<th>예약 날짜</th>
					<th>예약 시간</th>
					<th>예약자명</th>
				</tr>
			</thead>
			<tbody>
				<!-- <tr>
					<td>1</td>
					<td>내과</td>
					<td>김내과</td>
					<td>2024-11-30</td>
					<td>09:00</td>
					<td>홍길동</td>
				</tr> -->
				<%
				int num = 0;
					for(AppointmentVo av : blist) {
				%>
				<tr>
    				<td><%= av.getAidx() %></td>
    				<td><%= av.getAppointmentSubject() %></td>
    				<td><%= av.getDoctorInCharge() %></td>
    				<td><%= av.getAppointmentDate() %></td>
    				<td><%= av.getAppointmentTime() %></td>
    				<td><a href="${pageContext.request.contextPath}/patient/patientInfo.aws?patientId=<%= av.getPatientId() %>"><%= av.getPatientName() %>
        </a>
    </td>
</tr>

				<%
				num += 1;
				}
				%>
			</tbody>
			<tfoot>
			<tr>
				<td colspan="6">총 <%=num%>명입니다.
				</td>
			</tr>
		</tfoot>
		</table>
	</div>
</body>
</html>
