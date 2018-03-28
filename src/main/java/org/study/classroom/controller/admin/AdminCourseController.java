package org.study.classroom.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.study.classroom.controller.PageHelper;
import org.study.classroom.model.ClassroomLog;
import org.study.classroom.service.LogsService;
import org.study.classroom.utils.Constants;
import org.study.classroom.utils.Util;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("admin/course")
@Controller
public class AdminCourseController {
    @Resource
    private LogsService logsService;
    /**
     * 管理员课程查看
     * @param session 会在session中保存 course pageInfo
     * @param model 将pageInfo 传到jsp页面
     * @return 返回教室排课页面
     */
    @RequestMapping("course.do")
    public String classCourse(HttpSession session, Model model){
        List<ClassroomLog> logs = logsService.selectAllLogs();
        PageHelper.getList(session, model, 1, logs, Constants.COURSE_PAGES);
        return Constants.COURSE_PATH;
    }

    /**
     * 返回空闲教室查看这个页面
     * @param session request
     * @param model model
     * @return 管理员界面的空闲教室查看
     */
    @RequestMapping("returnClassroom.do")
    public String returnFreeClassroom(HttpSession session, Model model){
        PageHelper.getList(session, model, null, null, Constants.CLASSROOM_PAGES);
        return  Constants.CLASSROOM_PATH;
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
            return Constants.COURSE_PATH;
        }
        List<ClassroomLog> logs = logsService.selectAllLogsByClassName(className);
        PageHelper.getList(session, model, 1, logs, Constants.COURSE_PAGES);
        return Constants.COURSE_PATH;
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
        PageHelper.getList(session, model, pageRow, null, Constants.COURSE_PAGES);
        return Constants.COURSE_PATH;
    }

    /**
     * 管理员批量删除课程通过给定的多个id组成的字符串
     * @param session 将删除后的剩下的ClassroomLog放入pageInfo
     * @param model 将删除后的剩下的ClassroomLog放入pageInfo 再放入model给前端
     * @param logId 多个id组成的id字符串，比例“12121，2121，32323”表示有三个id
     * @return 返回删除过后的课程表
     */
    @RequestMapping("deleteOrdersById.do")
    public String deleteOrdersById(HttpSession session, Model model, String logId){
        System.out.println("logId: " + logId);
        if (StringUtils.isEmpty(logId) || !Util.isNumericStr(logId.trim())) {
            PageHelper.getList(session, model, null, null, Constants.COURSE_PAGES);
            return Constants.COURSE_PATH;
        }
        // delete from database
        if(Util.isNumeric(logId.trim())){
            logsService.deleteLogById(Long.parseLong(logId.trim()));
        }else{

            logsService.deleteAllLogsById(logId.trim());
        }

        // select from database
        List<ClassroomLog> logs = logsService.selectAllLogs();
        PageHelper.getList(session,model,null, logs, Constants.COURSE_PAGES);
        return Constants.COURSE_PATH;
    }

}
