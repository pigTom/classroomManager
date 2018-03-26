package org.study.classroom.mapper;

import org.study.classroom.model.ClassroomAdministrator;

public interface ClassroomAdminMapper{
    /**
     * query one administrator by id
     * @param id
     * @return
     */
    ClassroomAdministrator selectById(Long id);

    /**
     * query one administrator by name
     * @param name
     * @return
     */
    ClassroomAdministrator selectByName(String name);


}
