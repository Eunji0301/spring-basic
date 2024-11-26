<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>회원가입 페이지</title>
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
}

.container {
	width: 100%;
	max-width: 600px;
	margin: 50px auto;
	background-color: white;
	padding: 30px;
	border-radius: 8px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.title {
	text-align: center;
	margin-bottom: 20px;
}

.title h2 {
	font-size: 2rem;
	color: #333;
}

.form-group {
	margin-bottom: 15px;
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

.form-group input[type="radio"] {
	width: auto;
	margin-right: 5px;
}

.form-group .gender-label {
	display: inline-block;
	margin-right: 15px;
	font-size: 0.9rem;
}

.form-actions {
	text-align: center;
	margin-top: 20px;
}

.submit-btn {
	width: 100%;
	padding: 10px;
	background-color: #007bff; /* 파란색 */
	color: white;
	border: none;
	font-size: 1.2rem;
	cursor: pointer;
	border-radius: 4px;
}

.submit-btn:hover {
	background-color: #0056b3; /* Hover 시 더 짙은 파란색 */
}

.links {
	text-align: center;
	margin-top: 15px;
}

.links a {
	font-size: 0.9rem;
	color: #007bff;
	text-decoration: none;
}

.links a:hover {
	text-decoration: underline;
}
</style>
<!-- CDN주소 -->
<script src="https://code.jquery.com/jquery-latest.min.js"></script>

<script>
	const email = /[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]$/i;
	// alert(email.test("hello@email.com"));
	// 버튼을 눌렀을 때 check함수 작동
	function check() {
		var fm = document.frm;

		if (fm.memberId.value == "") {
			alert("아이디를 입력해주세요");
			fm.memberId.focus();
			return;
		}  else if(fm.btn2.value == "N") {
			alert("아이디 중복체크를 해주세요");
			fm.memberId.focus();
		}  else if (fm.memberPw.value == "") {
			alert("비밀번호를 입력해주세요");
			fm.memberPw.focus();
			return;
		} else if (fm.memberPwIsRight.value == "") {
			alert("비밀번호 확인을 입력해주세요.");
			fm.memberPwIsRight.focus();
			return;
		} else if (fm.memberPw.value != fm.memberPwIsRight.value) {
			alert("비밀번호가 일치하지 않습니다.");
			fm.memberPwIsRight.focus();
			return;
		} else if (fm.memberName.value == "") {
			alert("이름을 입력해주세요.");
			fm.memberName.focus();
			return;
		} else if (fm.memberEmail.value == "") {
			alert("이메일을 입력해주세요.");
			fm.memberEmail.focus();
			return;
		} else if (email.test(fm.memberEmail.value) == false) {
			alert("이메일 형식이 올바르지 않습니다.");
			fm.memberEmail.value = "";
			fm.memberEmail.focus();
			return;
		} else if (fm.memberPhone.value == "") {
			alert("연락처를 입력해주세요.");
			fm.memberPhone.focus();
			return;
		} else if (fm.memberBirth.value == "") {
			alert("생년월일을 입력해주세요.");
			fm.memberBirth.focus();
			return;
		} else if (hobbyCheck() == false) {
			alert("취미를 한 개 이상 선택해주세요.");
			return;
		}
		
		var ans = confirm("저장하시겠습니까 ?");
		if(ans == true) {
			// alert("이동할 정보등록할 차례입니다.");
			// html 홈태그 기능을 자바스크립트로 제어
			
			
			// 가상경로를 사용해서 쓸 예정. 가짜경로 형식은 /기능/세부기능.aws
			fm.action="<%=request.getContextPath()%>/member/memberSigninAction.aws";
			fm.method = "post";
			fm.submit();
		}

		return; // 리턴에 값을 쓰지 않으면 그냥 멈춤 종료
	}

	function hobbyCheck() {
		var arr = document.frm.memberHobby; // 문서객체 내부 frm객체 안에 input객체 선언
		var flag = false; // 체크유무 초기값 false 선언

		for (var i = 0; i < arr.length; i++) { // 선택한 여러값을 반복해서 출력
			if (arr[i].checked == true) { // 하나라도 선택했다면
				flag = true; // true값 리턴
				break;
			}
		}

		/* if (flag == false) {
			alert("취미를 한 개 이상 선택해주세요.")
			return false;
		} */

		return flag;
	}
	
	$(document).ready(function() {
		$("#btn2").click(function() {
			// alert("중복체크버튼 클릭");
			
			let memberId = $("#memberId").val();
			if(memberId == "") {
				alert("아이디를 입력해주세요 !");
				return;
			}
			
			$.ajax({
				type : "post", // 전송방식
				url : "<%=request.getContextPath()%>/member/memberIdCheck.aws",
				dataType : "json", // json타입 : 문서에서 {"키값1" : "value값", "키값2" : "value값", ...}
				data : {
					"memberId" : memberId
				},
				success : function(result) { // 결과가 넘어와서 성공했을 때 받는 영역
					// alert("전송성공 테스트 !");
					// alert("길이는 : " + result.length);
					// alert("cnt값은 : " + result.cnt);
					if (result.cnt == 0) {
						alert("사용할 수 있는 아이디입니다.");
						$("#btn2").val("Y");
					} else {
						alert("사용할 수 없는 아이디입니다.");
						$("#memberId").val(""); // 입력한 아이디 지우기
					}
				},
				error : function() { // 결과가 실패했을 때 받는 영역
					alert("전송실패 테스트ㅜ");
				}
			});
		});
	});
</script>
</head>
<body>
    <div class="container">
        <div class="title">
            <h2>회원가입</h2>
        </div>
        <form action="registerAction.aws" method="post">
            <div class="form-group">
                <label for="memberId">아이디</label>
                <input type="text" id="memberId" name="memberId" placeholder="아이디를 입력하세요">
            </div>
            <div class="form-group">
                <label for="password">비밀번호</label>
                <input type="password" id="password" name="memberPw" placeholder="비밀번호를 입력하세요">
            </div>
            <div class="form-group">
                <label for="confirmPassword">비밀번호 확인</label>
                <input type="password" id="confirmPassword" name="memberPwIsRight" placeholder="비밀번호를 다시 입력하세요">
            </div>
            <div class="form-group">
                <label for="name">이름</label>
                <input type="text" id="name" name="memberName" placeholder="이름을 입력하세요">
            </div>
            <div class="form-group">
                <label for="birthDate">생년월일</label>
                <input type="date" id="birthDate" name="memberBirth" required>
            </div>
            <div class="form-group">
                <label>성별</label>
                <label class="gender-label"><input type="radio" name="memberGender" value="male"> 남자</label>
                <label class="gender-label"><input type="radio" name="memberGender" value="female"> 여자</label>
            </div>
            <div class="form-group">
                <label for="address">주소</label>
                <input type="text" id="address" name="address" placeholder="주소를 입력하세요">
            </div>
            <div class="form-group">
                <label for="phone">전화번호</label>
                <input type="tel" id="phone" name="phone" placeholder="전화번호를 입력하세요 (예: 010-1234-5678)">
            </div>
            <div class="form-group">
                <label for="email">이메일</label>
                <input type="email" id="email" name="email" placeholder="이메일을 입력하세요">
            </div>
            <div class="form-actions">
                <button type="submit" class="submit-btn">회원가입</button>
            </div>
            <div class="links">
                <a href="login.jsp">이미 계정이 있으신가요? 로그인</a>
            </div>
        </form>
    </div>
</body>
</HTML>