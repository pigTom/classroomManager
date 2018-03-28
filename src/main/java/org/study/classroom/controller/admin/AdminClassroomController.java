package org.study.classroom.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.study.classroom.controller.PageHelper;
import org.study.classroom.model.Classroom;
import org.study.classroom.model.ClassroomLog;
import org.study.classroom.service.ClassroomService;
import org.study.classroom.utils.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.ArrayList;
@RequestMapping("/admin/classroom")
@Controller
public class AdminClassroomController {
    @Resource
    private ClassroomService classroomService;
    // 管理员界面请求-----------------------
    @RequestMapping("classroom.do")
    public String classroom(HttpSession session, Model model){
        List<Classroom> list = classroomService.getClassrooms();
        PageHelper.getList(session, model, 1, list, Constants.CLASSROOM_PAGES);
        return Constants.CLASSROOM_PATH;
    }

    /**
     * 管理员查看预定信息页面
     * @param id 教室Id
     * @param model 视图类
     * @return 返回预定教室的各个预定信息
     */
    @RequestMapping("intoOrder.do")
    public String intoOrder(String id,  Model model){
        if (StringUtils.isEmpty(id) || !Util.isNumeric(id)) {
            // 如果为空则回到教室页面
            return Constants.CLASSROOM_PATH;
        }

        List<ClassroomLog> logs = classroomService.selectLogsByClassroomId(Long.parseLong(id));
        BasePageInfo<ClassroomLog> pageInfo = new BasePageInfo<>(logs);
        model.addAttribute(Constants.COURSE_PAGES, pageInfo);
        return Constants.COURSE_PATH;
    }


    @RequestMapping("queryClassroom.do")
    public String query(HttpSession session, String className,  Model model){
        String buildingName = Util.getName(className);
        System.out.println("buildingName"+buildingName);
        String name = Util.getNumber(className);
        System.out.println("classname"+name);
        if (StringUtils.isEmpty(buildingName) && StringUtils.isEmpty(name)) {
            return Constants.CLASSROOM_PATH;
        }
        List<Classroom> classrooms = classroomService.selectByLike(buildingName, name);
        PageHelper.getList(session, model, 1, classrooms, Constants.CLASSROOM_PAGES);
        return Constants.CLASSROOM_PATH;
    }




    // 冻结教室
    @RequestMapping("freezeClassroomById.do")
    public String freezeStuById(HttpSession session, Model model, String id) {
        updateAvailable(session, model, id, Available.no, Constants.CLASSROOM_PAGES);
        return Constants.CLASSROOM_PATH;
    }

    // 解冻教室
    @RequestMapping("unfreezeClassroomById.do")
    public String unfreezeStudent(HttpSession session, Model model, String id){
        updateAvailable(session, model, id, Available.yes, Constants.CLASSROOM_PAGES);
        return Constants.CLASSROOM_PATH;
    }

    // 教室页面切换(分页)
    @RequestMapping("changeClassroomPage.do")
    public String classroomChangePage(HttpSession session, Model model, String pageNum) {
        Integer row = null;
        if (StringUtils.isEmpty(pageNum)) {
            this.noReloadData(session, null, model);
            return Constants.CLASSROOM_PATH;
        }
        try{
            row = Integer.parseInt(pageNum);
        }catch (Exception e){
            // do nothing
        }
        noReloadData(session, row, model);
        return Constants.CLASSROOM_PATH;
    }


    /**
     * 删除教室
     * @param id 教室Id
     * @param session 请求
     * @param model 视图
     * @return 返回教室被删除之后的教室页面
     */
    @RequestMapping("deleteClassroomById.do")
    public String deleteClassById(String id, HttpSession session, Model model) {
        if (StringUtils.isEmpty(id) || !Util.isNumericStr(id)) {
            this.noReloadData(session, null, model);
            return Constants.CLASSROOM_PAGES;
        }

        // delete from database
        classroomService.deleteAllById(id);
        //  不把数据从pageInfo中删除，而选择重新加载数据
//        // delete from pageInfo
//        String[] idStr = id.split(",");
//        Long[] ids = new Long[idStr.length];
//        for(int i = 0; i < ids.length; i++) {
//            ids[i] = Long.parseLong(idStr[i]);
//        }


        // select from database
        List<Classroom> list = classroomService.getClassrooms();
        PageHelper.getList(session, model, null, list, Constants.CLASSROOM_PAGES);
        return Constants.CLASSROOM_PATH;
    }


    // 不重新加载数据 attrTag为 教室页面数据
    private void noReloadData(HttpSession session, Integer row, Model model) {
        PageHelper.getList(session, model, row, null, Constants.CLASSROOM_PAGES);
    }


    private void updateAvailable(HttpSession session, Model model, String id
            , Available available, String attrTag) {

        if (StringUtils.isEmpty(id)) {
            noReloadData(session, null, model);
            return;
        }
//        List<Classroom> userList = new ArrayList<>();
        if(Util.isNumericStr(id)){
            // update user from database
            classroomService.updateAllById(id, available);

//            // update user from page
//            String[] ids = id.split(",");
//            for (String userId : ids) {
//                Classroom classroom = new Classroom();
//                classroom.setId(Long.parseLong(userId));
//                classroom.setAvailable(available);
//                userList.add(classroom);
//            }
        }
        // select from database
        List<Classroom> list = classroomService.getClassrooms();

        PageHelper.getList(session, model, null, list, attrTag);
    }

//    private void deleteClassroomFromPage(HttpSession session, Long[] ids) {
//        UserPageInfo userPageInfo = (UserPageInfo) session.getAttribute(Constants.CLASSROOM_PAGES);
//        if (userPageInfo == null) {
//            return;
//        }
//
//        for (Long id : ids) {
//            userPageInfo.deleteById(id);
//        }
//    }

}

