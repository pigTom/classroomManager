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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public boolean addUser(ClassroomUser user) {
        ClassroomUser user1 = userMapper.selectByUserId(user.getUserId());
        if (user1 != null) {
            return false;
        }
        if (user.getUserTitle().toString().equals("teacher")) {
            user.setPrivilege(Privilege.senior);
        }else{
            user.setPrivilege(Privilege.normal);
        }
        userMapper.insert(user);
        return true;
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
        String[] id = ids.split(",");
        Integer[] idi = new Integer[id.length];
        for (int i = 0; i<idi.length; i++) {
            idi[i] = Integer.parseInt(id[i]);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("idi", idi);
       return userMapper.deleteAllById(map);
    }


    public int updateAllById(String ids, Privilege privilege) {
        String[] idstr = ids.split(",");
        Integer[] idi = new Integer[idstr.length];
        for (int i = 0; i<idi.length; i++) {
            idi[i] = Integer.parseInt(idstr[i]);
        }
        System.out.println("service: id "+ids);
        HashMap<String, Object> map = new HashMap<>();
        map.put("ids", idi);
        map.put("privilege", privilege);
        return userMapper.updateAllPrivilege(map);
    }

    public int update(ClassroomUser user){
        return userMapper.update(user);
    }
    private List<ClassroomUser> selectByNameLikeOrId(String name, Title title) {
        // 如果是学号
        if (Util.isNumeric(name)) {
//            Long id = Long.parseLong(name);
            name = "%" + name + "%";
            List<ClassroomUser> user = userMapper.selectByUserIdLike(name);
            if (user == null) {
                return null;
            }
            return user;
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
