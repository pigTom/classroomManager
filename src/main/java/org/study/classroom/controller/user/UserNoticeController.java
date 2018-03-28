package org.study.classroom.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.study.classroom.controller.PageHelper;
import org.study.classroom.mapper.NoticeMapper;
import org.study.classroom.model.ClassroomAdministrator;
import org.study.classroom.model.Notice;
import org.study.classroom.utils.Constants;
import org.study.classroom.utils.Util;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("user/notice")
public class UserNoticeController {

    @Resource
    private NoticeMapper noticeMapper;
    /**
     *
     * @param session 会在session中保存 course pageInfo
     * @param model 将pageInfo 传到jsp页面
     * @return 返回教室排课页面
     */
    @RequestMapping("notice.do")
    public String notice(HttpSession session, Model model){

        List<Notice> logs = noticeMapper.getNotices();
        PageHelper.getList(session, model, 1, logs, Constants.NOTICE_PAGE);
        return Constants.USER_NOTICE_PATH;
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
     * @param session 存放pageInfo在session中
     * @param model 存pageInfo在model中让前端调用
     * @param row 展现给用户的实际页面
     * @return 课程信息页面
     */
    @RequestMapping("changePage.do")
    public String changeCoursePage(HttpSession session, Model model,  String row) {
        Integer pageRow = null;
        if (row != null && Util.isNumeric(row.trim())) {
            pageRow = Integer.parseInt(row.trim());
        }
        PageHelper.getList(session, model, pageRow, null, Constants.NOTICE_PAGE);
        return Constants.USER_NOTICE_PATH;
    }

}
