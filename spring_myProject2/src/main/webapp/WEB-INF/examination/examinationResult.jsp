<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<button onclick="location.href='home.jsp'">홈으로</button>
		<button onclick="location.href='reservation.jsp'">예약내역</button>
	</div>
	<div class="title">
		<h2>진료 결과</h2>
	</div>
	
	<!-- 환자 -->
	<div class="result-section">
		<h3>환자</h3>
		<p>이름 : 홍길동</p>
		<p>성별 : 남성</p>
		<p>나이 : 35세</p>
	</div>
  
	<!-- 의사 -->
	<div class="result-section">
		<h3>의사</h3>
		<p>이름 : 김철수</p>
		<p>진료과 : 내과</p>
		<p>면허번호 : I-123456789</p>
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
				<td>120/80 mmHg</td>
			</tr>
			<tr>
				<td>혈액검사</td>
				<td>정상</td>
			</tr>
			<tr>
				<td>MRI</td>
				<td>특이사항 없음</td>
			</tr>
			<tr>
				<td>X-ray</td>
				<td>특이사항 없음</td>
			</tr>
		</table>
	</div>
	
	<!-- 의사 소견 -->
	<div class="result-section">
		<h3>의사 소견</h3>
		<p>현재 건강 상태는 양호하나, 정기적인 검사가 필요합니다.</p>
	</div>
	
	<!-- 처방약 -->
	<div class="result-section">
		<h3>처방약</h3>
		<p>타이레놀 500mg - 하루 2회 복용</p>
	</div>
	
	<!-- 향후 치료 계획 -->
	<div class="result-section">
		<h3>향후 치료 계획</h3>
		<p>다음 진료 예약: 2024년 12월 10일</p>
		<p>운동량을 늘리고 균형 잡힌 식사를 유지하세요.</p>
	</div>
	
	<!-- 건강 관리 팁 -->
	<div class="result-section">
		<h3>건강 관리 팁</h3>
        <p>하루 30분 이상 규칙적으로 운동하세요.</p>
	</div>
</body>
</html>
