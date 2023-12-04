<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%

    String name = request.getParameter("keyword");
    System.out.println(name);

%>

<!DOCTYPE html>

<html lang="ko">

<head>
    <meta charset="UTF-8"/>
    <title>Ncloud</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="icon" href="../../public/images/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="../../public/images/favicon.ico">
    <link rel="stylesheet" href="../../public/css/reset.css">
    <link rel="stylesheet" href="../../public/css/common.css">
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>

    <%-- module --%>
    <script src="/sangwon/sangwon_module.js"></script>
</head>

<body>
<div class="wrap">
    <div class="main-news-search">
        <div class="layout">
            <p class="main-tit">뉴스 검색</p>
            <div class="main-news-search-cont">
                <form action="/search" method="get" id="selectInput">
                    <div class="input-group">
                        <div>
                            <select name="searchType" class="select-m">
                                <option value="content">기사 내용</option>
                                <option value="title">기사 제목</option>
                                <option value="reporter">기자 이름</option>
                            </select>
                        </div>
                        <div class="input-search">
                            <input class="input-l" type="text" placeholder="검색어를 입력하세요." name="keyword">
                            <button type="submit" class="blue-btn btn-l">검색</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="main-news-list">
        <div class="layout">
            <p class="main-tit">검색 결과</p>
            <div class="main-news-list-cont">
                <p class="main-news-list-info">뉴스 검색 결과 <span class="red-txt">192,168</span> 건입니다.</p>
                <%-- list 한페이지 10개 --%>
                <ul>
                    <%--<li class="no-data">검색 결과가 없습니다.<br>검색어를 다시 입력해 주세요.</li>--%>
                    <c:forEach var="result" items="${searchResult}" varStatus="status">
                        <li>
                            <a href="">
                                <div>
                                    <p class="main-news-list-tit">${result.nontagTitle}</p>
                                    <p class="main-news-list-txt">${result.nontagDetail}</p>
                                </div>
                                <p class="main-news-list-stxt">
                                    <span>${result.media}</span>
                                    <span>${result.date}</span>
                                    <span>${result.reporter}</span>
                                    <span>${result.email}</span>
                                </p>
                            </a>
                        </li>
                    </c:forEach>
                    <li>
                </ul>
                <div class="paging">
                    <div class="pagination">
                        <%--                        <a href="#" class="first-prev paging-icon"></a>--%>
                        <%--                        <a href="#" class="prev paging-icon"></a>--%>
                        <%--                        <a href="#" class="active">1</a>--%>
                        <%--                        <a href="#">2</a>--%>
                        <%--                        <a href="#">3</a>--%>
                        <%--                        <a href="#">4</a>--%>
                        <%--                        <a href="#">5</a>--%>
                        <%--                        <a href="#" class="next paging-icon"></a>--%>
                        <%--                        <a href="#" class="end-next paging-icon"></a>--%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="main-link-btn">
    <a href="/index">메인</a>
</div>
<script src="/js/newsController.js"></script>
</body>

</html>