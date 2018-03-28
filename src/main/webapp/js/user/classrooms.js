// 进入预定教室页面
function doOrder(classroomId) {
    var data = "classroomId="+classroomId;
    loadhtml("/user/classroom/doOrder.do", data);
}

function query() {
    var className = $("#className").val();
    loadhtml("/user/classroom/queryClassroom.do","className="+className);// 加载页面
}
function change1() {
    var page = $("#pagenum").val();
    change(page);
}
function change(pagenum, totalPage) {
    if( pagenum <= 0 || pagenum > totalPage ) {
        return;
    }
    loadhtml("/user/classroom/changeClassroomPage.do", "pageNum="+pagenum)
}


function loadhtml(url, data) {
    var tb = $("#loadhtml");
    tb.html(CommnUtil.loadingImg());
    tb.load(rootPath+url, data);// 加载页面
}

