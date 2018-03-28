package org.study.classroom.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.study.classroom.controller.PageHelper;
import org.study.classroom.model.Classroom;
import org.study.classroom.service.ClassroomService;
import org.study.classroom.utils.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.util.List;

@RequestMapping("user/classroom")
@Controller
public class UserClassroomController {

    // 用户界面请求 --------------------------------------
    @Resource
    private ClassroomService classroomService;
    @RequestMapping("doOrder.do")
    public String doOrder(String classroomId, HttpSession session, Model model){
        if (StringUtils.isEmpty(classroomId) || !Util.isNumeric(classroomId)){
            // 如果教室id不合法，就返回空闲教室查看
            return Constants.USER_CLASSROOM_PATH;
        }


        Long id = Long.parseLong(classroomId);
        BasePageInfo<Classroom> classroomPageInfo = (BasePageInfo<Classroom>) session.getAttribute
                (Constants.USER_CLASSROOM_PAGES);

        if (classroomPageInfo == null) {
            return Constants.USER_CLASSROOM_PATH;
        }


        List<Classroom> classrooms = classroomPageInfo.getData();
        for (Classroom classroom : classrooms) {
            if (classroom.getId().equals(id)) {
                model.addAttribute("classroom", classroom);
                return Constants.USER_ORDER_PATH;
            }
        }
        // 如果没有在classroom pageInfo 中找到教室，就返到空闲教室查看页面
        return  Constants.USER_CLASSROOM_PATH;
    }

    /**
     * 空闲教室查看
     * @param session 保存教室信息 (Classroom PageInfo) 的session
     * @param model 视图类
     * @return 用户进行空闲教室查看的页面
     */
    @RequestMapping("freeClassLook.do")
    public String freeClassLook(HttpSession session, Model model){
        List<Classroom> list = classroomService.getClassrooms();
        PageHelper.getList(session, model, 1, list, Constants.USER_CLASSROOM_PAGES);
        return Constants.USER_CLASSROOM_PATH;
    }
    @RequestMapping("queryClassroom.do")
    public String query(HttpSession session, String className,  Model model){
        String buildingName = Util.getName(className);
        System.out.println("buildingName"+buildingName);
        String name = Util.getNumber(className);
        System.out.println("classname"+name);
        if (StringUtils.isEmpty(buildingName) && StringUtils.isEmpty(name)) {
            return Constants.USER_CLASSROOM_PATH;
        }
        List<Classroom> list = classroomService.selectByLike(buildingName, name);
        PageHelper.getList(session, model, 1, list, Constants.USER_CLASSROOM_PAGES);
        return Constants.USER_CLASSROOM_PATH;
    }

//
//    // 不重新加载数据 attrTag为 教室页面数据
//    public void noReloadData(HttpSession session, Integer row, Model model) {
//        PageHelper.getList(session, model, row, null, Constants.USER_CLASSROOM_PAGES);
//    }
}
