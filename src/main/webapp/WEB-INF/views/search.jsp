<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%

    String name = request.getParameter("keyword");
    String SERVLET_PATH = request.getAttribute("SERVLET_PATH").toString();

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

    <%-- script import 순서 변경 금지 --%>
    <script>window.SERVLET_PATH = '<%=SERVLET_PATH%>';</script>

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
                            <input class="input-l" type="text" placeholder="검색어를 입력하세요." name="keyword"
                                   value="${keyword}">
                            <button type="submit" class="blue-btn btn-l" id="btn_search">검색</button>
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
                <p class="main-news-list-info">뉴스 검색 결과 <span class="red-txt">${searchResult.rowcount}</span> 건입니다.</p>
                <%-- list 한페이지 10개 --%>
                <ul class="news_list_search">
                    <%--<li class="no-data">검색 결과가 없습니다.<br>검색어를 다시 입력해 주세요.</li>--%>
                    <c:if test="${empty searchResult.result}">
                        <li class="no-data">검색 결과가 없습니다.<br>검색어를 다시 입력해 주세요.</li>
                    </c:if>
                    <c:forEach var="result" items="${searchResult.result}" varStatus="status">
                        <%--                        <button type="button" class="news_title_btn" url="${result.url}">--%>
                        <li>
                            <button type="button" class="news_title_btn" url="${result.url}">
                                <div class="news-title-btn-div">
                                    <p class="main-news-list-tit">${result.nontagTitle}</p>
                                    <p class="main-news-list-txt">${result.nontagDetail}</p>
                                </div>
                                <p class="main-news-list-stxt">
                                    <span>${result.media}</span>
                                    <span>${result.date}</span>
                                    <span>${result.reporter}</span>
                                    <span>${result.email}</span>
                                </p>
                            </button>
                        </li>
                        <%--                        </button>--%>

                    </c:forEach>
                </ul>
                <div class="paging">
                    <div class="pagination flex">
                        <button type="button" class="prev pagination-btn paging-icon" disabled></button>
                        <div class='pagination-wrapper'>
                            <button type="button" class="active pagination-btn" pageindex=1>1</button>
                            <button type="button" class="pagination-btn" pageindex=2>2</button>
                            <button type="button" class="pagination-btn" pageindex=3>3</button>
                            <button type="button" class="pagination-btn" pageindex=4>4</button>
                            <button type="button" class="pagination-btn" pageindex=5>5</button>
                        </div>
                        <button type="button" class="next pagination-btn paging-icon" pageindex=6></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="main-link-btn">
    <a href="/index">메인</a>
</div>

<%-- 활성화 class : show / <div class="modal modal-news show">--%>
<div class="modal modal-news">
    <div class="modal-body">
        <button type="button" class="modal-news-close">
            <img src="../../public/images/modal_news_close.svg">
        </button>
        <div class="modal-news-wrap">
            <div class="modal-news-header">
                <p class="modal-news-logo">
                    <%--                    <img src="../../public/images/logo_ytn.png">--%>
                </p>
                <p class="modal-news-tit"><span id="modal_title"></span></p>
                <div class="modal-news-info">
                    <div class="modal-news-info-txt">
                        <p><span id="modal_date"></span></p>
                        <p><span id="modal_media"></span></p>
                        <p><span id="modal_email"></span></p>
                    </div>
                    <div class="modal-news-info-btn">
                        <button type="button" class="btn-xs gray-line-btn">기사원문</button>
                        <button type="button" class="btn-xs gray-line-btn">스크랩</button>
                        <button type="button" class="btn-xs gray-line-btn">뉴스 듣기</button>
                    </div>
                </div>
            </div>
            <div class="modal-news-cont">
                <div class="modal-news-cont-img">
                    <img src="../../public/images/sample_modal_news_cont_img.jpg">
                    <p>[이미지출처=보도화면 캡처]</p>
                </div>
                <div class="modal-news-cont-txt">
                    <p><span id="modal_detail"></span></p>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/js/newsController.js"></script>
</body>

</html>