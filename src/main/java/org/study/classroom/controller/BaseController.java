package org.study.classroom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
@Controller
public class BaseController {
    @RequestMapping("user/logout.do")
    public String logout(HttpSession session){
        session.setMaxInactiveInterval(0);
        return "user";
    }

    @RequestMapping("admin/logout.do")
    public String adminLogout(HttpSession session){
        session.setMaxInactiveInterval(0);
        return "adminLogin";
    }
}
