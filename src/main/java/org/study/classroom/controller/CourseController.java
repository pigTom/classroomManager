package org.study.classroom.controller;

import com.sun.xml.internal.ws.resources.HttpserverMessages;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.study.classroom.model.ClassroomLog;
import org.study.classroom.service.LogsService;
import org.study.classroom.utils.ClassroomLogPageInfo;
import org.study.classroom.utils.Constants;
import org.study.classroom.utils.Util;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("classroom")
public class CourseController {

    @Resource
    private LogsService logsService;
    @RequestMapping("course.do")
    public String classCourse(HttpServletRequest request, Model model){
        return this.getList(request, model, 1,  true);
    }

    @RequestMapping("userLookCourse.do")
    public String userLookCourse(HttpSession session, Model model){


        List<ClassroomLog> classrooms = logsService.selectAllLogs();
        // userPageIn maybe empty
        ClassroomLogPageInfo logPageInfo = new ClassroomLogPageInfo(classrooms);
        logPageInfo.setEvePageNum(Constants.PAGE_NUM);
        session.setAttribute(Constants.COURSE_PAGES, logPageInfo);

        if (logPageInfo.getTotalPage() == 0) {
            return Constants.COURSE_PATH;
        }
        // 得到user PageInfo，在jsp页面里通过studentList.getData()得到一个list。
        logPageInfo.setCurrPage(1);
        model.addAttribute(Constants.COURSE_PAGES, logPageInfo);
        return Constants.BASE_USER_PATH + "course";
    }
    @RequestMapping("deleteAllOrdersById.do")
    public String deleteOrdersById(HttpServletRequest request, Model model, String logId){
        if (StringUtils.isEmpty(logId) || Util.isNumericStr(logId)) {
            return getList(request,  model, null, false);
        }
        // delete from database
        if(Util.isNumeric(logId)){
            logsService.deleteLogById(Long.parseLong(logId));
        }else{

            logsService.deleteAllLogsById(logId);
        }

        HttpSession session = request.getSession();
        ClassroomLogPageInfo logPageInfo = (ClassroomLogPageInfo) session.getAttribute
                (Constants.COURSE_PAGES);
        String[] ids = logId.split(",");
        for (String id : ids) {
            logPageInfo.deleteById(Long.parseLong(id));
        }
        return getList(request, model, null, false);
    }

    /**
     * row 是要显示的页数，如果row==null，则取当前页
     * userList是要被修改的page中的数据
     * @param request request
     * @param model model
     * @param row the row number to be shown on page
     * @return return classroom.jsp to be loaded by js
     */
    private String getList(HttpServletRequest request, Model model,
                           Integer row, boolean reload) {

        HttpSession session = request.getSession();
        ClassroomLogPageInfo logPageInfo = (ClassroomLogPageInfo) session.getAttribute
                (Constants.COURSE_PAGES);
        //Integer row = (Integer) session.getAttribute("row");

        if (reload || logPageInfo == null) {
            System.out.println("reload"+reload);
            List<ClassroomLog> classrooms = logsService.selectAllLogs();
            // userPageIn maybe empty
            logPageInfo = new ClassroomLogPageInfo(classrooms);
            logPageInfo.setEvePageNum(Constants.PAGE_NUM);
            session.setAttribute(Constants.COURSE_PAGES, logPageInfo);
        }


        if (logPageInfo.getTotalPage() == 0) {
            return Constants.COURSE_PATH;
        }

        if (row == null) {
            row = logPageInfo.getCurrPage();
        }
        // 得到user PageInfo，在jsp页面里通过studentList.getData()得到一个list。
        logPageInfo.setCurrPage(row);
        model.addAttribute(Constants.COURSE_PAGES, logPageInfo);
        return Constants.COURSE_PATH;
//         return "register";
    }
}
