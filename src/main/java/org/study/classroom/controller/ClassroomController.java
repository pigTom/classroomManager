package org.study.classroom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.study.classroom.model.Classroom;
import org.study.classroom.model.ClassroomLog;
import org.study.classroom.service.ClassroomService;
import org.study.classroom.utils.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.ArrayList;
@RequestMapping("/classroom")
@Controller
public class ClassroomController {
    @Resource
    private ClassroomService classroomService;
    @RequestMapping("classroom.do")
    public String classroom(HttpServletRequest request, Model model){
        return this.getList(request, model, 1, null, true);
    }

    @RequestMapping("queryClassroom.do")
    public String query(String classroomName,  Model model){
        String buildingName = Util.getName(classroomName);
        System.out.println("buildingName"+buildingName);
        String name = Util.getNumber(classroomName);
        System.out.println("classname"+name);
        if (StringUtils.isEmpty(buildingName) && StringUtils.isEmpty(name)) {
            return Constants.CLASSROOM_PATH;
        }
        List<Classroom> classrooms = classroomService.selectByLike(buildingName, name);
        ClassroomPageInfo pageInfo = new ClassroomPageInfo(classrooms);
        model.addAttribute(Constants.CLASSROOM_PAGES, pageInfo);
        return Constants.CLASSROOM_PATH;
    }


    @RequestMapping("freezeClassroomById.do")
    public String freezeStuById(HttpServletRequest request, Model model, String id) {
        return updateAvailable(request, model, id, Available.no);
    }

    @RequestMapping("unfreezeClassroomById.do")
    public String unfreezeStudent(HttpServletRequest request, Model model, String id){
        return updateAvailable(request, model, id, Available.yes);
    }

    @RequestMapping("changeClassroomPage.do")
    public String classroomChangePage(HttpServletRequest request, Model model, String pageNum) {
        Integer row = null;
        if (StringUtils.isEmpty(pageNum)) {
            return this.noReloadData(request, null , model);
        }
        try{
            row = Integer.parseInt(pageNum);
        }catch (Exception e){
            // do nothing
        }
        return noReloadData(request, row, model);
    }



    @RequestMapping("deleteClassroomById.do")
    public String deleteClassById(String id, HttpServletRequest request, Model model) {
        if (StringUtils.isEmpty(id) || !Util.isNumericStr(id)) {
            return this.noReloadData(request, null, model);
        }

        // delete from database
        classroomService.deleteAllById(id);

        // delete from pageInfo
        String[] idStr = id.split(",");
        Long[] ids = new Long[idStr.length];
        for(int i = 0; i < ids.length; i++) {
            ids[i] = Long.parseLong(idStr[i]);
        }
        this.deleteClassroomFromPage(request, ids);
        return this.noReloadData(request, null, model);
    }

    /**
     * 返回空闲教室查看这个页面
     * @param request request
     * @param model model
     * @return front page
     */
    @RequestMapping("returnFreeClassroom.do")
    public String returnFreeClassroom(HttpServletRequest request, Model model){
        return this.noReloadData(request, null, model);
    }

    @RequestMapping("intoOrder.do")
    public String intoOrder(String id, HttpServletRequest request, Model model){
        if (StringUtils.isEmpty(id) || !Util.isNumeric(id)) {
            return Constants.COURSE_PATH;
        }

        List<ClassroomLog> logs = classroomService.selectLogsByClassroomId(Long.parseLong(id));
        HttpSession session = request.getSession();
        ClassroomLogPageInfo pageInfo = new ClassroomLogPageInfo(logs);
       // session.setAttribute(Constants.COURSE_PAGES, pageInfo);
        model.addAttribute(Constants.COURSE_PAGES, pageInfo);
        return Constants.COURSE_PATH;
    }

    @RequestMapping("doOrder.do")
    public String doOrder(String classroomId, HttpSession session, Model model){
        if (StringUtils.isEmpty(classroomId) || !Util.isNumeric(classroomId)){
            return Constants.BASE_USER_PATH+"classroom_user";
        }
        Long id = Long.parseLong(classroomId);
        ClassroomPageInfo classroomPageInfo = (ClassroomPageInfo) session.getAttribute
                (Constants.CLASSROOM_PAGES);
        String s;
        if ((s = SafeCheck.checkSession(session)) != null){
            return s;
        }


        List<Classroom> classrooms = classroomPageInfo.getData();
        for (Classroom classroom : classrooms) {
            if (classroom.getId() == id) {
                model.addAttribute("classroom", classroom);
                return Constants.BASE_USER_PATH + "doOrder";
            }
        }
        return  Constants.BASE_USER_PATH+"classroom_user";
    }

