<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.myaws.myapp.domain.*"%>
<%
ArrayList<BoardVo> blist = (ArrayList<BoardVo>) request.getAttribute("blist");
PageMaker pm = (PageMaker) request.getAttribute("pm");
int totalCount = pm.getTotalCount();

String keyword = pm.getScri().getKeyword();
String search = pm.getScri().getSearch();
String param = "keyword=" + keyword + "&search=" + search;

// 현재 페이지 값
String currentPageParam = request.getParameter("page");
int currentPage = (currentPageParam == null) ? 1 : Integer.parseInt(currentPageParam);
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>글목록</title>
    <style>
        body {
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        
        .container {
            width: 800px;
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        
        h3 {
            border-bottom: 2px solid #ff7a00;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
        }
        
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        
        th {
            background-color: #f2f2f2;
            text-align: center;
        }
        
        .list {
            display: flex;
            justify-content: flex-end;
            margin-bottom: 20px;
        }
        
        .pagination {
            display: flex;
            justify-content: center;
            margin-top: 20px;
            margin-bottom: 20px;
        }
        
        .pagination a {
            margin: 0 5px;
            text-decoration: none;
            color: #333;
            border: 1px solid #ddd;
            padding: 5px 10px;
            border-radius: 4px;
        }
        
        .pagination a:hover {
            background-color: #ff7a00;
            color: #fff;
        }
        
        .pagination .current {
            font-weight: bold;
            color: #fff;
            background-color: #000;
            border-radius: 5px;
            padding: 5px 10px;
        }
        
        select, input[type="text"], button {
            padding: 5px;
            margin-right: 5px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        
        button {
            background-color: #333;
            color: #fff;
            cursor: pointer;
        }
        
        button:hover {
            background-color: #ff7a00;
        }
        
        a {
            text-decoration: none;
            color: #333;
        }
        
        a:hover {
            color: #ff7a00;
        }
        
        .write-button {
            margin-top: 20px;
            text-align: right;
        }
    </style>
</head>
<body>
    <div class="container">
        <h3>글목록</h3>
        <div class="list">
            <form name="frm" action="<%=request.getContextPath()%>/board/boardList.aws" method="get">
                <select name="search">
                    <option value="subject">제목</option>
                    <option value="writer">작성자</option>
                </select> 
                <input type="text" name="keyword" placeholder="검색어를 입력하세요">
                <button type="submit">검색</button>
            </form>
        </div>
        <table>
            <thead>
                <tr>
                    <th>No</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>조회</th>
                    <th>추천</th>
                    <th>날짜</th>
                </tr>
            </thead>
            <tbody>
                <%
                int num = totalCount - (pm.getScri().getPage() - 1) * pm.getScri().getPerPageNum();
                for (BoardVo bv : blist) {
                %>
                <tr>
                    <td style="text-align: center;"><%=bv.getBidx()%></td>
                    <td style="text-align: left; padding-left: <%= bv.getLevel_() * 20 %>px;">
                        <a href="<%=request.getContextPath()%>/board/boardContent.aws?bidx=<%=bv.getBidx()%>"><%=bv.getSubject()%></a>
                    </td>
                    
                    <td style="text-align: center;"><%=bv.getWriter()%></td>
                    <td style="text-align: center;"><%=bv.getViewcnt()%></td>
                    <td style="text-align: center;"><%=bv.getRecom()%></td>
                    <td style="text-align: center;"><%=bv.getWriteday()%></td>
                </tr>
                <%
                }
                %>
            </tbody>
        </table>
        <div class="write-button">
            <button type="button"
                onclick="location.href='<%=request.getContextPath()%>/board/boardWrite.aws'">글쓰기</button>
        </div>
        <div>
            <div class="pagination">
                <%
                if (pm.isPrev()) {
                %>
                <a href="<%=request.getContextPath()%>/board/boardList.aws?page=<%=pm.getStartPage() - 1%>&<%=param%>">◀</a>
                <%
                }
                for (int i = pm.getStartPage(); i <= pm.getEndPage(); i++) {
                if (i == currentPage) {
                %>
                <a href="?page=<%=i%>" class="current"><%=i%></a>
                <%
                } else {
                %>
                <a href="?page=<%=i%>"><%=i%></a>
                <%
                }
                }
                if (pm.isNext() && pm.getEndPage() > 0) {
                %>
                <a href="<%=request.getContextPath()%>/board/boardList.aws?page=<%=pm.getEndPage() + 1%>&<%=param%>">▶</a>
                <%
                }
                %>
            </div>
        </div>
    </div>
</body>
</html>