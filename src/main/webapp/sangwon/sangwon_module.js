window.Sangwon = {
    getContextPath : () => {
        return `${SERVER_SCHEME}://${SERVER_NAME}:${SERVER_PORT}${CONTEXT_PATH}`;
    },
    jsonToQueryString : (json) => {
        return '?'+Object.entries(json).map( ([key,value]) => ( value && key+'='+value )).filter(v=>v).join('&');
    }
}