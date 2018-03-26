package org.study.classroom.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.study.classroom.mapper.ClassroomUserMapper;
import org.study.classroom.model.ClassroomAdministrator;
import org.study.classroom.model.ClassroomUser;
import org.study.classroom.utils.*;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class UserService {
    @Resource
    private ClassroomUserMapper userMapper;
    public String login(String username, String password, HttpSession session) {

        // check if any exception happened
        if (username == null || !Util.isNumeric(username))
            return Constants.USERNAME_ERROR;
        if (password == null)
            return Constants.PASSWORD_ERROR;
        Long userId = Long.parseLong(username);
        ClassroomUser user = userMapper.selectByUserId(userId);
        // username error
        if (user == null)
            return Constants.ID_ERROR;
        // password error
        if (!user.getPassword().equals(password))
            return Constants.PASSWORD_ERROR;

        // 将用户信息存到session中
        session.setAttribute("user", user);
        return Constants.LOGIN_OK;
    }

    public void addUser(ClassroomUser user) {
        userMapper.insert(user);
    }

    public List<ClassroomUser> selectByTitle(Title title){
        List<ClassroomUser> userList = userMapper.selectByTitle(title);
        System.out.println("---- at user service----"+ userList);
        return userList;
    }

    public void deleteById(Long id){
        userMapper.deleteById(id);
    }

    public int deleteAll(String ids) {
       return userMapper.deleteAllById(ids);
    }

//    public int freezeById(Long id) {
//        ClassroomUser user = new ClassroomUser();
//        user.setId(id);
//        user.setPrivilege(Privilege.none);
//        return userMapper.update(user);
//    }

//    public int freezeAllById(String ids) {
//        if (ids.endsWith(",")) {
//            ids = ids.substring(0, ids.length() - 1);
//        }
//        return userMapper.updateAllPrivilege(Privilege.none, ids);
//    }

    public int updateAllById(String ids, Privilege privilege) {
        if (ids.endsWith(",")) {
            ids = ids.substring(0, ids.length() - 1);
        }
        System.out.println("service: id"+ids);
        return userMapper.updateAllPrivilege(privilege, ids);
    }

    public int update(ClassroomUser user){
        return userMapper.update(user);
    }
    private List<ClassroomUser> selectByNameLikeOrId(String name, Title title) {
        // 如果是学号
        if (Util.isNumeric(name)) {
            Long id = Long.parseLong(name);
            ClassroomUser user = userMapper.selectByUserId(id);
            if (user == null) {
                return null;
            }
            List<ClassroomUser> userList = new ArrayList<>();
            userList.add(user);
            return userList;
        }
        // 输入的是姓名
        return userMapper.selectByNameLike("%" + name + "%");
    }

    public List<ClassroomUser> selectStudentByNameLikeOrId(String name) {
        return selectByNameLikeOrId(name, Title.student);
    }
    public List<ClassroomUser> selectTeacherByNameLikeOrId(String name) {
        return selectByNameLikeOrId(name, Title.teacher);
    }

    public ClassroomUser selectUserById(Long id){
        return userMapper.selectById(id);
    }
}
