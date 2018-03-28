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
    loadhtml("/admin/deleteTeacherById.do","id="+id);
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
    loadhtml("/admin/deleteTeacherById.do","id="+ids);
}

// 冻结数据需要重新加载数据
function freeze(id, privilege) {
    if(privilege === 'none'){

        loadhtml("/admin/unfreezeTeacherById.do","id="+id);
    }else {
        loadhtml("/admin/freezeTeacherById.do", "id="+id);
    }
}
function freezeAll() {
    var ids = "";
    var boxes = $("table tr");
    $.each(boxes, function () {
        var box = $(this).find("input");
        if($(box).prop("checked")){
            ids += $(box).prop("title")+",";
        }
    });
    loadhtml("/admin/freezeTeacherById.do","id="+ids);
}

function update1(id) {
    $.ajax({
        url: "admin/resetPassword.do",
        type: "POST",
        dataType: 'text',
        data: "id=" + id,
        error: function ()
        {
            alert("修改失败1");
        },
        success: function (data)
        {
            var json1 = JSON.parse(data);
            if (json1.status === 'error'){
                alert("修改失败");
            }else{
                alert("修改成功");
            }
        }
    });// 加载页面
}
function query() {
    var userId = $("#userId").val();
    loadhtml("/admin/queryTeacher.do","userId="+userId);// 加载页面
}

function change1() {
    var page = $("#pagenum").val();
    change(page);
}
function change(pagenum, totalPage) {
    if( pagenum <= 0 || pagenum > totalPage) {
        return;
    }
    loadhtml("/admin/changeTeacherPage.do", "pageNum="+pagenum)
}
function loadhtml(url, data) {
    var tb = $("#loadhtml");
    tb.html(CommnUtil.loadingImg());
    tb.load(rootPath+url, data);// 加载页面
}