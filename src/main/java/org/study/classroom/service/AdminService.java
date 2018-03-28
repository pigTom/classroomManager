package org.study.classroom.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.study.classroom.mapper.ClassroomAdminMapper;
import org.study.classroom.mapper.ClassroomUserMapper;
import org.study.classroom.model.ClassroomAdministrator;
import org.study.classroom.model.ClassroomUser;
import org.study.classroom.utils.Constants;
import org.study.classroom.utils.Privilege;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@Transactional
public class AdminService {
    @Inject
    private ClassroomAdminMapper adminMapper;
    @Inject
    private ClassroomUserMapper userMapper;

    public String login(HttpSession session, String name, String password) {

        // check if any exception happened
        if (name == null)
            return Constants.USERNAME_ERROR;
        if (password == null)
            return Constants.PASSWORD_ERROR;

        ClassroomAdministrator administrator = adminMapper.selectByName(name);
        if (administrator == null)
            return Constants.USERNAME_ERROR;

        if (!administrator.getPassword().equals(password))
            return Constants.PASSWORD_ERROR;

        session.setAttribute("user", administrator);
        return Constants.LOGIN_OK;
    }

    public boolean freezeUser(Long id){
        ClassroomUser user = new ClassroomUser();
        user.setId(id);
        user.setPrivilege(Privilege.none);
        int i = userMapper.update(user);
        if (i == 1) {
            return true;
        }
        return false;
    }

}
