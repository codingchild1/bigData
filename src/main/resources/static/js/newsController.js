let count = 6;
$().ready(function () {

    // var
    const $document = $(document);
    const $pagination = $document.find('div.pagination');

    const pageSize = 5;
    const blockSize = 5;

    let totalPage = 0;
    let totalBlock = 0;
    let currentPageIndex = 1;

    const $pagingBtnWrap = $pagination.find('div.pagination-wrapper');

    const $backBtn = $pagination.find('button.prev');
    const $forwardBtn = $pagination.find('button.next');

    // 변수 선언 끝난 후 실행.
    if("search" == SERVLET_PATH){
        paginate();
    }

    /*** # event : start ***/
    // click : pagination
    $(document).on('click', 'button.pagination-btn', (e) => {
        e.preventDefault();
        e.stopImmediatePropagation();

        const $currentButton = $(e.currentTarget);
        const pageindex = $currentButton.attr('pageindex');
        paginate(pageindex -1);

    });

    // click : search
    $(document).on('click', 'button#btn_search', (e) => {
        e.preventDefault();
        e.stopImmediatePropagation();
        paginate();
    });

    // click : list
    $(document).on('click', 'button.news_title_btn, div.main-issue-img-cont > button', (e) => {
        const $target = $(e.currentTarget);
        const url = $target.attr("url");
        findByUrl(url);
        $(".modal").css("display", "block");
    });
    /*** event : end ***/

    function searchList(page = 0){
        const $form = $('form#selectInput');
        const searchType = $form.find('select.select-m').find('option:selected').val();
        const keyword = $form.find('input.input-l').val();

        const result = Sangwon.post("/search", {
            searchType : searchType,
            keyword : keyword,
            page : page
        });

        const resultList = result.result
        const rowcount = result.rowcount;

        $('span.red-txt').text(rowcount);

        totalPage = Math.ceil(rowcount / pageSize);
        totalBlock = Math.ceil(totalPage / blockSize);

        // draw List
        $(".news_list_search").empty();

        resultList.reduce((a, v) => {
            printNewsList(v);
        }, []);
    }

    function paginate(idx = 0){
        searchList(idx);

        let nowPage = idx + 1;
        let nowBlock = Math.ceil(nowPage / blockSize);
        let firstPage = ((nowBlock - 1) * blockSize) + 1;
        let last = nowBlock * blockSize, lastPage = last >= totalPage ? totalPage : last;
        let backPage = firstPage <= 1 ? 1 : firstPage - 1;

        $backBtn.prop('disabled', (nowPage > 1 && nowBlock <= totalBlock ? false : true)).attr('pageindex', backPage);
        $forwardBtn.prop('disabled', (nowBlock < totalBlock ? false : true)).attr('pageindex', lastPage+1);

        $pagingBtnWrap.empty();

        let dom = '';

        for(let i = firstPage; i <= lastPage && i <= totalPage; i++){
            dom = `<button pageindex="${i}" type="button" class="pagination-btn ${i != nowPage ? '' : 'active'}">${i}</button>`;
            $pagingBtnWrap.append(dom);
        }

        currentPageIndex = nowPage;

    }

    function showLatestNews(list){
        const news = list[0];

        const $document = $(document);
        const $wrapper = $document.find('div.main-issue-img-cont > button');
        const $img = $wrapper.find('.main-issue-img').find('img');
        const $title = $wrapper.find('.main-issue-img-tit');

        $wrapper.attr('url', news.url);

        $img.attr('src', news.img);
        $title.text(news.nontagTitle);

    }

    function printNewsList(param) {
        const $list = $(".news_list_search");

        let str = "";

        str += "<li>";
        str += "<button type='button' class='news_title_btn' url=" + param.url + ">";
        str += "<div class='news-title-btn-div'>";
        str += "<p class='main-news-list-tit'>" + param.nontagTitle + "</p>";
        str += "<p class='main-news-list-txt'>" + param.nontagDetail + "</p>";
        str += "</div>";
        str += "<p class='main-news-list-stxt'>";
        str += "<span>" + param.media + "</span>";
        str += "<span>" + param.date + "</span>";
        str += "<span>" + param.reporter + "</span>";
        str += "<span>" + param.email + "</span>";
        str += "</p>";
        str += "</button>";
        str += "</li>";

        $list.append(str);
    }

    $(".modal-news-close").on("click", function (e) {
        console.log(e.target.getAttribute("_id_no"));
        $(".modal").css("display", "none");
    });
    /*** event : end ***/



    /*** # methods execution : start ***/

    // set latest news
    setLatestNews();



    /*** methods execution : end ***/



    /*** # methods definition : start ***/
    function setLatestNews(){
        const result = Sangwon.get("/getLatestNews");
        showLatestNews(result);
    }

    /*Url 조회 시작*/
    function findByUrl(newsUrl) {
        $.ajax({
            type: "POST",
            url: "/detail",
            data: {
                newsUrl: newsUrl,
            },
            dataType: "json",
            success: function (returnData, status) {
                console.log("성공");
                $("#modal_title").html(returnData.title);
                $("#modal_date").text(returnData.date);
                $("#modal_media").text(returnData.media);
                $("#modal_email").text(returnData.email);
                $("#modal_detail").html(returnData.detail);
                $(".modal-news-cont-img").find("img").attr("src", returnData.img);
            },
            fail: function () {
                console.log("실패");
            },
        });
    }

    // $(document).on("click", ".paginationBtn", function (e) {
    //
    //   let pageNo = e.target.textContent - 1;
    //   $.ajax({
    //     type: "post",
    //     url: "/search",
    //     // dataType: 'json',
    //     data: {
    //           keyword: $("input[name='keyword']").val(),
    //           searchType: $("select[name='searchType']").val(),
    //           page: pageNo,
    //     },
    //     success: function (result, status) {
    //       $(".main-news-list-info").empty();
    //       $(".news_list_search").empty();
    //
    //       $(".main-news-list-info").append(
    //         "<p class='main-news-list-info'>뉴스 검색 결과 <span class='red-txt'>192,168</span> 건입니다.</p>",
    //       );
    //
    //       for (
    //         let newsDataSize = 0;
    //         newsDataSize < result.length;
    //         newsDataSize++
    //       ) {
    //         let param = result[newsDataSize];
    //         printNewsList(param);
    //       }
    //       $(".pagination").empty();
    //
    //       let str = "";
    //       str += "<button type='button' class='prev paging-icon'></button>";
    //       str += "<div class='pagination-wrapper'>";
    //       str += "<button type='button' class='paginationBtn'>1</button>";
    //       str += "<button type='button' class='paginationBtn'>2</button>";
    //       str += "<button type='button' class='paginationBtn'>3</button>";
    //       str += "<button type='button' class='paginationBtn'>4</button>";
    //       str += "<button type='button' class='paginationBtn'>5</button>";
    //       str += "</div>";
    //       str += "<button type='button' class='next paging-icon'></button>";
    //       $(".pagination").append(str);
    //
    //       // var button = document.createElement("button");
    //       // button.addEventListener('click', clickHandler);
    //       // document.getElementById('dd')
    //       // $('.paginationBtn').on('click', clickHandler);
    //       // $('.paginationBtn').on('click')
    //
    //       // $(".pagination").append("<a href='#' class='paginationBtn active'>1</a>");
    //       // $(".pagination").append("<a href='#' class='paginationBtn'>2</a>");
    //       // $(".pagination").append("<a href='#' class='paginationBtn'>3</a>");
    //       // $(".pagination").append("<a href='#' class='paginationBtn'>4</a>");
    //     },
    //     fail: function () {
    //       console.log(arguments);
    //     },
    //   });
    //
    //
    //   });

    // /*검색 ajax*/
    //   $(document).on('click', '#btn_search', function () {
    //       search();
    //   });
    //
    //   function search() {
    //       $.ajax({
    //           type: "get",
    //           url: "/search",
    //           data: {
    //               searchType: $('select[name="searchType"]').val(),
    //               keyword: $('input[name="keyword"]').val()
    //           },
    //           success: function (returnData, status) {
    //               console.log("성공");
    //           },
    //           fail: function () {
    //               console.log("실패");
    //           },
    //       });
    //   }
    // // /*검색 ajax*/
    //
    // /*다음버튼*/
    // $(document).on("click", ".next", function () {
    //     $(".pagination").empty();
    //     let str = "";
    //     str += "<button type='button' class='prev paging-icon'></button>";
    //     for (let i = count; i < count + 5; i++) {
    //         str += "<button type='button' class='paginationBtn'>" + i + "</button>";
    //     }
    //     str += "<button type='button' class='next paging-icon'></button>";
    //     $(".pagination").append(str);
    //     count += 5;
    // });
    //
    // /*이전버튼*/
    // $(document).on("click", ".prev", function () {
    //     if (count > 5) {
    //         count -= 5;
    //         $(".pagination").empty();
    //         let str = "";
    //         str += "<button type='button' class='prev paging-icon'></button>";
    //         for (let i = count; i < count + 5; i++) {
    //             str += "<button type='button' class='paginationBtn'>" + i + "</button>";
    //         }
    //         str += "<button type='button' class='next paging-icon'></button>";
    //         $(".pagination").append(str);
    //     } else {
    //         count = 1;
    //     }
    // });
});
