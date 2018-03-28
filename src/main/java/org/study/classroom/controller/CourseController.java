package org.study.classroom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.study.classroom.model.ClassroomLog;
import org.study.classroom.service.LogsService;
import org.study.classroom.utils.Constants;
import org.study.classroom.utils.Util;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("admin/classroom")
public class CourseController {

    @Resource
    private LogsService logsService;
    @RequestMapping("course.do")
    public String classCourse(HttpSession session, Model model){
        List<ClassroomLog> logs = logsService.selectAllLogs();
        PageHelper.getList(session, model, 1, logs, Constants.USER_COURSE_PAGES);
        return Constants.COURSE_PATH;
    }

    @RequestMapping("userLookCourse.do")
    public String userLookCourse(HttpSession session, Model model){


        List<ClassroomLog> logs = logsService.selectAllLogs();
        PageHelper.getList(session, model, 1, logs, Constants.USER_COURSE_PAGES);
        return Constants.USER_COURSE_PATH;
    }
    @RequestMapping("deleteAllOrdersById.do")
    public String deleteOrdersById(HttpSession session, Model model, String logId){
        if (StringUtils.isEmpty(logId) || Util.isNumericStr(logId)) {
            PageHelper.getList(session, model, null, null, Constants.USER_COURSE_PAGES);
            return Constants.USER_COURSE_PATH;
        }
        // delete from database
        if(Util.isNumeric(logId)){
            logsService.deleteLogById(Long.parseLong(logId));
        }else{

            logsService.deleteAllLogsById(logId);
        }


        // select from database
        List<ClassroomLog> logs = logsService.selectAllLogs();
        PageHelper.getList(session,model,null, logs, Constants.USER_CLASSROOM_PAGES);
        return Constants.USER_COURSE_PATH;
    }

}
