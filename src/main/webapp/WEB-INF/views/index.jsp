<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html lang="ko">

<%

    String SERVER_SCHEME = request.getScheme();
    String SERVER_NAME = request.getServerName();
    String SERVER_PORT = String.valueOf(request.getServerPort());
	String CONTEXT_PATH = request.getContextPath();

%>

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
    <script>window.SERVER_SCHEME = '<%=SERVER_SCHEME%>';window.SERVER_NAME = '<%=SERVER_NAME%>';window.SERVER_PORT = '<%=SERVER_PORT%>';window.CONTEXT_PATH = '<%=CONTEXT_PATH%>';</script>

    <script src="https://code.jquery.com/jquery-latest.min.js"></script>

    <script src="https://www.amcharts.com/lib/4/core.js"></script>
    <script src="https://www.amcharts.com/lib/4/charts.js"></script>
    <script src="https://www.amcharts.com/lib/4/plugins/wordCloud.js"></script>
    <script src="https://www.amcharts.com/lib/4/themes/animated.js"></script>

    <%-- module --%>
    <script src="/sangwon/sangwon_module.js"></script>
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
                <%--                <div class="main-keyword-legend">--%>
                <%--                    <p class="legend legend1">--%>
                <%--                        <span class="legend-color"></span>--%>
                <%--                        <span class="legend-txt">Text1</span>--%>
                <%--                    </p>--%>
                <%--                    <p class="legend legend2">--%>
                <%--                        <span class="legend-color"></span>--%>
                <%--                        <span class="legend-txt">Text2</span>--%>
                <%--                    </p>--%>
                <%--                    <p class="legend legend3">--%>
                <%--                        <span class="legend-color"></span>--%>
                <%--                        <span class="legend-txt">Text3</span>--%>
                <%--                    </p>--%>
                <%--                </div>--%>
                <%--키워드 들어가는 공간--%>
                <div class="main-keyword-txt" id="chartDiv">
                    <!-- <iframe src="http://192.168.0.170:5601/app/dashboards#/view/53d6d5e0-8da6-11ee-a28b-a73ae6d6f567?embed=true&_g=(filters%3A!()%2CrefreshInterval%3A(pause%3A!t%2Cvalue%3A0)%2Ctime%3A(from%3Anow-15m%2Cto%3Anow))" height="100%" width="100%"></iframe> -->

                </div>
            </div>
        </div>
    </div>
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
                    <img src="../../public/images/logo_ytn.png">
                </p>
                <p class="modal-news-tit">2030 부산엑스포 '불발'...결선 없이 사우디 '확정'</p>
                <div class="modal-news-info">
                    <div class="modal-news-info-txt">
                        <p>2023-11-29</p>
                        <p>김엔클 기자</p>
                        <p>ncloud@head.co.kr</p>
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
                    <p>
                        [앵커]
                        2030 세계박람회 개최지 선정 투표에서 부산이 사우디아라비아 리야드에 밀려 아쉽게 고배를 마셨습니다.
                        1차 투표에서 사우디가 3분의 2를 넘는 표를 확보하면서 결선 투표 없이 바로 최종 후보지로 결정됐습니다.
                        프랑스 파리에서 박광렬 기자가 보도합니다.
                        <br><br>
                        [기자]
                        각본 없는 '반전 드라마'는 결국, 쓰이지 못했습니다.
                        2030 엑스포 개최지 결정을 위한 국제박람회기구 BIE 총회, 165개 나라가 참여한 1차 투표에서 사우디 리야드가 119표를 가져간 겁니다.
                        <br><br>
                        대한민국 부산이 29표, 이탈리아 로마가 17표로 뒤를 이었습니다.
                        2차 투표로 가기 위한 마지노선은 사우디의 110표 미만 득표, 리야드가 10표 차이로 결선 없이 최종 개최도시에 이름을 올리는 순간이었습니다.
                        <br><br>
                        1차 투표에서 사우디를 넘진 못해도 표 차이를 최대한 줄여 2차에서 역전을 노리겠다던 우리로선 다소 아쉬운 성적표가 아닐 수 없습니다.
                        <br><br>
                        [한덕수 / 국무총리 : 국민 여러분, 그동안 지원해 주신 것에 대해서 성원에 충분히 응답하지 못해서 대단히 죄송합니다. 그리고 또 무거운 책임감을 느낍니다.]
                        <br><br>
                        물론 실망하기엔 이르다는 목소리도 적지 않습니다.
                        후발주자로 불리하게 시작한 데다 상상을 초월하는 사우디 자금력과 맞닥뜨렸지만, '코리아 원팀'은 희망의 끈을 놓지 않았습니다.
                        <br><br>
                        최종 PT에서도 사우디발 '오일 머니' 공세에 맞서 일회성이 아닌 지속 가능한 연대와 협력을 강조하며 정면 승부를 선택했기에 더 진한 여운이 남습니다.
                        <br><br>
                        [김이태 / 부산대 관광컨벤션학과 교수 (부산엑스포 유치위 자문) : (사우디가) 엑스포 개최를 위해서 10조 원 이상의 투자를 저개발 국가에다 천문학적 개발 차관과 원조
                        기금을 주는 역할을 함으로 인해 금전적인 투표가….]
                        <br><br>
                        유치전에선 고배를 마셨지만, 성과가 없는 건 아닙니다.
                        민관 합동으로 전 세계를 돌며 만들어진 글로벌 외교 네트워크는 돈으로 환산할 수 없는 소중한 가치라는 평가입니다.
                        특히 사우디와 달리 민간이 유치전에 적극 참여하면서 향후 시장 개척의 신호탄을 쐈다는 점도 무시할 수 없는 측면입니다.
                        <br><br>
                        결선에서 박빙의 승부를 펼칠 거라는 기대는 비록 빗나갔지만, 이번 유치전 과정은 또 하나의 역사로 남았습니다.
                        프랑스 파리에서 YTN 박광렬입니다.
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="/js/newsController.js"></script>

