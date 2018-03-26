package org.study.classroom.controller;

import org.study.classroom.model.ClassroomUser;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/3/25.
 */
public class SafeCheck {
    public static String checkSession(HttpSession session) {
        ClassroomUser user = (ClassroomUser)session.getAttribute("user");
        if (user == null){
            return "user_login";
        }else{
            return null;
        }
    }
}