    @RequestMapping("freeClassLook.do")
    public String freeClassLook(HttpSession session, Model model){

        List<Classroom> classrooms = classroomService.getClassrooms();
        // userPageIn maybe empty
        ClassroomPageInfo classroomPageInfo = new ClassroomPageInfo(classrooms);
        classroomPageInfo.setEvePageNum(Constants.PAGE_NUM);
        session.setAttribute(Constants.CLASSROOM_PAGES, classroomPageInfo);


        if (classroomPageInfo.getTotalPage() == 0) {
            return Constants.CLASSROOM_PATH;
        }

        // 得到user PageInfo，在jsp页面里通过studentList.getData()得到一个list。
        classroomPageInfo.setCurrPage(1);
        model.addAttribute(Constants.CLASSROOM_PAGES, classroomPageInfo);
        return Constants.BASE_USER_PATH+"classroom_user";
    }

    @RequestMapping("gotoClassroom.do")
    public String gotoClassroom(HttpServletRequest request, Model model) {
        return noReloadData(request, null, model);
    }


    public String noReloadData(HttpServletRequest request, Integer row, Model model) {
        return getList(request, model, row, null, false);
    }
    /**
     * row 是要显示的页数，如果row==null，则取当前页
     * userList是要被修改的page中的数据
     * @param request request
     * @param model model
     * @param row the row number to be shown on page
     * @param classroomList List<ClassroomUser> to be updated
     * @return return classroom.jsp to be loaded by js
     */
    private String getList(HttpServletRequest request, Model model,
                           Integer row, List<Classroom> classroomList,
                           boolean reload) {

        HttpSession session = request.getSession();
        ClassroomPageInfo classroomPageInfo = (ClassroomPageInfo) session.getAttribute
                (Constants.CLASSROOM_PAGES);
        //Integer row = (Integer) session.getAttribute("row");

        if (reload || classroomPageInfo == null) {
            System.out.println("reload"+reload);
            List<Classroom> classrooms = classroomService.getClassrooms();
            // userPageIn maybe empty
            classroomPageInfo = new ClassroomPageInfo(classrooms);
            classroomPageInfo.setEvePageNum(Constants.PAGE_NUM);
            session.setAttribute(Constants.CLASSROOM_PAGES, classroomPageInfo);
        }


        if (classroomPageInfo.getTotalPage() == 0) {
            return Constants.CLASSROOM_PATH;
        }

        // 如果userList不为空, 则将userPageInfo中的数据进行更新
        if(classroomList != null && classroomList.size() > 0){
            for (Classroom classroom : classroomList) {
                classroomPageInfo.updateById(classroom);
            }
        }
        if (row == null) {
            row = classroomPageInfo.getCurrPage();
        }
        // 得到user PageInfo，在jsp页面里通过studentList.getData()得到一个list。
        classroomPageInfo.setCurrPage(row);
        model.addAttribute(Constants.CLASSROOM_PAGES, classroomPageInfo);
        return Constants.CLASSROOM_PATH;
//         return "register";
    }

    private String updateAvailable(HttpServletRequest request, Model model, String id
            , Available available) {

        if (StringUtils.isEmpty(id)) {
            return noReloadData(request, null, model);
        }
        List<Classroom> userList = new ArrayList<>();
        if(Util.isNumericStr(id)){
            // update user from database
            classroomService.updateAllById(id, available);

            // update user from page
            String[] ids = id.split(",");
            for (String userId : ids) {
                Classroom classroom = new Classroom();
                classroom.setId(Long.parseLong(userId));
                classroom.setAvailable(available);
                userList.add(classroom);
            }
        }
        return getList(request, model, null, userList, false);
    }

    private void deleteClassroomFromPage(HttpServletRequest request, Long[] ids) {
        HttpSession session = request.getSession();
        UserPageInfo userPageInfo = (UserPageInfo) session.getAttribute(Constants.CLASSROOM_PAGES);
        if (userPageInfo == null) {
            return;
        }

        for (Long id : ids) {
            userPageInfo.deleteById(id);
        }
    }

}

