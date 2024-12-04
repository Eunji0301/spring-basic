<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>의사 진료 결과 작성</title>
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

.header {
	display: flex;
	justify-content: center;
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
	margin: 0 10px;
}

.header button:hover {
	background-color: #0056b3;
	border-color: #0056b3;
}

.title {
	text-align: center;
	margin: 20px 0;
}

.title h2 {
	font-size: 24px;
	color: #333;
}

.result-section {
	background: #ffffff;
	border-radius: 10px;
	padding: 20px;
	margin: 15px auto;
	width: 90%;
	max-width: 800px; /* 섹션 최대 너비 */
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.result-section h3 {
	margin-top: 0;
	font-size: 18px;
	border-bottom: 2px solid #007bff;
	padding-bottom: 5px;
}

.result-section input, .result-section textarea, .result-section select {
	width: 100%; /* 입력 필드가 섹션 너비에 맞춤 */
	max-width:750px; /* 입력 필드 최대 너비 */
	padding: 10px;
	margin: 10px 0;
	border: 1px solid #ddd;
	border-radius: 5px;
	font-size: 14px;
}

.result-section textarea {
	height: 80px; /* 텍스트 영역 높이 */
	resize: none; /* 크기 조정 비활성화 */
}

button[type="submit"] {
	background-color: #007bff;
	color: white;
	padding: 10px 20px;
	border: 2px solid #007bff;
	border-radius: 8px;
	cursor: pointer;
	font-size: 16px;
}

button[type="submit"]:hover {
	background-color: #0056b3;
	border-color: #0056b3;
}

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
		<button onclick="window.location.href='${pageContext.request.contextPath}/examination/examinationResult.aws'">진료 결과 조회</button>
	</div>
	<div class="title">
		<h2>진료 결과 작성 폼</h2>
	</div>
	
	<form action="submitResult.jsp" method="post">
		<!-- 환자 정보 입력 -->
		<div class="result-section">
			<h3>환자 정보</h3>
			<input type="text" name="patientName" placeholder="환자 이름">
			<select name="gender" required>
				<option value="">성별 선택</option>
				<option value="남성">남성</option>
				<option value="여성">여성</option>
			</select>
			<input type="number" name="age" placeholder="환자 나이">
		</div>
		
		<!-- 검사 결과 입력 -->
		<div class="result-section">
			<h3>검사 결과</h3>
			<input type="text" name="bloodPressureResult" placeholder="혈압 (예: 120/80 mmHg)">
			<input type="text" name="bloodTestResult" placeholder="혈액검사 결과">
			<input type="text" name="mriResult" placeholder="MRI 결과">
			<input type="text" name="xrayResult" placeholder="X-ray 결과">
		</div>
		
		<!-- 의사 소견 입력 -->
		<div class="result-section">
			<h3>의사 소견</h3>
			<textarea name="doctorOpinion" placeholder="의사 소견을 입력하세요"></textarea>
		</div>
		
		<!-- 처방약 입력 -->
		<div class="result-section">
			<h3>처방약</h3>
			<textarea name="prescription" placeholder="처방약 정보를 입력하세요"></textarea>
		</div>
		
		<!-- 향후 치료 계획 입력 -->
		<div class="result-section">
			<h3>향후 치료 계획</h3>
			<textarea name="treatmentPlan" placeholder="향후 치료 계획을 입력하세요"></textarea>
		</div>
		
		<!-- 건강 관리 팁 입력 -->
		<div class="result-section">
			<h3>건강 관리 팁</h3>
			<textarea name="healthTips" placeholder="건강 관리 팁을 입력하세요"></textarea>
		</div>
		
		<!-- 제출 버튼 -->
		<div style="text-align: center; margin: 20px 0;">
			<button type="submit">
				작성 완료
			</button>
		</div>
	</form>
</body>
</html>
