<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<HTML>
<HEAD>
<TITLE>Signin</TITLE>

<link href="/resources/css/style.css" type="text/css" rel="stylesheet">
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

</HEAD>

<BODY>
	<header>
		<a href="./memberSignin.jsp">회원가입 페이지</a>
	</header>
	<nav>
		<a href="./memberLogin.jsp" style="text-decoration: none;'">회원로그인
			가기</a>
	</nav>
	<form name="frm">
		<table border="1">
			<tr>
				<th class="idcolor">아이디</th>
				<td><input type="text" id = "memberId" name="memberId" maxlength="50"
					style="width: 100px;">
					<button type="button" name="btn2" id="btn2" value="N">아이디중복체크</button></td>
			</tr>
			<tr>
				<th class="idcolor">비밀번호</th>
				<td><input type="password" name="memberPw" maxlength="50"
					style="width: 50px;"></td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td><input type="password" name="memberPwIsRight"
					maxlength="50" style="width: 50px;"></td>
			</tr>
			<tr>
				<th id="name">이름</th>
				<td><input type="text" name="memberName" maxlength="50"
					style="width: 100px;"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="email" name="memberEmail" maxlength="50"
					style="width: 150px;"></td>
			</tr>
			<tr>
				<th>연락처</th>
				<td><input type="number" name="memberPhone" maxlength="50"
					style="width: 150px;"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><select name="memberAddress" style="width: 100px;">
						<option value="서울">서울</option>
						<option value="대전" selected>대전</option>
						<option value="부산">부산</option>
						<option value="인천">인천</option>
						<option value="광주">광주</option>
				</select></td>
			</tr>
			<tr>
				<th>성별</th>
				<td><input type="radio" name="memberGender" id="select1"
					value="M"><label for="select1">남성</label> <input
					type="radio" name="memberGender" checked id="select2" value="F"
					checked><label for="select2">여성</label></td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td><input type="number" name="memberBirth" maxlength="50"
					style="width: 100px;">예)20240920</td>
			</tr>
			<tr>
				<th>취미</th>
				<td><input type="checkbox" name="memberHobby" id="check1"
					value="야구"><label for="check1">야구</label> <input
					type="checkbox" name="memberHobby" id="check2" value="농구"><label
					for="check2">농구</label> <input type="checkbox" name="memberHobby"
					id="check3" value="축구"><label for="check3">축구</label></td>
			</tr>

			<tr>
				<td colspan=2 style="text-align: center;">
					<button type="button" onclick="check();">저장하기</button>
				</td>
				<!-- <input type="submit" name="btn" value="회원정보 저장하기">
					<input type="reset" name="btn" value="초기화"></td> -->
			</tr>
		</table>
	</form>
</BODY>
</HTML>
