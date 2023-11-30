<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
</head>

<body>
<div class="wrap">
    <div class="main-issue">
        <div class="layout">
            <p class="main-tit">오늘의 이슈</p>
            <div class="main-issue-cont">
                <div class="main-issue-img-cont">
                    <button type="button">
                        <div class="main-issue-img">
                            <img src="../../public/images/main_issue_img.jpg">
                        </div>
                        <p class="main-issue-img-tit">"재난지원금 환수 면제‥보상금 전액 지급"</p>
                    </button>
                    <a href="" class="txt-btn">전체 뉴스보기</a>
                </div>
                <div class="main-issue-list-cont">
                    <ul>
                        <c:forEach var="newsList" items="${newsDataList}" varStatus="status" end="7">
                            <li>
                                <button type="button" class="news_title_btn" _id_no="${newsList.newsNo}">
                                    <p class="main-issue-list-txt1" style="z-index: -1">${newsList.nontagTitle}</p>
                                    <p class="main-issue-list-txt2" style="z-index: -1">${newsList.media}</p>
                                </button>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="main-keyword">
        <div class="layout">
            <p class="main-tit">오늘의 키워드</p>
            <div class="main-keyword-cont">
                <div class="main-keyword-legend">
                    <p class="legend legend1">
                        <span class="legend-color"></span>
                        <span class="legend-txt">Text1</span>
                    </p>
                    <p class="legend legend2">
                        <span class="legend-color"></span>
                        <span class="legend-txt">Text2</span>
                    </p>
                    <p class="legend legend3">
                        <span class="legend-color"></span>
                        <span class="legend-txt">Text3</span>
                    </p>
                </div>
                <%--키워드 들어가는 공간--%>
                <div class="main-keyword-txt"></div>
            </div>
        </div>
    </div>
</div>

<%--<script>--%>
<%--    $().ready(function () {--%>
<%--        $(".news_title_btn").on('click', function (e) {--%>
<%--            console.log(e.target.attr("_id_no"));--%>
<%--        });--%>
<%--    });--%>
<%--</script>--%>
<script src="/js/newsController.js"></script>
</body>

</html>