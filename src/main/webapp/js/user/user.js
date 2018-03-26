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
            alert(data);
            var tb = $("#loadhtml");
            tb.html(CommnUtil.loadingImg());
            tb.load(rootPath+"/user/alertPasswordPage.do")
        } ,
        error: function (data)
        {
            alert(data);
        }
    });
}



