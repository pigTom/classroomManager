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
function cancelOrders(logId, classId) {
    loadhtml("classroom/deleteOrdersById.do", "logId="+logId+"&classroomId="+classId);
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
    alert(ids)
    loadhtml("classroom/deleteAllOrdersById.do","logId="+ids);
}
function return1() {
    loadhtml("classroom/returnFreeClassroom.do");
}
function loadhtml(url, data) {
    var tb = $("#loadhtml");
    tb.html(CommnUtil.loadingImg());
    tb.load(url, data);// 加载页面
}