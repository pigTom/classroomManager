package org.study.classroom.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.study.classroom.model.ClassroomUser;

import java.util.List;

public class TestClassroomUser extends BaseSqlSessionFactory{
    @Test
    public void getAllUsers(){
        SqlSession sqlSession = getSqlSession();
        try{
            ClassroomUserMapper userMapper =
                    sqlSession.getMapper(ClassroomUserMapper.class);
            List<ClassroomUser> userList = userMapper.selectAll();
            Assert.assertTrue(userList.size() > 1);
            for (ClassroomUser user : userList) {
                System.out.println(user.getId()+", "+user.getUserName()+","+
                        user.getPrivilege());
            }
        }finally {
            sqlSession.close();
        }

    }
    @Test
    public void getUserBy(){
        SqlSession sqlSession = getSqlSession();
        try{
            ClassroomUserMapper userMapper = sqlSession.getMapper(ClassroomUserMapper.class);
            ClassroomUser user = userMapper.selectByUserId(1L);
            Assert.assertNotNull(user);
            System.out.println("user name: " + user.getPassword());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void getUserName(){
        SqlSession sqlSession = getSqlSession();
        try{
            ClassroomUserMapper userMapper = sqlSession.getMapper(ClassroomUserMapper.class);
            ClassroomUser userList = userMapper.selectByName("李主任");

        }finally {
            sqlSession.close();
        }
    }
}
