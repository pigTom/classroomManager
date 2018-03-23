package org.study.classroom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.study.classroom.service.AdminService;
import org.study.classroom.utils.Constants;

import javax.inject.Inject;


@Controller
@RequestMapping("admin")
public class AdminController {
    @Inject
    AdminService adminService;
    @RequestMapping("login.do")
    public String login(String adminName, String password, Model model){

        if (StringUtils.isEmpty(adminName)){
            model.addAttribute("idError", Constants.USERNAME_EMPTY);
            return "adminLogin";
        }
        if (StringUtils.isEmpty(password)){
            model.addAttribute("passwordError", Constants.PASSWORD_EMPTY);
            return "adminLogin";
        }

        String result = adminService.login(adminName, password);
        if (!Constants.LOGIN_OK.equals(result)){
            model.addAttribute("loginError", result);
            return "adminLogin";
        }

        // login ok
        return "WEB-INF/jsp/admin/admin";
    }

    @RequestMapping("freezeUser.do")
    public void freezeUser(String id){
        if(StringUtils.isEmpty(id)){
            throw new NullPointerException();
        }
        Long userId = Long.parseLong(id);


    }
}
