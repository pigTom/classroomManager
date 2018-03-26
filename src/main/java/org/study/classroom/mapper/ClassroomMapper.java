package org.study.classroom.mapper;

import org.study.classroom.model.Classroom;
import org.study.classroom.utils.Available;

import java.util.HashMap;
import java.util.List;

public interface ClassroomMapper {
    List<Classroom> selectAllClassrooms();

    List<Classroom> selectClassroomsByLike(HashMap<String, String> map);

    int updateAllAvailable(Available available, String id);

    int deleteClassroomById(Long id);

    int deleteAllClassroomById(String id);

}
