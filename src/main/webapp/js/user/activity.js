function selectAll() {
    // 如果它是选中的，就取消选中所有check box
    if( $("#checkbox").is(':checked')){
        //$(this).attr("checked", "checked");
        //alert("checked");
        $("table input").prop("checked", true);
    }else{
        // $(this).attr("checked", "checked");
        // alert("unchecked");
        $("table input").prop("checked", false);
    }
}

function query() {
    var userId = $("#className").val();
    loadhtml("/user/course/queryCourse.do","className="+userId);// 加载页面
}


function return1() {
    loadhtml("/user/activity/returnClassroom.do");
}


function change1() {
    var page = $("#pagenum").val();
    change(page);
}

function change(pagenum, totalPage) {
    if( pagenum <= 0 || pagenum > totalPage ) {
        return;
    }
    loadhtml("/user/activity/changeActivityPage.do", "row="+pagenum)
}

function cancelOrders(logId) {
    loadhtml("/user/activity/deleteOrdersById.do", "logId="+logId);
}

function loadhtml(url, data) {
    var tb = $("#loadhtml");
    tb.html(CommnUtil.loadingImg());
    tb.load(rootPath+url, data);// 加载页面
}