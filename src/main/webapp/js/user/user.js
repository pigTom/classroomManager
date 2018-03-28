function alertBtn() {
    $("table tr td input").each(function () {
        $(this).removeAttr("disabled");
    })
}

function alertUserInfo() {
    event.preventDefault();
    if ($("#qq").attr("disabled") === "disabled"){
        alert("请您修改后再按确认");
        return false;
    }
    // 校验用户Id只能是数字
    var checkNum = /^[0-9]*$/;
    var qq = $("#qq").val();
    if (!checkNum.test(qq)){
        alert("qq账号不正确");
        return false;
    }
    //校验姓名
    var name = $("#userName").val();
    if (name.trim() === ''){
        alert("姓名不能为空");
        return false;
    }
    if (checkNum.test(name)){
        alert("姓名不能全为数字");
        return false;
    }

    // 校验邮箱
    var mail = $("#userMail").val();
    var checkMail = /^[\w-]+(.[\w-])*@[\w-]+.[\w]+$/;
    if (mail.trim() === ''){
        alert("邮箱不能为空");
        return false;
    }
    if (!checkMail.test(mail)){
        alert("邮箱格错误");
        return false;
    }

    $.ajax({
        url: rootPath+"/user/alertInfo.do",
        type: "POST",
        dataType: 'text',
        data: $("form").serialize(),
        async: true,
        error: function (data)
        {
            alert("修改失败");
        },
        success: function (data)
        {
            var json1 = JSON.parse(data);
            if (json1.status === 'error'){
                alert("修改失败");
            }else{
                alert("修改成功")
                var tb = $("#loadhtml");
                tb.html(CommnUtil.loadingImg());
                tb.load(rootPath+"/user/userInfoPage.do")
            }
        }
    })

}


function alertPassword() {

    var passwd = $("#password").val();
    if (passwd.trim() === ''){
        alert("原始不能为空");
        return;
    }

    var newPwd = $("#newPassword").val();
    if (newPwd.trim() === ''){
        alert("新密码不能为空");
        return
    }

    var newPwd1 = $("#newPassword1").val();
    if (newPwd1 !== newPwd){
        alert("再次密码输入不一致");
        return;
    }

    $.ajax({
        url: rootPath+"/user/alterPassword.do",
        type: "POST",
        data: $("form").serialize(),
        dataType: 'text',
        async: true,
        success: function (data)
        {
            var json1 = JSON.parse(data);
            if (json1.status === 'error'){
                alert("修改失败");
            }
            alert("修改成功");
            var tb = $("#loadhtml");
            tb.html(CommnUtil.loadingImg());
            tb.load(rootPath+"/user/alertPasswordPage.do")
        } ,
        error: function ()
        {
            alert("修改失败");
        }
    });
}
function change1() {
    var page = $("#pagenum").val();
    change(page);
}
function change(pagenum, totalPage) {
    if( pagenum <= 0 || pagenum > totalPage ) {
        return;
    }
    loadhtml("classroom/changeClassroomPage.do", "pageNum="+pagenum)
}


function loadhtml(url, data) {
    var tb = $("#loadhtml");
    tb.html(CommnUtil.loadingImg());
    tb.load(rootPath+"/"+url, data);// 加载页面
}

function orderClass() {

    // 阻止表单发送
    event.preventDefault();

    $.ajax({
        url: rootPath+"/user/course/orderClass.do",
        type: "POST",
        data: $("form").serialize(),
        dataType: 'text',
        async: true,
        success: function (data)
        {
            if (data === 'success'){

                alert("预定成功");
                // 重置表单
                loadhtml("user/classroom/doOrder.do")
            }else{
                alert("预定失败");
                $("form").each(function () {
                    $(this).reset();
                });
                ///$("#tipDate").attr("checked", true);
            }
        } ,
        error: function ()
        {
            alert("服务端错误");
        }
    });
}

$(function () {
    $("#orderDate").bind("change", function () {

        var orderDate = $(this).find("option:selected").val();
        console.log("select val: " + orderDate);
        var classroomId = $(this).attr("title");
        console.log("classroomId : " + classroomId);
        $.ajax({
            url: rootPath+"/user/course/findFreeOrderTime.do",
            type: "POST",
            data: "date="+orderDate+"&classroomId="+classroomId,
            dateType: "text",
            success: function (data) {
                console.log("data...:  " + data);
                if (data !== undefined){
                    // 移除提醒框
                    $("#orderTime option").remove();

                    // 获取到id为orderTime的选项框
                    var select = $("#orderTime");

                    // data 应该为 1, 2, 3, 4, 5组成的字符串表示可以进行预定
                    var orders = data.split(/\s*?,/);

                    if (orders.length === 0){
                        var op1 = $('<option>教室此日不可预定</option>');
                        select.append(op1);
                        return;
                    }

                    for (var i = 0; i<orders.length; i++) {
                        var op = $('<option></option>');
                        switch (orders[i].trim()){
                            case '1':
                                op.text("第一大节");
                                break;
                            case '2':
                                op.text("第二大节");
                                break;
                            case '3':
                                op.text("第三大节");
                                break;
                            case '4':
                                op.text("第四大节");
                                break;
                            case '5':
                                op.text("第五大节");
                                break;
                            default:
                                op.html("教室此日不可预定");
                        }

                        op.val(orders[i].trim());
                        select.append(op);
                    }

                }else{
                    alert("服务器发生错误");
                }
            }
        })
    })
});
