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
// 删除用户byId
function deleteClassroom(id) {
    loadhtml("admin/classroom/deleteClassroomById.do","id="+id);
}

// 删除所有选中的元素
function deleteAll() {
    var ids = "";
    var boxes = $("table tr");
    $.each(boxes, function () {
        var box = $(this).find("input");
        if($(box).prop("checked")){
            ids += $(box).prop("title")+",";
        }
    });
    loadhtml("admin/classroom/deleteClassroomById.do","id="+ids);
}

// 冻结数据需要重新加载数据
function freeze(id, available) {
    if(available === 'yes'){

        loadhtml("admin/classroom/freezeClassroomById.do","id="+id);
    }else {
        loadhtml("admin/classroom/unfreezeClassroomById.do", "id="+id);
    }
}
function freezeAll() {
    var ids = "";
    var boxes = $("table tr");
    $.each(boxes, function () {
        var box = $(this).find("input");
        //alert($(box).prop("title"));
        if($(box).prop("checked")){
            ids += $(box).prop("title")+",";
        }
    });
    loadhtml("admin/classroom/freezeClassroomById.do","id="+ids);
}

function query() {
    var className = $("#className").val();
    loadhtml("admin/classroom/queryClassroom.do","className="+className);// 加载页面
}

function change1() {
    var page = $("#pagenum").val();
    change(page);
}
function change(pagenum, totalPage) {
    if( pagenum <= 0 || pagenum > totalPage ) {
        return;
    }
    loadhtml("admin/classroom/changeClassroomPage.do", "pageNum="+pagenum)
}
function intoOrder(id) {
    loadhtml("admin/classroom/intoOrder.do", "id="+id);
}


function loadhtml(url, data) {
    var tb = $("#loadhtml");
    tb.html(CommnUtil.loadingImg());
    tb.load(rootPath+"/"+url, data);// 加载页面
}