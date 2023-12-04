$().ready(function () {
    $(".news_title_btn").on('click', function (e) {
        console.log(e.target.getAttribute("_id_no"));
    });

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