package org.study.classroom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String login(String userName, String password, Model model) {
        System.out.println("------------------login---------------");
        if (StringUtils.isEmpty(userName)) {
            model.addAttribute("usernameError", Constants.USERNAME_EMPTY);
            return "adminLogin";
        }
        if (StringUtils.isEmpty(password)) {
            model.addAttribute("passwordError", Constants.PASSWORD_EMPTY);
            return "adminLogin";
        }

        String result = userService.login(userName, password);
        if (!Constants.LOGIN_OK.equals(result)) {
            model.addAttribute("loginError", result);
            return "user_login";
        }

        // login ok
        return "index";
    }

    @RequestMapping("/register.do")
    public String register(String userId, String password, String userTitle,
                           String userName, Model model) {
        if (StringUtils.isEmpty(userId)) {
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
        user.setUserId(userId);
        user.setPassword(password);
        user.setUserTitle(Title.valueOf(userTitle));
        userService.addUser(user);
        return "user_login";
    }

    @RequestMapping("queryStudent.do")
    public String query(String userId,  Model model){
        List<ClassroomUser> userList = userService.selectStudentByNameLikeOrId(userId);
        if (userList == null ) {
            return Constants.STUDENT_PATH;
        }
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
     *
     * @param request request
     * @param model model
     * @return reload page
     */
    @RequestMapping("/students.do")
    public String getStudents(HttpServletRequest request, Model model){
        return this.getList(request, model, 1, null, true, Title.student);
    }
    @RequestMapping("/teachers.do")
    public String getTeachers(HttpServletRequest request, Model model){
        return this.getList(request, model, 1, null, true, Title.teacher);
    }
//    /**
//     * id可以是单个id，
//     * @param id 可以是单个的id,或者是id1,id2...组成的字符串
//     * @param request HttpServletRequest
//     * @param model Spring Model
//     * @return studentList.jsp
//     */
//    @RequestMapping("deleteStudentById.do")
//    public String deleteById(String id, HttpServletRequest request, Model model) {
//        if (StringUtils.isEmpty(id)) {
//            return this.noReloadData(request, null, model, Title.student);
//        }
//        // delete from database
//        userService.deleteAll(id);
//
//        // delete from pageInfo
//        String[] idstr = id.split(",");
//        Long[] ids = new Long[idstr.length];
//        for(int i = 0; i<ids.length; i++) {
//            ids[i] = Long.parseLong(idstr[i]);
//        }
//        this.deleteFromPage(request, ids);
//
//        // reload page
//        return this.noReloadData(request, null,  model, Title.student);
//    }
    @RequestMapping("freezeStudentById.do")
    public String freezeStuById(HttpServletRequest request, Model model, String id) {
        return updatePrivilege(request, model, id, Title.student, Privilege.none);
    }

    @RequestMapping("freezeTeacherById.do")
    public String freezeTeaById(HttpServletRequest request, Model model, String id) {
        return updatePrivilege(request, model, id, Title.teacher, Privilege.none);
    }

    @RequestMapping("unfreezeTeacherById.do")
    public String unfreezeTeacher(HttpServletRequest request, Model model, String id){
        return updatePrivilege(request, model, id, Title.teacher, Privilege.senior);
    }
    @RequestMapping("unfreezeStudentById.do")
    public String unfreezeStudent(HttpServletRequest request, Model model, String id){
       return updatePrivilege(request, model, id, Title.student, Privilege.normal);
    }

    @RequestMapping("changeStudentPage.do")
    public String studentChangePage(HttpServletRequest request, Model model, String pageNum) {
        Integer row = null;
        if (StringUtils.isEmpty(pageNum)) {
            return this.noReloadData(request, null , model, Title.student);
        }
        try{
            row = Integer.parseInt(pageNum);
        }catch (Exception e){
            // do nothing
        }
        return noReloadData(request, row, model, Title.student);
    }

    @RequestMapping("changeTeacherPage.do")
    public String teacherChangePage(HttpServletRequest request, Model model, String pageNum) {
        Integer row = null;
        if (StringUtils.isEmpty(pageNum)) {
            return this.noReloadData(request, null , model, Title.teacher);
        }
        try{
            row = Integer.parseInt(pageNum);
        }catch (Exception e){
            // do nothing
        }
        return noReloadData(request, row, model, Title.teacher);
    }

    @RequestMapping("deleteStudentById.do")
    public String deleteStudentById(String id, HttpServletRequest request, Model model) {
        if (StringUtils.isEmpty(id) || !Util.isNumericStr(id)) {
            return this.noReloadData(request, null, model, Title.student);
        }

        // delete from database
        userService.deleteAll(id);

        // delete from pageInfo
        String[] idStr = id.split(",");
        Long[] ids = new Long[idStr.length];
        for(int i = 0; i < ids.length; i++) {
            ids[i] = Long.parseLong(idStr[i]);
        }
        this.deleteStudentFromPage(request, ids);
        return this.noReloadData(request, null, model, Title.student);
    }

    @RequestMapping("deleteTeacherById")
    public String deleteTeacherById(String id, HttpServletRequest request, Model model) {
        if (StringUtils.isEmpty(id) || !Util.isNumericStr(id)) {
            return this.noReloadData(request, null, model, Title.teacher);
        }

        // delete from database
        userService.deleteAll(id);

        // delete from pageInfo
        String[] idStr = id.split(",");
        Long[] ids = new Long[idStr.length];
        for(int i = 0; i < ids.length; i++) {
            ids[i] = Long.parseLong(idStr[i]);
        }
        this.deleteTeacherFromPage(request, ids);
        return this.noReloadData(request, null, model, Title.teacher);
    }

    private String noReloadData(HttpServletRequest request, Integer row, Model model, Title title) {
        return getList(request, model, row, null, false, title);
    }
    /**
     * row 是要显示的页数，如果row==null，则取当前页
     * userList是要被修改的page中的数据
     * @param request request
     * @param model model
     * @param row the row number to be shown on page
     * @param userList List<ClassroomUser> to be updated
     * @return return studentList.jsp to be loaded by js
     */
    private String getList(HttpServletRequest request, Model model,
                           Integer row, List<ClassroomUser> userList,
                           boolean reload, Title title) {
        String pageInfo;
        String returnPath;
        if (title.equals(Title.student)) {
            pageInfo = Constants.STUDENT_PAGES;
            returnPath = Constants.STUDENT_PATH;
        }else{
            pageInfo = Constants.TEACHER_PAGES;
            returnPath = Constants.TEACHER_PATH;
        }
        HttpSession session = request.getSession();
        UserPageInfo userPageInfo = (UserPageInfo) session.getAttribute(pageInfo);
        //Integer row = (Integer) session.getAttribute("row");

        if (reload || userPageInfo == null) {
            System.out.println("reload"+reload);
            List<ClassroomUser> users = userService.selectByTitle(title);
            // userPageIn maybe empty
            userPageInfo = new UserPageInfo(users);
            userPageInfo.setEvePageNum(Constants.PAGE_NUM);
            session.setAttribute(pageInfo, userPageInfo);
        }


        if (userPageInfo.getTotalPage() == 0) {
            return returnPath;
        }

        // 如果userList不为空, 则将userPageInfo中的数据进行更新
        if(userList != null && userList.size() > 0){
            for (ClassroomUser user : userList) {
                userPageInfo.updateById(user);
            }
        }
        if (row == null) {
            row = userPageInfo.getCurrPage();
        }
        // 得到user PageInfo，在jsp页面里通过studentList.getData()得到一个list。
        userPageInfo.setCurrPage(row);
        model.addAttribute(pageInfo, userPageInfo);
        return returnPath;
//         return "register";
    }

    private String updatePrivilege(HttpServletRequest request, Model model, String id, Title title
        , Privilege privilege) {
        System.out.println("user id: "+id);

        if (StringUtils.isEmpty(id)) {
            return noReloadData(request, null, model, title);
        }
        List<ClassroomUser> userList = new ArrayList<>();
        if(Util.isNumericStr(id)){
            // update user from database
            userService.updateAllById(id, privilege);

            // update user from page
            String[] ids = id.split(",");
            for (String userId : ids) {
                ClassroomUser user = new ClassroomUser();
                user.setId(Long.parseLong(userId));
                user.setPrivilege(privilege);
                userList.add(user);
            }
        }
        return getList(request, model, null, userList, false, title);
    }

    private void deleteStudentFromPage(HttpServletRequest request, Long[] ids) {
        HttpSession session = request.getSession();
        UserPageInfo userPageInfo = (UserPageInfo) session.getAttribute(Constants.STUDENT_PAGES);
        if (userPageInfo == null) {
            return;
        }

        for (Long id : ids) {
            userPageInfo.deleteById(id);
        }
    }

    private void deleteTeacherFromPage(HttpServletRequest request, Long[] ids) {
        HttpSession session = request.getSession();
        UserPageInfo userPageInfo = (UserPageInfo) session.getAttribute(Constants.TEACHER_PAGES);
        if (userPageInfo == null) {
            return;
        }

        for (Long id : ids) {
            userPageInfo.deleteById(id);
        }
    }

}
