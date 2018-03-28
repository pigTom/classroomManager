package org.study.classroom.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.study.classroom.model.ClassroomUser;
import org.study.classroom.service.UserService;
import org.study.classroom.utils.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class UserController {
    @Resource()
    private UserService userService;

    @RequestMapping("/login.do")
    public String login(String userName, String password, Model model, HttpSession session) {

        if (StringUtils.isEmpty(userName)) {
            model.addAttribute("userNameError", Constants.USERNAME_EMPTY);
            return "user_login";
        }
        if (StringUtils.isEmpty(password)) {
            model.addAttribute("passwordError", Constants.PASSWORD_EMPTY);
            return "user_login";
        }

        String result = userService.login(userName, password, session);
        if (!Constants.LOGIN_OK.equals(result)) {
            model.addAttribute("loginError", result);
            return "user_login";
        }

        // login ok
        return "user";
    }

    @RequestMapping("logout.do")
    public String logout(HttpSession session){
        session.setMaxInactiveInterval(0);
        return "user_login";
    }

    /**
     * @return 返回学生信息页面，即用户信息修改页面
     */
    @RequestMapping("userInfoPage.do")
    public String info(){
        return Constants.USER_INFORMATION_PATH;
    }

    /**
     * 返回密码修改页面
     * @return 密码修改页面
     */
    @RequestMapping("alertPasswordPage.do")
    public String alertPasswordPage(){
        return Constants.USER_PASSWORD_PATH;
    }

    /**
     *
     * @param session 用户session，保存用户的信息（classroomUser对象）
     * @param password 原始密码
     * @param newPassword 修改的新密码
     * @param newPassword1 再次确认的新密码
     * @return 返回 是否修改成功（json数据）
     */
    @ResponseBody
    @RequestMapping("alterPassword.do")
    public String alertPassword(HttpSession session, String password, String newPassword, String newPassword1){
        String status = "{\"status\":\"error\"}";
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(newPassword) || !newPassword.equals(newPassword1)){
            return status;

        }
        ClassroomUser user = (ClassroomUser)session.getAttribute("user");

        user.setPassword(newPassword);
        userService.update(user);
        return "{\"status\":\"success\"}";
    }

    /**
     * 修改用户信息
     * @param session 保存用户信息的session
     * @param userMail 用户电子邮件地址
     * @param userTelephone 用户电话
     * @param userQq 用户qq
     * @param userSex 用户性别
     * @param userName 用户姓名
     * @return 返回修改的状态（成功或者失败）
     */
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
