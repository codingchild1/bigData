function ajaxSangwon(_url, _param, _type){
    let result = undefined;

    const param = _param || {};

    $.ajax({
        type: _type,
        url: _url,
        async : false,
        dataType: 'json',
        data: param,
        success: function (r, status) {
            result = r;
        },
        fail: function (e) {
            result = e;
        }
    });

    return result;
}

window.Sangwon = {
    getContextPath : () => {
        return `${SERVER_SCHEME}://${SERVER_NAME}:${SERVER_PORT}${CONTEXT_PATH}`;
    },
    jsonToQueryString : (json) => {
        return '?'+Object.entries(json).map( ([key,value]) => ( value && key+'='+value )).filter(v=>v).join('&');
    },
    get : (url, param) => {
        return ajaxSangwon(url, param, "GET");
    },
    post : (url, param) => {
        return ajaxSangwon(url, param, "POST");
    }
}