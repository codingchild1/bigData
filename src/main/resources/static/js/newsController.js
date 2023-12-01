$().ready(function () {
    $(".news_title_btn").on('click', function (e) {
        console.log(e.target.getAttribute("_id_no"));
    });
});