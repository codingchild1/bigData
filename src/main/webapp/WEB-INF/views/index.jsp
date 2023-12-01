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

    <script src="https://cdn.amcharts.com/lib/5/index.js"></script>
    <script src="https://cdn.amcharts.com/lib/5/wc.js"></script>
    <script src="https://cdn.amcharts.com/lib/5/themes/Animated.js"></script>
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
            <button id="call" style="background-color: blue;">불러오기</button>
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

        $document.on('click', '#call', () => {

            $.ajax({
                type: "get",
                url: "/jylee/proxy/proxy.jsp?url=" + "http://192.168.0.170:9200/newsanalyst.crawling/_search",
                dataType: 'json',
                data: {},
                success: function (result, status) {
                    const count = getEsCount(result);

                    // json -> 객체 배열 형태로 변환
                    const dataArray = Object.keys(count).map(key => {
                        return {
                            category: key,
                            value: count[key],
                        }
                    })

                    // Create root element
                    // https://www.amcharts.com/docs/v5/getting-started/#Root_element
                    var root = am5.Root.new("chartDiv");


                    // Set themes
                    // https://www.amcharts.com/docs/v5/concepts/themes/
                    root.setThemes([
                        am5themes_Animated.new(root)
                    ]);


                    // Add wrapper container
                    var container = root.container.children.push(am5.Container.new(root, {
                        width: am5.percent(100),
                        height: am5.percent(100),
                        layout: root.verticalLayout
                    }));


                    // Add chart title
                    var title = container.children.push(am5.Label.new(root, {
                        text: "Most popular languages on StackOverflow",
                        fontSize: 20,
                        x: am5.percent(50),
                        centerX: am5.percent(50)
                    }));


                    // Add series
                    // https://www.amcharts.com/docs/v5/charts/word-cloud/
                    var series = container.children.push(am5wc.WordCloud.new(root, {
                        categoryField: "tag",
                        valueField: "value",
                        calculateAggregates: true, // this is needed for heat rules to work
                        randomness: 0
                        //     maxCount:100,
                        //     minWordLength:2,
                        //     maxFontSize:am5.percent(35)
                    }));

                    // Set up heat rules
                    // https://www.amcharts.com/docs/v5/charts/word-cloud/#Via_heat_rules
                    series.set("heatRules", [{
                        target: series.labels.template,
                        dataField: "value",
                        min: am5.color(0xFFD4C2),
                        max: am5.color(0xFF621F),
                        key: "fill"
                    }]);

                    // Configure labels
                    series.labels.template.setAll({
                        paddingTop: 5,
                        paddingBottom: 5,
                        paddingLeft: 5,
                        paddingRight: 5,
                        fontFamily: "Courier New",
                        cursorOverStyle: "pointer"
                    });

                    // 워드 클라우드 클릭 이벤트
                    series.labels.template.events.on("click", function (ev) {
                        debugger;
                        const category = ev.target.dataItem.get("category");
                        let url = ev.target.dataItem.dataContext.url;
                        if(url == undefined){
                            url = "http://www.daum.net"
                        }
                        window.open(url);
                    });

                    // series.data.setAll([
                    //     {category: "JavaScript", value: 64.96, url: "http://naver.com"},
                    //     {category: "HTML/CSS", value: 56.07},
                    //     {category: "Python", value: 48.24},
                    //     {category: "SQL", value: 47.08},
                    //     {category: "Java", value: 35.35},
                    //     {category: "Node.js", value: 33.91},
                    //     {category: "TypeScript", value: 30.19},
                    //     {category: "Ty23peScript", value: 30.19},
                    //     {category: "TypeS23cript", value: 40.19},
                    //     {category: "TypeSc1ript", value: 50.19},
                    //     {category: "TypeSc23ript", value: 60.19},
                    //     {category: "TypfeScdript", value: 70.19},
                    //     {category: "TypeScdeript", value: 80.19},
                    //     {category: "TypeaaScript", value: 90.19},
                    // ]);

                    series.data.setAll(dataArray);


                    // 워드 클라우드 생성 함수 호출
                    // createWordCloud(counts);

                    console.log(count);
                },
                fail: function () {
                    console.log(arguments)
                }
            });

        });

        // get ElasticSearch count
        function getEsCount(list){
            let counts = {};
            const hits = list.hits.hits;
            hits.reduce((a, hit) => {
                const detail = hit._source.nounDetail;
                detail.reduce((b, noun) => {
                    const morph = noun.morph;
                    if(undefined == counts[morph]){
                        counts[morph] = 0;
                    }
                    counts[morph]++;
                }, {});
            }, {});
            return counts;
        }

    });


</script>

</body>

</html>