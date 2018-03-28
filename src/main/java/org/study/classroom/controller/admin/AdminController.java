package org.study.classroom.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.study.classroom.controller.PageHelper;
import org.study.classroom.model.ClassroomUser;
import org.study.classroom.service.AdminService;
import org.study.classroom.service.UserService;
import org.study.classroom.utils.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("admin")
public class AdminController {
    @Resource
    private AdminService adminService;
    @Resource()
    private UserService userService;

    @RequestMapping("login.do")
    public String login(HttpSession session, String adminName, String password, Model model){

        if (StringUtils.isEmpty(adminName)){
            model.addAttribute("idError", Constants.USERNAME_EMPTY);
            return "admin_login";
        }
        if (StringUtils.isEmpty(password)){
            model.addAttribute("passwordError", Constants.PASSWORD_EMPTY);
            return "admin_login";
        }

        String result = adminService.login(session, adminName, password);
        if (!Constants.LOGIN_OK.equals(result)){
            model.addAttribute("loginError", result);
            return "admin_login";
        }

        // login ok
        return "index";
    }

    @RequestMapping("logout.do")
    public String logout(HttpSession session){
        session.setMaxInactiveInterval(0);
        return "admin_login";
    }
    /**
     * 用户注册
     * @param userId 用户的学号或者老师的工号
     * @param password 以后的登录密码
     * @param userTitle 用户的身份，学生或者老师
     * @param userName 用户的姓名
     * @param model 返回的视图类
     * @return 返回将展示的页面
     */
    @RequestMapping("/register.do")
    public String register(String userId, String password, String userTitle,
                           String userName, Model model) {
        System.out.println("register.do");
        if (StringUtils.isEmpty(userId) || !Util.isNumeric(userId)) {
            model.addAttribute("userIdError", Constants.ID_EMPTY);
            return "register";
        }
        if (StringUtils.isEmpty(password)) {
            model.addAttribute("passwordError", Constants.PASSWORD_EMPTY);
            return "register";
        }
        if (StringUtils.isEmpty(userTitle)) {
            model.addAttribute("userTitleError", Constants.USER_TITLE_ERROR);
            return "register";
        }
        if (StringUtils.isEmpty(userName)) {
            model.addAttribute("userNameError", Constants.USERNAME_EMPTY);
        }
        ClassroomUser user = new ClassroomUser();
        user.setUserName(userName);
        user.setUserId(Long.parseLong(userId.trim()));
        user.setPassword(password);
        user.setUserTitle(Title.valueOf(userTitle));
        if  (!userService.addUser(user)){
            model.addAttribute("userIdError", "学号已存在");
            return "register";
        }
        return "user_login";
    }

    /**
     * 查询学生信息根据学生的姓名或者学号
     * @param userId 学生的姓名或者学号
     * @param model 要返回给前端的视图类
     * @return 返回查询的学生页面
     */
    @RequestMapping("queryStudent.do")
    public String query(String userId,  Model model){
        List<ClassroomUser> userList = userService.selectStudentByNameLikeOrId(userId);
        UserPageInfo pageInfo = new UserPageInfo(userList);
        model.addAttribute(Constants.STUDENT_PAGES, pageInfo);
        return Constants.STUDENT_PATH;
    }
    @RequestMapping("queryTeacher.do")
    public String queryTeacher(String userId,  Model model){
        List<ClassroomUser> userList = userService.selectTeacherByNameLikeOrId(userId);
        if (userList == null ) {
            return Constants.TEACHER_PATH;
        }
        UserPageInfo pageInfo = new UserPageInfo(userList);
        model.addAttribute(Constants.TEACHER_PAGES, pageInfo);
        return Constants.TEACHER_PATH;
    }


    /**
     *  从数据库中查询，然后返回所有学生的列表
     * @param session session 里放着pageInfo
     * @param model model
     * @return reload page
     */
    @RequestMapping("/students.do")
    public String getStudents(HttpSession session, Model model){
        System.out.println("admin students .do");
        List<ClassroomUser> users = userService.selectByTitle(Title.student);
        PageHelper.getList(session, model, 1, users, Constants.STUDENT_PAGES);
        return Constants.STUDENT_PATH;
    }

    /**
     *  从数据库中查询然后返回所有老师的列表
     * @param session 请求
     * @param model 将要返回的视图
     * @return 返回学生列表
     */
    @RequestMapping("/teachers.do")
    public String getTeachers(HttpSession session, Model model){
        List<ClassroomUser> userList = userService.selectByTitle(Title.teacher);
        PageHelper.getList(session, model, 1, userList, Constants.TEACHER_PAGES);
        return Constants.TEACHER_PATH;
    }