<script>
    window.dd = window.console.log.bind(console);

    const $document = $(document);

    // 이지영
    $document.ready(() => {

        drawAmChart();

        // get ElasticSearch count
        function getEsCount(list) {
            let counts = {};
            let url = {}

            const c = 'count';

            const hits = list.hits.hits;
            hits.reduce((a, hit) => {
                const source = hit._source;
                const detail = source.nounDetail;

                /*
                url[i] = hit._id;
                url[u] = source.url;
                */
                detail.reduce((b, noun) => {
                    const morph = noun.morph;
                    if (undefined == counts[morph]) {
                        counts[morph] = 0;
                        // counts[morph][id] = 0;
                    }
                    counts[morph]++;
                }, {});
            }, {});
            return counts;
        }

        // ajax and draw amchart
        function drawAmChart() {
            $.ajax({
                type: "get",
                url: "/jylee/proxy/proxy.jsp?url=" + "http://192.168.0.170:9200/newsanalyst.crawling/_search",
                dataType: 'json',
                data: {},
                success: function (result, status) {
                    const count = getEsCount(result);
                    console.log(count);
                    // json -> 객체 배열 형태로 변환
                    const dataArray = Object.keys(count).map(key => {
                        return {
                            tag: key,
                            weight: count[key],
                        }
                    })
                    let filterData = dataArray.filter(item => {
                        return item.weight > 2 && item.tag.length >= 2;
                    })
                    am4core.useTheme(am4themes_animated);
                    var chart = am4core.create("chartDiv", am4plugins_wordCloud.WordCloud);
                    var series = chart.series.push(new am4plugins_wordCloud.WordCloudSeries());
                    //series.maxCount = 10;
                    // 글자크기 선정
                    series.maxFontSize = 100;
                    series.minFontSize = 15;
                    // weight값이 1이상인 값만 추출
                    series.data = filterData;
                    series.dataFields.word = "tag";
                    series.dataFields.value = "weight";
                    // 글자간의 겹침 방지 (숫자가 커질수록 글자 겹침)
                    series.accuracy = 4;
                    // 글자와 글자사이 간격
                    series.step = 25;
                    // 단어 배치(가로 고정)
                    series.rotationThreshold = 0;
                    series.labels.template.tooltipText = "{word}: {value}";
                    // 색상 랜덤 적용
                    series.colors = new am4core.ColorSet();
                    series.colors.passOptions = {};
                    // 사용 안되는 변수(확인필요)
                    // series.maxCount = 10;
                    // series.minWordLength = 100;
                    // 유용한 옵션
                    // series.fontFamily = "'M PLUS 1p', sans-serif";
                    // series.maxFontSize = am4core.percent(30);
                    // series.colors.reuse = true;

                    // 워드 클라우드 클릭 이벤트
                    series.labels.template.events.on("hit", function (ev) {
                        const clickWord = ev.target.dataItem.dataContext.tag;
                        console.log(clickWord);
                        moveKeywordPage(clickWord);
                    });

                    // 페이지 이동
                    // series.labels.template.url = "http://localhost:8280/search";
                },
                fail: function () {
                    console.log(arguments)
                }
            });
        }

        // # start : moveKeywordPage
        function moveKeywordPage(keyword){
            const _json = {
                keyword: keyword
            };

            const ctx = Sangwon.getContextPath();
            const qs = Sangwon.jsonToQueryString(_json);

            window.console.log(Sangwon);

            const url = ctx+'/search'+qs;

            window.location.href = url;

        }
        // end : moveKeywordPage


    });


</script>

</body>

</html>