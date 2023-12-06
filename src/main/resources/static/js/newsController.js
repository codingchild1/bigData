$().ready(function () {
    $(".news_title_btn").on('click', function (e) {
        const newsUrl=e.target.getAttribute("url");
        findByUrl(newsUrl);
        console.log(e.target.getAttribute("url"));
        $(".modal").css("display", "block");
    });
    $(".modal-news-close").on('click', function (e) {
        console.log(e.target.getAttribute("_id_no"));
        $(".modal").css("display", "none");
    });
    /*Url 조회 시작*/
    function findByUrl(newsUrl){
        $.ajax({
            type:"POST",
            url:"/detail",
            data:{
                "newsUrl":newsUrl
            },
            dataType:"json",
            success:function(returnData, status){
                console.log("성공");
                $("#modal_title").html(returnData.title);
                $("#modal_date").text(returnData.date);
                $("#modal_media").text(returnData.media);
                $("#modal_email").text(returnData.email);
                $("#modal_detail").html(returnData.detail);
                $(".modal-news-cont-img").find("img").attr("src", returnData.img)
            },
            fail:function() {
                console.log("실패");
            }
        });
    }
    /*Url 조회 끝*/

    /*뉴스검색 페이지네이션*/
    $(".pagination").append("<button type='button' class='paginationBtn active'>1</button>");
    $(".pagination").append("<button type='button' class='paginationBtn'>2</button>");
    $(".pagination").append("<button type='button' class='paginationBtn'>3</button>");
    $(".pagination").append("<button type='button' class='paginationBtn'>4</button>");

        $(document).on('click', '.paginationBtn', function (e) {
            let pageNo = e.target.textContent - 1;
            $.ajax({
                type: "post",
                url: "/search",
                // dataType: 'json',
                data: {
                    keyword: $("input[name='keyword']").val(),
                    page: pageNo
                },
                success: function (result, status) {
                    $(".main-news-list-info").empty();
                    $(".news_list_search").empty();

                    $(".main-news-list-info").append("<p class='main-news-list-info'>뉴스 검색 결과 <span class='red-txt'>192,168</span> 건입니다.</p>");

                    for (let newsDataSize = 0; newsDataSize < result.length; newsDataSize++) {
                        let param = result[newsDataSize];
                        printNewsList(param)
                    }
                    $(".pagination").empty();

                    let str = "";
                    str += "<button type='button' class='paginationBtn active'>1</button>";
                    str += "<button type='button' class='paginationBtn'>2</button>";
                    str += "<button type='button' class='paginationBtn'>3</button>";
                    str += "<button type='button' class='paginationBtn'>4</button>";
                    $(".pagination").append(str);

                    // var button = document.createElement("button");
                    // button.addEventListener('click', clickHandler);
                    // document.getElementById('dd')
                    // $('.paginationBtn').on('click', clickHandler);
                    // $('.paginationBtn').on('click')




                    // $(".pagination").append("<a href='#' class='paginationBtn active'>1</a>");
                    // $(".pagination").append("<a href='#' class='paginationBtn'>2</a>");
                    // $(".pagination").append("<a href='#' class='paginationBtn'>3</a>");
                    // $(".pagination").append("<a href='#' class='paginationBtn'>4</a>");

                },
                fail: function () {
                    console.log(arguments)
                }

            })

            function printNewsList(param) {
                let str = "";

                str += "<li>";
                str += "<a href=''>";
                str += "<div>";
                str += "<p class='main-news-list-tit'>" + param.nontagTitle + "</p>";
                str += "<p class='main-news-list-txt'>" + param.nontagDetail + "</p>";
                str += "</div>";
                str += "<p class='main-news-list-stxt'>";
                str += "<span>" + param.media + "</span>";
                str += "<span>" + param.date + "</span>";
                str += "<span>" + param.reporter + "</span>";
                str += "<span>" + param.email + "</span>";
                str += "</p>";
                str += "</a>";
                str += "</li>";
                $(".news_list_search").append(str);
            }
        });

})