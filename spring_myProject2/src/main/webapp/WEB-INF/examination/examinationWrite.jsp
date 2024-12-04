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

button[type="button"] {
	background-color: #007bff;
	color: white;
	padding: 10px 20px;
	border: 2px solid #007bff;
	border-radius: 8px;
	cursor: pointer;
	font-size: 16px;
}

button[type="button"]:hover {
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
<script>
    function check() {
        var fm = document.frm;

        if (fm.examinationBp.value == "") {
            alert("혈압을 입력해주세요");
            fm.examinationBp.focus();
            return;
        } else if (fm.examinationBloodTest.value == "") {
            alert("혈액검사 결과를 입력해주세요");
            fm.examinationBloodTest.focus();
            return;
        } else if (fm.examinationMri.value == "") {
            alert("MRI 결과를 입력해주세요");
            fm.examinationMri.focus();
            return;
        } else if (fm.examinationXray.value == "") {
            alert("X-ray 결과를 입력해주세요");
            fm.examinationXray.focus();
            return;
        } else if (fm.examinationDoctorNotes.value == "") {
            alert("의사 소견을 입력해주세요");
            fm.examinationDoctorNotes.focus();
            return;
        } else if (fm.examinationPrescription.value == "") {
            alert("처방약 정보를 입력해주세요");
            fm.examinationPrescription.focus();
            return;
        } else if (fm.examinationTreatmentPlan.value == "") {
            alert("향후 치료 계획을 입력해주세요");
            fm.examinationTreatmentPlan.focus();
            return;
        } else if (fm.examinationHealthTips.value == "") {
            alert("건강 관리 팁을 입력해주세요");
            fm.examinationHealthTips.focus();
            return;
        }
        
        var ans = confirm("작성을 완료하시겠습니까 ?");
        if(ans == true) {
            fm.action="${pageContext.request.contextPath}/examination/examinationWriteAction.aws";
            fm.method = "post";
            fm.submit();
        }

        return; 
    }
</script>
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
	
	<form name="frm">
		<!-- 환자 정보 입력 -->
		<div class="result-section">
			<h3>환자 정보</h3>
			<input type="text" name="patientName" value="${pv.getPatientName }">
			<select name="patientGender">
    			<option value="">성별 선택</option>
    			<option value="남성" ${pv.patientGender == '남성' ? 'selected' : ''}>남성</option>
    			<option value="여성" ${pv.patientGender == '여성' ? 'selected' : ''}>여성</option>
			</select>
			<input type="number" name="age" value="2024 - year(${pv.getPatientBirth}) + 1">
		</div>
		
		<!-- 검사 결과 입력 -->
		<div class="result-section">
			<h3>검사 결과</h3>
			<input type="text" name="examinationBp" placeholder="혈압 (예: 120/80 mmHg)">
			<input type="text" name="examinationBloodTest" placeholder="혈액검사 결과">
			<input type="text" name="examinationMri" placeholder="MRI 결과">
			<input type="text" name="examinationXray" placeholder="X-ray 결과">
		</div>
		
		<!-- 의사 소견 입력 -->
		<div class="result-section">
			<h3>의사 소견</h3>
			<textarea name="examinationDoctorNotes" placeholder="의사 소견을 입력하세요"></textarea>
		</div>
		
		<!-- 처방약 입력 -->
		<div class="result-section">
			<h3>처방약</h3>
			<textarea name="examinationPrescription" placeholder="처방약 정보를 입력하세요"></textarea>
		</div>
		
		<!-- 향후 치료 계획 입력 -->
		<div class="result-section">
			<h3>향후 치료 계획</h3>
			<textarea name="examinationTreatmentPlan" placeholder="향후 치료 계획을 입력하세요"></textarea>
		</div>
		
		<!-- 건강 관리 팁 입력 -->
		<div class="result-section">
			<h3>건강 관리 팁</h3>
			<textarea name="examinationHealthTips" placeholder="건강 관리 팁을 입력하세요"></textarea>
		</div>
		
		<!-- 제출 버튼 -->
		<div style="text-align: center; margin: 20px 0;">
			<button type="button" onclick="check();">작성 완료</button>
		</div>
	</form>
</body>
</html>
