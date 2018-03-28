package org.study.classroom.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.study.classroom.controller.PageHelper;
import org.study.classroom.model.ClassroomLog;
import org.study.classroom.model.ClassroomUser;
import org.study.classroom.service.LogsService;
import org.study.classroom.utils.Constants;
import org.study.classroom.utils.Util;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("user/course")
@Controller
public class UserCourseController {

    @Resource
    private LogsService logsService;
    /**
     * 用户课程查看
     * @param session 会在session中保存 course pageInfo
     * @param model 将pageInfo 传到jsp页面
     * @return 返回教室排课页面
     */
    @RequestMapping("course.do")
    public String classCourse(HttpSession session, Model model){
        List<ClassroomLog> logs = logsService.selectAllLogs();
        PageHelper.getList(session, model, 1, logs, Constants.USER_COURSE_PAGES);
        return Constants.USER_COURSE_PATH;
    }

    /**
     * 返回空闲教室查看这个页面
     * @param session request
     * @param model model
     * @return 管理员界面的空闲教室查看
     */
    @RequestMapping("returnClassroom.do")
    public String returnFreeClassroom(HttpSession session, Model model){
        PageHelper.getList(session, model, null, null, Constants.USER_CLASSROOM_PAGES);
        return  Constants.USER_CLASSROOM_PATH;
    }

    /**
     *
     * @param className 教室名
     * @param session session中将存入ClassroomLog page Info
     * @param model 在model中存入pageInfo对象
     * @return 返回查询后的课程页面
     */
    @RequestMapping("queryCourse.do")
    public String queryCourse(String className, HttpSession session, Model model) {
        if (StringUtils.isEmpty(className)) {
            return Constants.USER_COURSE_PATH;
        }
        List<ClassroomLog> logs = logsService.selectAllLogsByClassName(className);
        PageHelper.getList(session, model, 1, logs, Constants.USER_COURSE_PAGES);
        return Constants.USER_COURSE_PATH;
    }


    /**
     *
     * @param session 存放pageInfo在session中
     * @param model 存pageInfo在model中让前端调用
     * @param row 展现给用户的实际页面
     * @return 课程信息页面
     */
    @RequestMapping("changeCoursePage.do")
    public String changeCoursePage(HttpSession session, Model model,  String row) {
        Integer pageRow = null;
        if (row != null && Util.isNumeric(row.trim())) {
            pageRow = Integer.parseInt(row.trim());
        }
        PageHelper.getList(session, model, pageRow, null, Constants.USER_COURSE_PAGES);
        return Constants.USER_COURSE_PATH;
    }


    /// ---------------- below order --------------------------
    @ResponseBody
    @RequestMapping("orderClass.do")
    public String orderClass(HttpSession session, String classId, String logName, String orderDate, String orderTime){
        if (StringUtils.isEmpty(classId) || !Util.isNumeric(classId.trim()) ||
                StringUtils.isEmpty(orderDate) || !Util.isNumeric(orderDate.trim()) ||
                StringUtils.isEmpty(orderTime) || !Util.isNumeric(orderTime.trim()) ||
                StringUtils.isEmpty(logName)) {
            return "error";
        }
        ClassroomUser user = (ClassroomUser)session.getAttribute("user");
        Long classId1 = Long.parseLong(classId.trim());
        Long userId = user.getId();
        Integer date = Integer.parseInt(orderDate.trim());
        Integer time = Integer.parseInt(orderTime.trim());
        if (date < 0)
            return "error";

        if (time < 1 || time > 5){
            return "error";
        }
        // add order
        logsService.addOrder(classId1,logName, date, time, userId);
        return "success";
    }

    @ResponseBody
    @RequestMapping("findFreeOrderTime.do")
    public String findFreeOrderTime(String classroomId, String date) {
        System.out.println("classroomId "+classroomId + ", data: " + date);

        if (StringUtils.isEmpty(classroomId) || !Util.isNumeric(classroomId.trim()) ||
            StringUtils.isEmpty(date) || !Util.isNumeric(date.trim())) {
            return "0";
        }

        List<String> list = new ArrayList<>(5);
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        Long id = Long.parseLong(classroomId.trim());
        Integer iDate = Integer.parseInt(date.trim());
        // select from database
        List<ClassroomLog> logs = logsService.getLogsByIdAndDate(id, iDate);
        if (logs == null) {
            String s = list.toString();
            return s.substring(1, s.length()-1);
        }else {
            for (ClassroomLog log : logs) {
                list.remove(log.getLogTime());
            }

            if (list.size() > 0)
            {
                String s = list.toString();
                return s.substring(1, s.length() - 1);
            }
            return "0";
        }
    }


}
