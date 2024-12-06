<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>개인프로젝트(11/25-12/06)</title>
<style>
@font-face {
    font-family: 'GangwonEduPowerExtraBoldA';
    src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/GangwonEduPowerExtraBoldA.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

body {
    margin: 0;
    font-family: GangwonEduPowerExtraBoldA;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-color: #f9f9f9;
}

.container {
    display: flex;
    flex-direction: column;
    width: 80%;
    height: 80vh;
    margin: 0 auto;
    border: 1px solid #ccc;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    overflow: hidden;
}

.header {
    width: 100%;
    text-align: center;
    font-size: 40px;
    font-weight: bold;
    padding: 20px 0;
    background-color: #007BFF;
    color: white;
}

.user-info {
    text-align: right;
    padding: 10px 20px;
    background-color: #f0f0f0;
    font-size: 20px;
}

.user-info a {

    color: #007BFF;
    text-decoration: none;
}

.user-info a:hover {
    text-decoration: underline;
}

.menu {
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #f2f2f2;
    padding: 10px 0;
    border-bottom: 1px solid #ddd;
}

.menu ul {
    list-style: none;
    display: flex;
    padding: 0;
    margin: 0;
}

.menu li {
    margin: 0 15px;
    font-size: 20px;
    color: #333;
    cursor: pointer;
}

.menu li:hover {
    color: #007BFF;
    text-decoration: underline;
}

.main-content {
    background-image: url('https://img.freepik.com/premium-vector/woman-being-seen-by-doctor-hospital_147644-2830.jpg?w=1060');
    background-size: cover; /* 콘텐츠 크기에 맞게 이미지 조정 */
    background-position: center; /* 이미지 중앙 정렬 */
    flex: 1;
    display: flex;
    justify-content: center;
    align-items: center;
    color: #555;
    font-size: 18px;
    height: 50px; /* 필요 시 높이 지정 */
}

</style>
</head>
<body>
    <div class="container">
        <!-- 로그인한 사용자 이름 표시 -->
        <div class="user-info">
            <c:if test="${!empty sessionScope.pidx}">
                <!-- 환자 로그인 -->
                환자 로그인 - ♥${patientName}님 환영합니다♥&nbsp;<a href="${pageContext.request.contextPath}/patient/patientLogout.aws">로그아웃하려면 클릭하세요.</a>
            </c:if>
            <c:if test="${!empty sessionScope.didx}">
                <!-- 의사 로그인 -->
                의사 로그인 - ♥${doctorName}님 환영합니다♥&nbsp;<a href="${pageContext.request.contextPath}/doctor/doctorLogout.aws">로그아웃하려면 클릭하세요.</a>
            </c:if>
        </div>

        <!-- 상단 제목 -->
        <header class="header">MEDICLOUD</header>
        <!-- 메뉴 -->
        <nav class="menu">
            <ul>
                <li><a href="${pageContext.request.contextPath}/patient/patientLogin.aws">환자 로그인 / 회원가입</a></li>
                <li><a href="${pageContext.request.contextPath}/doctor/doctorLogin.aws">의사 로그인 / 회원가입</a></li>
                <li><a href="${pageContext.request.contextPath}/appointment/makeAppointment.aws">진료 예약</a></li>
                <li><a href="${pageContext.request.contextPath}/appointment/viewAppointment.aws">진료 예약 현황</a></li>
                <li><a href="${pageContext.request.contextPath}/examination/examinationResult.aws">진료 결과 조회</a></li>
                <li><a href="${pageContext.request.contextPath}/examination/examinationWrite.aws">진료 결과 기록</a></li>
                <li><a href="${pageContext.request.contextPath}/board/boardList.aws">공지사항 / 커뮤니티</a></li>
            </ul>
        </nav>

        <!-- 메인 콘텐츠 -->
        <main class="main-content"></main>
    </div>
</body>
</html>
