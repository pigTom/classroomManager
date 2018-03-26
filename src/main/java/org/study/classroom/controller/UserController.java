package org.study.classroom.controller;

import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.study.classroom.model.ClassroomUser;
import org.study.classroom.service.UserService;
import org.study.classroom.utils.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {
    @Resource()
    private UserService userService;

    @RequestMapping("/login.do")
    public String login(String userName, String password, Model model, HttpServletRequest request) {
        System.out.println("------------------login---------------");
        if (StringUtils.isEmpty(userName)) {
            model.addAttribute("userNameError", Constants.USERNAME_EMPTY);
            return "user_login";
        }
        if (StringUtils.isEmpty(password)) {
            model.addAttribute("passwordError", Constants.PASSWORD_EMPTY);
            return "user_login";
        }

        String result = userService.login(userName, password, request.getSession());
        if (!Constants.LOGIN_OK.equals(result)) {
            model.addAttribute("loginError", result);
            return "user_login";
        }

        // login ok
        return "user";
    }


    // ---------------------------- user login -------------------------------//
    @RequestMapping("userInfoPage.do")
    public String info(){
        return Constants.BASE_USER_PATH+"userInfo";
    }

    @RequestMapping("alertPasswordPage.do")
    public String alertPasswordPage(){
        return Constants.BASE_USER_PATH + "alertPassword";
    }

    @ResponseBody
    @RequestMapping("alterPassword.do")
    public String alertPassword(HttpSession session, Model model, String password, String newPassword, String newPassword1){
        if (StringUtils.isEmpty(password)){
            return "原始密码不能为空";

        }
        if (StringUtils.isEmpty(newPassword)) {
            return "新密码不能为空";
        }
        if (!newPassword.equals(newPassword1)) {
            return "新密码不一致";
        }
        ClassroomUser user = (ClassroomUser)session.getAttribute("user");

        user.setPassword(newPassword);
        userService.update(user);
        return "修改成功";
    }

    @ResponseBody
    @RequestMapping(value="alertInfo.do", produces = "text/html;charset=UTF-8")
    public String alertInfo(HttpSession session, String userMail, String userTelephone,
                         String userQq, String userSex, String userName ) {

        System.out.println("alertInfo");
        if (StringUtils.isEmpty(userName)) {
            return "{\"status\":\"error\"}";

        }
        ClassroomUser user = (ClassroomUser) session.getAttribute("user");
        user.setUserName(userName);
        if (userMail == null) {
            userMail = "";
        }
        user.setUserMail(userMail);
        if (userQq == null) {
            userQq = "";
        }
        user.setUserQq(userQq);
        if (userTelephone == null) {
            userTelephone = "";
        }
        user.setUserTelephone(userTelephone);
        if (userSex == null) {
            userSex = Sex.male.toString();
        }
        user.setUserSex(Sex.valueOf(userSex));

        // update to database
        System.out.println("update user info");
        userService.update(user);
//        return "{\"success  \":\"修改成功\"}";
        return "{\"status\":\"success\"}";
    }


}
