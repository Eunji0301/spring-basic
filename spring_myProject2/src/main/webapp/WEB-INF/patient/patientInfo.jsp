<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>환자 정보</title>
<style>
@font-face {
	font-family: 'GangwonEduPowerExtraBoldA';
	src:
		url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/GangwonEduPowerExtraBoldA.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

body {
	font-family: GangwonEduPowerExtraBoldA;
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	flex-direction: column;
}

.title {
	font-size: 2rem;
	color: #333;
	margin-bottom: 20px;
	position: relative;
	top: 90px;
}

.container {
	width: 100%;
	max-width: 600px;
	background-color: white;
	padding: 30px;
	border-radius: 8px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	display: flex;
	flex-direction: column;
	align-items: center;
	margin-top: 40px; 
}

.form-group {
	margin-bottom: 15px;
	width: 100%;
}

.form-group label {
	display: block;
	font-size: 1rem;
	margin-bottom: 5px;
	color: #555;
}

.form-group input, .form-group select {
	width: 100%;
	padding: 10px;
	font-size: 1rem;
	border: 1px solid #ddd;
	border-radius: 4px;
	box-sizing: border-box;
}

.form-actions {
	text-align: center;
	margin-top: 20px;
}

.submit-btn {
	width: 100%;
	padding: 10px;
	background-color: #007bff;
	color: white;
	border: none;
	font-size: 1.2rem;
	cursor: pointer;
	border-radius: 4px;
}

.submit-btn:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<div class="title">
		<h2>환자 정보</h2>
	</div>
	<div class="container">
		<form action="examinationWrite.jsp" method="post">
			<input type="hidden" id="pidx" name="pidx" value="${pv.pidx }">
			
			<input type="hidden" id="aidx" name="aidx" value="${param.aidx}" readonly="readonly">
			
			<div class="form-group">
				<label for="patientName">이름</label>
				<input type="text" id="patientName" name="patientName" value="${pv.patientName}" readonly="readonly">
			</div>

			<div class="form-group">
				<label for="patientGender">성별</label>
				<input type="text" id="patientGender" name="patientGender" value="${pv.patientGender}" readonly="readonly">
			</div>

			<div class="form-group">
				<label for="age">나이</label> 
				<input type="text" id="age" name="age"value="${age}" readonly="readonly">
			</div>

			<div class="form-actions">
				<a href="${pageContext.request.contextPath}/examination/examinationWrite.aws?aidx=${param.aidx }&pidx=${pv.pidx }&patientName=${pv.patientName}&patientGender=${pv.patientGender}&age=${age}">
					<button type="button" class="submit-btn">진료 결과 작성</button>
				</a>
			</div>
		</form>
	</div>
</body>
</html>