    @RequestMapping("freezeStudentById.do")
    public String freezeStuById(HttpSession session, Model model, String id) {
        return updatePrivilege(session, model, id, Title.student, Privilege.none);
    }

    @RequestMapping("freezeTeacherById.do")
    public String freezeTeaById(HttpSession session, Model model, String id) {
        return updatePrivilege(session, model, id, Title.teacher, Privilege.none);
    }

    @RequestMapping("unfreezeTeacherById.do")
    public String unfreezeTeacher(HttpSession session, Model model, String id){
        return updatePrivilege(session, model, id, Title.teacher, Privilege.senior);
    }
    @RequestMapping("unfreezeStudentById.do")
    public String unfreezeStudent(HttpSession session, Model model, String id){
        return updatePrivilege(session, model, id, Title.student, Privilege.normal);
    }

    @RequestMapping("changeStudentPage.do")
    public String studentChangePage(HttpSession session, Model model, String pageNum) {
        Integer row = null;
        if (!StringUtils.isEmpty(pageNum) && Util.isNumeric(pageNum.trim())) {
            row = Integer.parseInt(pageNum.trim());
        }
        noReloadData(session, row, model, Constants.STUDENT_PAGES);
        return Constants.STUDENT_PATH;
    }

    @ResponseBody
    @RequestMapping("resetPassword.do")
    public String resetPassword(String id) {
        if (StringUtils.isEmpty(id) && !Util.isNumeric(id)) {
            return " {\"status\": \"error\"} ";
        }
        // update
        ClassroomUser user = new ClassroomUser();
        user.setId(Long.parseLong(id));
        user.setPassword(Constants.DEFAULT_PASSWORD);
        userService.update(user);
        return  " {\"status\": \"success\"} ";
    }

    @RequestMapping("changeTeacherPage.do")
    public String teacherChangePage(HttpSession session, Model model, String pageNum) {
        Integer row = null;
        if (!StringUtils.isEmpty(pageNum) && Util.isNumeric(pageNum)){
            row = Integer.parseInt(pageNum);
        }
        noReloadData(session, row, model, Constants.TEACHER_PAGES);
        return Constants.TEACHER_PATH;
    }

    @RequestMapping("deleteStudentById.do")
    public String deleteStudentById(String id, HttpSession session, Model model) {
        if (StringUtils.isEmpty(id) || !Util.isNumericStr(id)) {
            noReloadData(session, null, model, Constants.STUDENT_PAGES);
            return Constants.STUDENT_PATH;
        }

        // delete from database
        userService.deleteAll(id);

        // select from database
        List<ClassroomUser> userList = userService.selectByTitle(Title.student);
        PageHelper.getList(session, model, null, userList, Constants.STUDENT_PAGES);
        return Constants.STUDENT_PATH;
    }

    @RequestMapping("deleteTeacherById")
    public String deleteTeacherById(String id, HttpSession session, Model model) {
        if (StringUtils.isEmpty(id) || !Util.isNumericStr(id)) {
            this.noReloadData(session, null, model, Constants.TEACHER_PAGES);
            return Constants.TEACHER_PATH;
        }

        // delete from database
        userService.deleteAll(id);

        // select from database
        List<ClassroomUser> list = userService.selectByTitle(Title.teacher);
        PageHelper.getList(session, model, null, list, Constants.TEACHER_PAGES);
        return Constants.TEACHER_PATH;
    }

    private void noReloadData(HttpSession session, Integer row, Model model, String attrTag) {
        PageHelper.getList(session, model, row, null, attrTag);
    }



    private String updatePrivilege(HttpSession session, Model model, String id, Title title
            , Privilege privilege) {
        String attrTag;
        String turnPath;
        if (Title.teacher.equals(title)){
            attrTag = Constants.TEACHER_PAGES;
            turnPath = Constants.TEACHER_PATH;
        }else{
            attrTag = Constants.STUDENT_PAGES;
            turnPath = Constants.STUDENT_PATH;
        }


        if (StringUtils.isEmpty(id) || !Util.isNumericStr(id)) {
            noReloadData(session, null, model, attrTag);
            return turnPath;
        }

        // 进行更新
        userService.updateAllById(id, privilege);
        // 查询数据
        List<ClassroomUser> list = userService.selectByTitle(title);

        PageHelper.getList(session, model, null, list, attrTag);
        return turnPath;
    }

}
