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

            },
            fail:function() {
                console.log("실패");
            }
        });
    }
    /*Url 조회 끝*/


    /*뉴스검색 페이지네이션*/
    $(".pagination").append("<a href='#' class='active'>1</a>");
    $(".pagination").append("<a href='#'>2</a>");
    $(".pagination").append("<a href='#'>3</a>");
    $(".pagination").append("<a href='#'>4</a>");

    $.ajax({
        type: "get",
        url: "/search",
        dataType: 'json',
        data: {},
        success: function (result, status) {
        },
        fail: function () {
            console.log(arguments)
        }

    })

})