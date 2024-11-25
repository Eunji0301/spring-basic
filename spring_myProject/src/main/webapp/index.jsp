<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>진료 예약 시스템</title>
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
	font-size: 24px;
	font-weight: bold;
	padding: 20px 0;
	background-color: #007BFF;
	color: white;
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
	font-size: 16px;
	color: #333;
	cursor: pointer;
}

.menu li:hover {
	color: #007BFF;
	text-decoration: underline;
}

.main-content {
	flex: 1;
	display: flex;
	justify-content: center;
	align-items: center;
	background-color: #e6e6e6;
	font-size: 18px;
	color: #555;
}
</style>

</head>
<body>
	<div class="container">
		<!-- 상단 제목 -->
		<header class="header"> 진료 예약 시스템 </header>

		<!-- 메뉴 -->
		<nav class="menu">
			<ul>
				<li>로그인</li>
				<li>회원가입</li>
				<li>진료 예약</li>
				<li>진료 결과 조회</li>
				<li>진료 결과 기록</li>
				<li>공지사항 / 커뮤니티</li>
			</ul>
		</nav>

		<!-- 메인 콘텐츠 -->
		<main class="main-content">이미지</main>
	</div>
</body>
</html>