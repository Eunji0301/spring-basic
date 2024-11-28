<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.myaws.myapp.domain.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>게시글 보기</title>
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
  font-family: GangwonEduPowerExtraBoldA;
} 
 
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
	color: black;
	margin: 0;
	padding: 10px 0;
	border-bottom: 2px solid #007bff;
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
	background-color: #007bff;
	color: #fff;
	border: none;
	padding: 10px 20px;
	margin-left: 5px;
	border-radius: 5px;
	cursor: pointer;
	font-size: 14px;
	transition: background-color 0.3s ease;
}

.btn-group button:hover {
	background-color: #0056b3;
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
	color: black;
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
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.comment-box button:hover {
	background-color: #0056b3;
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
	background-color: #007bff;
	color: #fff;
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
	var downloadImage = getImageLink("${bv.filename}"); // 주소 사이에 s-는 빼고
	var downLink = "${pageContext.request.contextPath}/board/displayFile.aws?fileName=" + downloadImage + "&down=1";
	
	return downLink;
}

 $(document).ready(function() {
	 
	$.boardCommentList();
	 
	$("#dUrl").html(getOriginalFileName("${bv.filename}"));
	
	$("#dUrl").click(function(event) {
		$("#dUrl").attr("href", download());
		return;
	});
	
	$("#btn").click(function() {
		$.ajax({
			type: "get",
			url: "${pageContext.request.contextPath}/board/boardRecom.aws?bidx=${bv.bidx}",
			dataType: "json",
			success: function(result) {
				var str = "추천(" + result.recom + ")";
				$("#btn").val(str);
				$("#btn").html(str);
			},
			error: function() {
				alert("전송실패 테스트ㅜ");
			}
		});

	});

	 $("#cmtBtn").click(function() {
		let midx = "${midx}";
			//alert(loginCheck);
			if (midx == "" || midx == "null" || midx == null || midx == 0){
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
				"bidx": "${bv.bidx}",
				"midx": "${midx}"
			},
			dataType: "json",
			success: function(result) {
				if(result.value == 1) {
					alert("댓글 추가에 성공했습니다.");
					$("#ccontents").val("");
					$("#block").val(1);
				}
				$.boardCommentList();
			},
			error: function() {
				alert("댓글 추가에 실패했습니다.");
			}
		});
	});
	 
	$("#more").click(function(){
		$.boardCommentList();
	});
});

//댓글 삭제 함수
function commentDel(cidx){	
	let ans = confirm("삭제하시겠습니까?");	
	
	if (ans == true){
		$.ajax({
			type :  "get",    //전송방식
			url : "${pageContext.request.contextPath}/comment/" + cidx + "/commentDeleteAction.aws",
			dataType : "json",       // json타입은 문서에서  {"키값" : "value값","키값2":"value값2"}
			success : function(result){   //결과가 넘어와서 성공했을 받는 영역
				alert("댓글이 삭제되었습니다.");	
				$.boardCommentList();						
			},
			error : function(){  //결과가 실패했을때 받는 영역						
				alert("댓글 삭제 요청에 실패했습니다.");
			}			
		});			
	}	
	return;
}

//jquery로 만드는 함수  ready밖에 생성
$.boardCommentList = function(){
	alert("test")
	let block = $("#block").val();
	alert("block"+block);
	
	$.ajax({
		type :  "get",    //전송방식
		url : "${pageContext.request.contextPath}/comment/${bv.bidx}/"+block+"/commentList.aws",
		dataType : "json",       // json타입은 문서에서  {"키값" : "value값","키값2":"value값2"}
		success : function(result){   //결과가 넘어와서 성공했을 받는 영역
			alert("전송성공 테스트");			
			var strTr = "";				
			$(result.clist).each(function(){	
				
				var btnn="";			
				 //현재로그인 사람과 댓글쓴 사람의 번호가 같을때만 나타내준다
				if (this.midx == "${midx}") {
					if (this.delyn=="N"){
						btnn= "<button type='button' onclick='commentDel("+this.cidx+");'>삭제</button>";
					}			
				}
				strTr = strTr + "<tr>"
				+"<td>"+this.cidx+"</td>"
				+"<td>"+this.cwriter+"</td>"
				+"<td class='content'>"+this.ccontents+"</td>"
				+"<td>"+this.writeday+"</td>"
				+"<td>"+btnn+"</td>"
				+"</tr>";					
			});		       
			
			var str  = "<table class='replyTable'>"
				+"<tr>"
				+"<th>번호</th>"
				+"<th>작성자</th>"
				+"<th>내용</th>"
				+"<th>날짜</th>"
				+"<th>DEL</th>"
				+"</tr>"+strTr+"</table>";		
			
			$("#commentListView").html(str);		
			
			if(result.moreView == "N") {
				$("#morebtn").css("display","none");
			} else {
				$("#morebtn").css("display","block");
			}
			
			alert(result.nextBlock);
			$("#block").val(result.nextBlock);

			
			},
		error : function(){  //결과가 실패했을때 받는 영역						
			alert("전송실패");
		}			
	});
}
</script>
</head>
<body>
	<div class="container">
		<div class="title_view">
			<p>${bv.subject }&nbsp;(조회수 : ${bv.viewcnt })
			</p>
			<div>${bv.writer }&nbsp;(${bv.writeday })
			</div>
		</div>
		<div class="content">${bv.contents }</div>
		<c:if test="${not empty bv.filename}">
    		<img src="${pageContext.request.contextPath}/board/displayFile.aws?fileName=${bv.filename}" alt="첨부파일 이미지">
    		<p>
        		<a id="dUrl" href="${pageContext.request.contextPath}/board/displayFile.aws?fileName=${bv.filename}&down=1" class="fileDown">첨부파일 다운로드</a>
    		</p>
		</c:if>

		<div class="btn-group">
			<button type="button" id="btn" value="추천(${bv.recom})">추천(${bv.recom})</button>
			<button type="button" onclick="location.href='<%=request.getContextPath()%>/board/boardModify.aws?bidx=${bv.bidx}'">수정</button>
			<button type="button" onclick="location.href='<%=request.getContextPath()%>/board/boardDelete.aws?bidx=${bv.bidx}'">삭제</button>
			<button type="button" onclick="location.href='<%=request.getContextPath()%>/board/boardReply.aws?bidx=${bv.bidx}'">답변</button>
			<button type="button" onclick="location.href='<%=request.getContextPath()%>/board/boardList.aws?bidx=${bv.bidx}'">목록</button>
		</div>

		<div class="comment-section">
			<h3>댓글</h3>
			<div class="comment-box">
				<p>
					<input type="text" id="cwriter" name="cwriter" value="${memberName}" readonly="readonly">
				</p>
				<textarea id="ccontents" placeholder="댓글을 입력하세요"></textarea>
				<button id="cmtBtn">댓글 작성</button>
			</div>
			<table class="comment-list">
			
				<tbody id="commentListView">
					
				</tbody>
			</table>
			
			<input type="hidden" id="block" value="1">
			<div id="morebtn" style="text-align: center; line-height: 50px;">
				<button type="button" id="more">더보기</button>
			</div>
		</div>
	</div>
</body>
</html>
