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
</head>

<body>
<div class="wrap">
    <div class="main-news-search">
        <div class="layout">
            <p class="main-tit">뉴스 검색</p>
            <div class="main-news-search-cont">
                <form>
                    <div class="input-search">
                        <input class="input-l" type="text" placeholder="검색어를 입력하세요.">
                        <button type="submit" class="blue-btn btn-l">검색</button>
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
                    <li>
                        <a href="">
                            <div>
                                <p class="main-news-list-tit">브라질 주지사 “아마존, 이름 사용 값 내야”</p>
                                <p class="main-news-list-txt">브라질 아마존 열대우림을 담당하는 지자체 가운데 하나인 아마조나스주가 글로벌 전자상거래 업체 아마존에
                                    ‘이름 사용 값’을 요구하겠다는 뜻을 지난달 30일(현지시간) 밝혔다.</p>
                            </div>
                            <p class="main-news-list-stxt">
                                <span>경향신문</span>
                                <span>2023-11-29</span>
                                <span>김엔클 기자</span>
                                <span>ncloud@head.co.kr</span>
                            </p>
                        </a>
                    </li>
                    <li>
                        <a href="">
                            <div>
                                <p class="main-news-list-tit">브라질 주지사 “아마존, 이름 사용 값 내야”</p>
                                <p class="main-news-list-txt">브라질 아마존 열대우림을 담당하는 지자체 가운데 하나인 아마조나스주가 글로벌 전자상거래 업체 아마존에
                                    ‘이름 사용 값’을 요구하겠다는 뜻을 지난달 30일(현지시간) 밝혔다.</p>
                            </div>
                            <p class="main-news-list-stxt">
                                <span>경향신문</span>
                                <span>2023-11-29</span>
                                <span>김엔클 기자</span>
                                <span>ncloud@head.co.kr</span>
                            </p>
                        </a>
                    </li>
                    <li>
                        <a href="">
                            <div>
                                <p class="main-news-list-tit">브라질 주지사 “아마존, 이름 사용 값 내야”</p>
                                <p class="main-news-list-txt">브라질 아마존 열대우림을 담당하는 지자체 가운데 하나인 아마조나스주가 글로벌 전자상거래 업체 아마존에
                                    ‘이름 사용 값’을 요구하겠다는 뜻을 지난달 30일(현지시간) 밝혔다.</p>
                            </div>
                            <p class="main-news-list-stxt">
                                <span>경향신문</span>
                                <span>2023-11-29</span>
                                <span>김엔클 기자</span>
                                <span>ncloud@head.co.kr</span>
                            </p>
                        </a>
                    </li>
                    <li>
                        <a href="">
                            <div>
                                <p class="main-news-list-tit">브라질 주지사 “아마존, 이름 사용 값 내야”</p>
                                <p class="main-news-list-txt">브라질 아마존 열대우림을 담당하는 지자체 가운데 하나인 아마조나스주가 글로벌 전자상거래 업체 아마존에
                                    ‘이름 사용 값’을 요구하겠다는 뜻을 지난달 30일(현지시간) 밝혔다.</p>
                            </div>
                            <p class="main-news-list-stxt">
                                <span>경향신문</span>
                                <span>2023-11-29</span>
                                <span>김엔클 기자</span>
                                <span>ncloud@head.co.kr</span>
                            </p>
                        </a>
                    </li>
                    <li>
                        <a href="">
                            <div>
                                <p class="main-news-list-tit">브라질 주지사 “아마존, 이름 사용 값 내야”</p>
                                <p class="main-news-list-txt">브라질 아마존 열대우림을 담당하는 지자체 가운데 하나인 아마조나스주가 글로벌 전자상거래 업체 아마존에
                                    ‘이름 사용 값’을 요구하겠다는 뜻을 지난달 30일(현지시간) 밝혔다.</p>
                            </div>
                            <p class="main-news-list-stxt">
                                <span>경향신문</span>
                                <span>2023-11-29</span>
                                <span>김엔클 기자</span>
                                <span>ncloud@head.co.kr</span>
                            </p>
                        </a>
                    </li>
                    <li>
                        <a href="">
                            <div>
                                <p class="main-news-list-tit">브라질 주지사 “아마존, 이름 사용 값 내야”</p>
                                <p class="main-news-list-txt">브라질 아마존 열대우림을 담당하는 지자체 가운데 하나인 아마조나스주가 글로벌 전자상거래 업체 아마존에
                                    ‘이름 사용 값’을 요구하겠다는 뜻을 지난달 30일(현지시간) 밝혔다.</p>
                            </div>
                            <p class="main-news-list-stxt">
                                <span>경향신문</span>
                                <span>2023-11-29</span>
                                <span>김엔클 기자</span>
                                <span>ncloud@head.co.kr</span>
                            </p>
                        </a>
                    </li>
                    <li>
                        <a href="">
                            <div>
                                <p class="main-news-list-tit">브라질 주지사 “아마존, 이름 사용 값 내야”</p>
                                <p class="main-news-list-txt">브라질 아마존 열대우림을 담당하는 지자체 가운데 하나인 아마조나스주가 글로벌 전자상거래 업체 아마존에
                                    ‘이름 사용 값’을 요구하겠다는 뜻을 지난달 30일(현지시간) 밝혔다.</p>
                            </div>
                            <p class="main-news-list-stxt">
                                <span>경향신문</span>
                                <span>2023-11-29</span>
                                <span>김엔클 기자</span>
                                <span>ncloud@head.co.kr</span>
                            </p>
                        </a>
                    </li>
                    <li>
                        <a href="">
                            <div>
                                <p class="main-news-list-tit">브라질 주지사 “아마존, 이름 사용 값 내야”</p>
                                <p class="main-news-list-txt">브라질 아마존 열대우림을 담당하는 지자체 가운데 하나인 아마조나스주가 글로벌 전자상거래 업체 아마존에
                                    ‘이름 사용 값’을 요구하겠다는 뜻을 지난달 30일(현지시간) 밝혔다.</p>
                            </div>
                            <p class="main-news-list-stxt">
                                <span>경향신문</span>
                                <span>2023-11-29</span>
                                <span>김엔클 기자</span>
                                <span>ncloud@head.co.kr</span>
                            </p>
                        </a>
                    </li>
                    <li>
                        <a href="">
                            <div>
                                <p class="main-news-list-tit">브라질 주지사 “아마존, 이름 사용 값 내야”</p>
                                <p class="main-news-list-txt">브라질 아마존 열대우림을 담당하는 지자체 가운데 하나인 아마조나스주가 글로벌 전자상거래 업체 아마존에
                                    ‘이름 사용 값’을 요구하겠다는 뜻을 지난달 30일(현지시간) 밝혔다.</p>
                            </div>
                            <p class="main-news-list-stxt">
                                <span>경향신문</span>
                                <span>2023-11-29</span>
                                <span>김엔클 기자</span>
                                <span>ncloud@head.co.kr</span>
                            </p>
                        </a>
                    </li>
                    <li>
                        <a href="">
                            <div>
                                <p class="main-news-list-tit">브라질 주지사 “아마존, 이름 사용 값 내야”</p>
                                <p class="main-news-list-txt">브라질 아마존 열대우림을 담당하는 지자체 가운데 하나인 아마조나스주가 글로벌 전자상거래 업체 아마존에
                                    ‘이름 사용 값’을 요구하겠다는 뜻을 지난달 30일(현지시간) 밝혔다.</p>
                            </div>
                            <p class="main-news-list-stxt">
                                <span>경향신문</span>
                                <span>2023-11-29</span>
                                <span>김엔클 기자</span>
                                <span>ncloud@head.co.kr</span>
                            </p>
                        </a>
                    </li>
                </ul>
                <div class="paging">
                    <div class="pagination">
                        <a href="#" class="first-prev paging-icon"></a>
                        <a href="#" class="prev paging-icon"></a>
                        <a href="#" class="active">1</a>
                        <a href="#">2</a>
                        <a href="#">3</a>
                        <a href="#">4</a>
                        <a href="#">5</a>
                        <a href="#" class="next paging-icon"></a>
                        <a href="#" class="end-next paging-icon"></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

</html>