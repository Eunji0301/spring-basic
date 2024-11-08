<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.myaws.myapp.domain.*"%>
<%
BoardVo bv = (BoardVo) request.getAttribute("bv"); // 강제형변환 양쪽형을 맞춰준다.

String memberName = "";
if (session.getAttribute("memberName") != null) {
	memberName = (String) session.getAttribute("memberName");
}

int midx = 0;
if (session.getAttribute("midx") != null) {
	midx = Integer.parseInt(session.getAttribute("midx").toString());
}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>게시글 보기</title>
<style>
body {
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
}

.container {
	width: 800px;
	margin: 50px auto;
	padding: 20px;
	background-color: #fff;
	border-radius: 10px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.title_view {
	margin-bottom: 20px;
}

.title_view p {
	font-size: 18px;
	font-weight: bold;
	color: #333;
	margin: 0;
	padding: 10px 0;
	border-bottom: 2px solid #ff7a00;
}

.title_view div {
	font-size: 16px;
	color: #555;
	margin: 10px 0;
}

.content {
	margin: 20px 0;
	padding: 20px;
	background-color: #f9f9f9;
	border: 1px solid #ddd;
	line-height: 1.6;
	font-size: 16px;
}

.btn-group {
	margin-top: 20px;
	text-align: right;
}

.btn-group button {
	background-color: #333;
	color: #fff;
	border: none;
	padding: 10px 20px;
	margin-left: 5px;
	border-radius: 5px;
	cursor: pointer;
	font-size: 14px;
}

.btn-group button:hover {
	background-color: #ff7a00;
}

.comment-section {
	margin-top: 40px;
	padding: 20px;
	border: 1px solid #ddd;
	border-radius: 5px;
	background-color: #fff;
}

.comment-section h3 {
	font-size: 18px;
	margin-bottom: 20px;
}

.comment-box {
	margin-top: 20px;
	border: 1px solid #ddd;
	border-radius: 5px;
	padding: 15px;
	background-color: #f9f9f9;
}

.comment-box textarea {
	width: 100%;
	height: 100px;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	resize: none;
	font-size: 14px;
	background-color: #fff;
	margin-bottom: 10px;
}

.comment-box button {
	margin-top: 10px;
	padding: 10px 20px;
	background-color: #333;
	color: #fff;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.comment-box button:hover {
	background-color: #ff7a00;
}

.comment-list {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
	border: none;
}

.comment-list th, .comment-list td {
	padding: 8px;
	text-align: center;
}

.comment-list th {
	background-color: #f2f2f2;
}
</style>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
function checkImageType(fileName) {
	var pattern = /jpg$|gif$|png$|jpeg$/i; // 자바스크립트의 정규표현식
	
	return fileName.match(pattern);
}

function getOriginalFileName(fileName) { // 원본파일이름 추출
	var idx = fileName.lastIndexOf("_") + 1;
	return fileName.substr(idx);
}

function getImageLink(fileName) {
	var front = fileName.substr(0,12);
	var end = fileName.substr(14);
	
	return front + end;
}

function download() {
	var downloadImage = getImageLink("<%=bv.getFilename()%>"); // 주소 사이에 s-는 빼고
	var downLink = "<%=request.getContextPath()%>/board/displayFile.aws?fileName=" + downloadImage + "&down=1";
	
	return downLink;
}

 $(document).ready(function() {
	 
	$("#dUrl").html(getOriginalFileName("<%=bv.getFilename()%>"));
	
	$("#dUrl").click(function(event) {
		$("#dUrl").attr("href", download());
		return;
	});
	
	$("#btn").click(function() {
		$.ajax({
			type: "get",
			url: "<%=request.getContextPath()%>/board/boardRecom.aws?bidx=<%=bv.getBidx()%>",
			dataType: "json",
			success: function(result) {
				 alert(result.recom);
				var str = "추천(" + result.recom + ")";
				$("#btn").val(str);
				$("#btn").html(str);
			},
			error: function() {
				alert("전송실패 테스트ㅜ");
			}
		});

	});

	<%-- $("#cmtBtn").click(function() {
		let loginCheck = "<%=session.getAttribute("midx") != null ? session.getAttribute("midx") : ""%>";
		if (loginCheck === "") {
			alert("로그인을 해주세요");
			return;
		}

		let cwriter = $("#cwriter").val();
		let ccontents = $("#ccontents").val();

		if (cwriter == "") {
			alert("작성자를 입력해주세요");
			$("#cwriter").focus();
			return;
		} else if (ccontents == "") {
			alert("내용을 입력해주세요");
			$("#ccontents").focus();
			return;
		}

		$.ajax({
			type: "post",
			url: "<%=request.getContextPath()%>/comment/commentWriteAction.aws",
			data: {
				"cwriter": cwriter,
				"ccontents": ccontents,
				"bidx": "<%=bv.getBidx()%>",
				"midx": "<%=session.getAttribute("midx")%>"
			},
			dataType: "json",
			success: function(result) {
				if (result.success) {
					alert("댓글이 성공적으로 추가되었습니다!");

					var newCommentRow = "<tr>"
						+ "<td>" + result.cidx + "</td>"
						+ "<td>" + result.cwriter + "</td>"
						+ "<td>" + result.ccontents + "</td>"
						+ "<td>" + result.writeday + "</td>"
						+ "<td><button onclick='deleteComment(this, " + result.cidx + ")'>삭제</button></td>"
						+ "</tr>";

					$("#commentList").append(newCommentRow);

					$("#ccontents").val("");
				} else {
					alert("댓글 추가에 실패했습니다.");
				}
			},
			error: function() {
				alert("댓글 추가에 실패했습니다.");
			}
		});
	}); --%>
});

<%-- //댓글 삭제 함수
function deleteComment(button, cidx) {
	let ans = confirm("이 댓글을 삭제하시겠습니까 ?");
	
	if(ans == true) {
		$.ajax({
            type: "get",
            url: "<%=request.getContextPath()%>/comment/commentDelete.aws?cidx="+ cidx,
			dataType : "json",
			success : function(result) {
				alert("댓글이 삭제되었습니다.");
				$.boardCommentList();
			},
			error : function() {
				alert("댓글 삭제 요청에 실패했습니다.");
			}
		});
	}
} --%>
</script>
</head>
<body>
	<div class="container">
		<div class="title_view">
			<p><%=bv.getSubject()%>&nbsp;(조회수 : <%=bv.getViewcnt()%>)
			</p>
			<div><%=bv.getWriter()%>&nbsp;(<%=bv.getWriteday()%>)
			</div>
		</div>
		<div class="content"><%=bv.getContents()%></div>
		<%
		if (bv.getFilename() != null) {
		%>
		<img src="<%=request.getContextPath()%>/board/displayFile.aws?fileName=<%=bv.getFilename()%>">
		<p>
			<a id="dUrl" href="" class="fileDown">첨부파일 다운로드</a>
		</p>
		<%
		}
		%>
		<div class="btn-group">
			<button type="button" id="btn" value="추천(<%=bv.getRecom()%>)">추천(<%=bv.getRecom()%>)</button>
			<button type="button" onclick="location.href='<%=request.getContextPath()%>/board/boardModify.aws?bidx=<%=bv.getBidx()%>'">수정</button>
			<button type="button" onclick="location.href='<%=request.getContextPath()%>/board/boardDelete.aws?bidx=<%=bv.getBidx()%>'">삭제</button>
			<button type="button" onclick="location.href='<%=request.getContextPath()%>/board/boardReply.aws?bidx=<%=bv.getBidx()%>'">답변</button>
			<button type="button" onclick="location.href='<%=request.getContextPath()%>/board/boardList.aws?bidx=<%=bv.getBidx()%>'">목록</button>
		</div>

		<div class="comment-section">
			<h3>댓글</h3>
			<div class="comment-box">
				<p>
					<input type="text" id="cwriter" name="cwriter" value="<%=session.getAttribute("mname")%>" readonly="readonly">
				</p>
				<textarea id="ccontents" placeholder="댓글을 입력하세요"></textarea>
				<button id="cmtBtn">댓글 작성</button>
			</div>
			<table class="comment-list">
				<thead>
					<tr>
						<th>번호</th>
						<th>작성자</th>
						<th>내용</th>
						<th>날짜</th>
						<th>DEL</th>
					</tr>
				</thead>
				<tbody id="commentList">
					<tr>
						<td>1</td>
						<td>사용자1</td>
						<td>댓글 내용입니다.</td>
						<td>2024-10-29 12:00</td>
						<td><button onclick="deleteComment(this, 1)">삭제</button></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
