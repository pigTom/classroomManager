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
function deleteAllOrders(){
    var ids = "";
    var boxes = $("table tr");
    $.each(boxes, function () {
        var box = $(this).find("input");
        if($(box).prop("checked")){
            ids += $(box).prop("title")+",";
        }
    });
    loadhtml("admin/course/deleteAllOrdersById.do","logId="+ids);
}
function query() {
    var className = $("#className").val();
    loadhtml("admin/course/queryCourse.do","className="+className);// 加载页面
}


function return1() {
    loadhtml("admin/course/returnClassroom.do");
}

function cancelOrders(logId) {
    loadhtml("admin/course/deleteOrdersById.do", "logId="+logId);
}
function change1() {
    var page = $("#pagenum").val();
    change(page);
}
function change(pagenum, totalPage) {
    if( pagenum <= 0 || pagenum > totalPage ) {
        return;
    }
    loadhtml("admin/course/changeCoursePage.do", "row="+pagenum)
}


function loadhtml(url, data) {
    var tb = $("#loadhtml");
    tb.html(CommnUtil.loadingImg());
    tb.load(url, data);// 加载页面
}