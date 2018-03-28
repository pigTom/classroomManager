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
    var className = $("#className").val();
    loadhtml("user/course/queryCourse.do","className="+className);// 加载页面
}
function return1() {
    loadhtml("/user/notice/returnClassroom.do");
}

function goContent(subject, publisher, content) {
    var mainFrame = $("<div></div>");


    // 主题
    var subjectValue = $("<span></span>");
    subjectValue.html("主题:&ensp;&ensp;"+subject);
    var subjectDiv = $("<div class='data'></div>");
    subjectDiv.append(subjectValue);

    // 发布者
    var publisherValue = $("<span></span>");
    publisherValue.html("发布者:&ensp;&ensp;" + publisher);
    var publisherDiv = $("<div class='data'></div>");
    publisherDiv.append(publisherValue);

    // 内容
    var contentDiv = $("<div class='data'></div>");
    contentDiv.append(content);

    // 按顺序加入mainFrame
    mainFrame.append(subjectDiv);
    mainFrame.append(publisherDiv);
    mainFrame.append(contentDiv);
    var tb = $("#loadhtml");

    // 删除#loadhtml元素的所有子元素
    tb.children().remove();
    // 加载新创建的页面
    // tb.html(CommnUtil.loadingImg());
    tb.append(mainFrame);
}

function change1() {
    var page = $("#pagenum").val();
    change(page);
}
function change(pagenum, totalPage) {
    if( pagenum <= 0 || pagenum > totalPage ) {
        return;
    }
    loadhtml("user/notice/changePage.do", "row="+pagenum)
}


function loadhtml(url, data) {
    var tb = $("#loadhtml");
    tb.html(CommnUtil.loadingImg());
    tb.load(url, data);// 加载页面
}