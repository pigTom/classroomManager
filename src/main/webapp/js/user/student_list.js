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
function deleteUser(id) {
    loadhtml("user/deleteStudentById.do","id="+id);
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
    alert(ids)
    loadhtml("user/deleteStudentById.do","id="+ids);
}

// 冻结数据需要重新加载数据
function freeze(id, privilege) {
    if(privilege === 'none'){

        loadhtml("user/unfreezeStudentById.do","id="+id);
    }else {
        loadhtml("user/freezeStudentById.do", "id="+id);
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
    alert(ids)
    loadhtml("user/freezeStudentById.do","id="+ids);
}

function update1(id) {
    loadhtml(rootPath+"user/updateStudent.do?id=" + id);// 加载页面
}
function query() {
    var userId = $("#userId").val();
    loadhtml("user/queryStudent.do","userId="+userId);// 加载页面
}

function change1() {
    var page = $("#pagenum").val();
    change(page);
}
function change(pagenum, totalPage) {
    if( pagenum <= 0 || pagenum > totalPage ) {
        return;
    }
    loadhtml("user/changeStudentPage.do", "pageNum="+pagenum)
}
function loadhtml(url, data) {
    var tb = $("#loadhtml");
    tb.html(CommnUtil.loadingImg());
    tb.load(url, data);// 加载页面
}