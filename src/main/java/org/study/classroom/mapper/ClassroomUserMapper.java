package org.study.classroom.mapper;

import org.study.classroom.model.Classroom;
import org.study.classroom.model.ClassroomUser;
import org.study.classroom.utils.Privilege;
import org.study.classroom.utils.Title;

import javax.management.loading.PrivateClassLoader;
import java.util.List;

public interface ClassroomUserMapper{
    /**
     * query all classroom users
     * @return
     */
    List<ClassroomUser> selectAll();

    List<ClassroomUser> selectByTitle(Title title);
    /**
     * query classroom user by specified id
     * @return
     */
    ClassroomUser selectById(Long id);

    /**
     * query classroom users by user name,
     * result will be none or one element.
     * @param userName
     * @return
     */
    ClassroomUser selectByName(String userName);


    /**
     * insert a classroom user to database
     *
     * @param classroomUser
     * @return
     */
    int insert(ClassroomUser classroomUser);

    List<ClassroomUser> selectByNameLike(String userName);

    int update(ClassroomUser user);

    int deleteById(Long id);

    int deleteAllById(String ids);

    int updateAllPrivilege(Privilege privilege, String ids);
}
