<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>환자 진료 예약</title>
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

.form-group {
	margin-bottom: 15px;
}

label {
	display: block;
	font-weight: bold;
	margin-bottom: 5px;
	color: #555;
}

select, input[type="text"], input[type="date"] {
	width: 100%;
	padding: 8px;
	border: 1px solid #ccc;
	border-radius: 5px;
	font-size: 16px;
}

select:focus, input[type="text"]:focus, input[type="date"]:focus {
	border-color: #007BFF;
}

button.submit-btn, button.nav-btn {
	width: 100%;
	padding: 10px;
	background-color: #007BFF;
	color: white;
	border: none;
	border-radius: 5px;
	font-size: 16px;
	font-family: GangwonEduPowerExtraBoldA;
	cursor: pointer;
}

button.submit-btn:hover, button.nav-btn:hover {
	background-color: #0056b3;
}

.nav-buttons {
	width: 100%;
	max-width: 600px;
	display: flex;
	justify-content: space-around;
	margin-bottom: 20px;
}

.nav-buttons button {
	width: 30%;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>	
	// 버튼을 눌렀을 때 check함수 작동
	function check() {
		var fm = document.frm;

		if (fm.appointmentSubject.value == "") {
			alert("진료 과목을 입력해주세요");
			fm.appointmentSubject.focus();
			return;
		} else if (fm.doctorInCharge.value == "") {
			alert("진료 의사를 입력해주세요");
			fm.doctorInCharge.focus();
			return;
		} else if (fm.patientName.value == "") {
			alert("예약자명을 입력해주세요");
			fm.patientName.focus();
			return;
		} else if (fm.patientPhone.value == "") {
			alert("연락처를 입력해주세요");
			fm.patientPhone.focus();
			return;
		}
		
		var ans = confirm("예약을 확정하시겠습니까 ?");
		if(ans == true) {
			fm.action="${pageContext.request.contextPath}/appointment/makeAppointmentAction.aws";
			fm.method = "post";
			fm.submit();
		}

		return; // 리턴에 값을 쓰지 않으면 그냥 멈춤 종료
	}
</script>
</head>
<body>
	<!-- 상단 버튼들 -->
	<div class="nav-buttons">
		<button class="nav-btn" onclick="window.location.href='${pageContext.request.contextPath}'">홈</button>
		<button class="nav-btn" onclick="window.location.href='${pageContext.request.contextPath}/appointment/viewAppointment.aws'">예약현황</button>
		<button class="nav-btn" onclick="window.location.href='${pageContext.request.contextPath}/examination/examinationResult.aws'">진료결과조회</button>
	</div>

	<!-- 진료 예약 폼 -->
	<div class="container">
		<h2>진료 예약 폼</h2>
		<form name="frm">
			<!-- 진료 과목 -->
			<div class="form-group">
				<label for="subject">진료 과목</label> 
				<input type="text" id="appointmentSubject" name="appointmentSubject">
			</div>

			<!-- 진료 의사 -->
			<div class="form-group">
				<label for="doctor">진료 의사</label> 
				<input type="text" id="doctorInCharge" name="doctorInCharge">
			</div>

			<!-- 예약 날짜 -->
			<div class="form-group">
				<label for="date">예약 날짜</label> 
				<input type="date" id="appointmentDate" name="appointmentDate">
			</div>

			<!-- 예약 시간 -->
			<div class="form-group">
				<label for="time">예약 시간</label> 
				<select id="appointmentTime" name="appointmentTime">
					<option value="">선택하세요</option>
					<option value="0900">09:00</option>
					<option value="1030">10:30</option>
					<option value="1300">13:00</option>
					<option value="1430">14:30</option>
					<option value="1600">16:00</option>
				</select>
			</div>

			<!-- 예약자명 -->
			<div class="form-group">
				<label for="name">예약자명</label> 
				<input type="text" id="patientName" name="patientName" value="${sessionScope.patientName}" readonly="readonly">

			</div>

			<!-- 연락처 -->
			<div class="form-group">
				<label for="contact">연락처</label> 
				<input type="text" id="patientPhone" name="patientPhone" value="${sessionScope.patientPhone}" readonly="readonly">
			</div>

			<!-- 예약 확정 버튼 -->
			<button type="button" class="submit-btn" onclick="check();">예약 확정</button>
		</form>
	</div>
</body>
</html>
