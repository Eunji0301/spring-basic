<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<%
String pidx = (String) request.getParameter("pidx");

Object resultObj = request.getAttribute("result");
Object pidxObj = request.getAttribute("pidx");
System.out.println("result in JSP: " + resultObj);
System.out.println("pidx in JSP: " + pidxObj);
%>
<!DOCTYPE html>
<html>
<head>
<title>진료 결과</title>
<style>
@font-face {
	font-family: 'GangwonEduPowerExtraBoldA';
	src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/GangwonEduPowerExtraBoldA.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

* {
	font-family: GangwonEduPowerExtraBoldA;
}
body {
	background-color: #f9f9f9;
	margin: 0;
	padding: 0;
}

/* 상단 헤더 스타일 */
.header {
	display: flex;
	justify-content: center; /* 버튼들을 가운데 정렬 */
	align-items: center;
	padding: 20px;
	color: #007bff;
}

.header button {
	background-color: #007bff;
	color: white;
	border: 2px solid #007bff;
	padding: 10px 20px;
	border-radius: 8px;
	cursor: pointer;
	font-weight: bold;
	margin: 0 10px; /* 버튼 사이 간격 */
}

.header button:hover {
	background-color: #0056b3;
	border-color: #0056b3;
}

/* 페이지 제목 스타일 */
.title {
	text-align: center;
	margin: 20px 0;
}

.title h2 {
	font-size: 24px;
	color: #333;
}

/* 결과 카드 스타일 */
.result-section {
	background: #ffffff;
	border-radius: 10px;
	padding: 20px;
	margin: 15px auto;
	width: 90%;
	max-width: 800px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

/* 각 결과 제목 스타일 */
.result-section h3 {
	margin-top: 0;
	font-size: 18px;
	border-bottom: 2px solid #007bff;
	padding-bottom: 5px;
}

/* 결과 항목 스타일 */
.result-section p {
	margin: 8px 0;
	font-size: 14px;
	color: #555;
}

/* 데이터 테이블 스타일 */
.data-table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 10px;
}

.data-table th, .data-table td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: center;
}

.data-table th {
	background-color: #007bff;
	color: white;
}

/* 하단 여백 */
footer {
	text-align: center;
	margin: 20px 0;
	color: #777;
	font-size: 12px;
}

</style>
</head>
<body>
	<div class="header">
		<button onclick="window.location.href='${pageContext.request.contextPath}/index.jsp'">홈으로</button>
		<button onclick="window.location.href='${pageContext.request.contextPath}/appointment/viewAppointment.aws'">예약내역</button>
	</div>
	<div class="title">
		<h2>진료 결과</h2>
	</div>
	
	<!-- 환자 -->
	<div class="result-section">
		
		<h3>환자</h3>
		<%-- <p>환자 ID : ${pidx }</p> --%>
		<p>이름 : </p>
		<p>성별 : </p>
		<p>나이 : 세</p>
	</div>
  
	<!-- 의사 -->
	<div class="result-section">
		<h3>의사</h3>
		<p>이름 : </p>
		<p>진료과 : </p>
		<p>면허번호 : </p>
	</div>
	
	<!-- 검사 결과 -->
	<div class="result-section">
		<h3>검사 결과</h3>
		<table class="data-table">
			<tr>
				<th>검사 항목</th>
				<th>결과</th>
			</tr>
			<tr>
				<td>혈압</td>
				<td>${result.examinationBp}</td>
			</tr>
			<tr>
				<td>혈액검사</td>
				<td>${result.examinationBloodTest}</td>
			</tr>
			<tr>
				<td>MRI</td>
				<td>${result.examinationMri}</td>
			</tr>
			<tr>
				<td>X-ray</td>
				<td>${result.examinationXray}</td>
			</tr>
		</table>
	</div>
	
	<div class="result-section">
		<h3>의사 소견</h3>
		<p>${result.examinationDoctorNotes}</p>
	</div>
	
	<div class="result-section">
		<h3>처방약</h3>
		<p>${result.examinationPrescription}</p>
	</div>

	<div class="result-section">
		<h3>향후 치료 계획</h3>
		<p>${result.examinationTreatmentPlan}</p>
	</div>
	
	<div class="result-section">
		<h3>건강 관리 팁</h3>
        <p>${result.examinationHealthTips}</p>
	</div>
</body>
</html>
