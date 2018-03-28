package org.study.classroom.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.study.classroom.controller.PageHelper;
import org.study.classroom.model.ClassroomLog;
import org.study.classroom.model.ClassroomUser;
import org.study.classroom.service.LogsService;
import org.study.classroom.utils.Constants;
import org.study.classroom.utils.Util;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("user/activity")
@Controller
public class UserActivityController {
    @Resource
    private LogsService logsService;
    // -------------------  Activity  ----------------------//
    @RequestMapping("activity.do")
    public String activity(HttpSession session, Model model){

        ClassroomUser user = (ClassroomUser)session.getAttribute("user");

        if (user == null){
            // session invalid
            session.setMaxInactiveInterval(0);
            return "user_login";
        }
        Long userId = user.getId();
        // select from database
        List<ClassroomLog> list = logsService.selectLogsByUserId(userId);
        PageHelper.getList(session, model, 1, list, Constants.USER_ACTIVITY_PAGES);
        return Constants.USER_ACTIVITY_PATH;
    }

    @RequestMapping("changeActivityPage.do")
    public String changeActivityPage(HttpSession session, Model model,  String row) {
        Integer pageRow = null;
        if (row != null && Util.isNumeric(row.trim())) {
            pageRow = Integer.parseInt(row.trim());
        }
        PageHelper.getList(session, model, pageRow, null, Constants.USER_ACTIVITY_PAGES);
        return Constants.USER_ACTIVITY_PATH;
    }

    @RequestMapping("deleteOrdersById.do")
    public String deleteOrdersById(HttpSession session, Model model, String logId){
        System.out.println("logId: " + logId);
        if (StringUtils.isEmpty(logId) || !Util.isNumericStr(logId.trim())) {
            PageHelper.getList(session, model, null, null, Constants.USER_ACTIVITY_PAGES);
            return Constants.USER_ACTIVITY_PATH;
        }
        // delete from database
        if(Util.isNumeric(logId.trim())){
            logsService.deleteLogById(Long.parseLong(logId.trim()));
        }else{

            logsService.deleteAllLogsById(logId.trim());
        }

        // get user from session
        ClassroomUser user = (ClassroomUser) session.getAttribute("user");
        Long userId = user.getId();
        // select from database
        List<ClassroomLog> logs = logsService.selectLogsByUserId(userId);
        PageHelper.getList(session,model,null, logs, Constants.USER_ACTIVITY_PAGES);
        return Constants.USER_ACTIVITY_PATH;
    }
}
