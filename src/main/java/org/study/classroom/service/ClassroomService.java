package org.study.classroom.service;


import org.springframework.stereotype.Service;
import org.study.classroom.mapper.ClassroomLogMapper;
import org.study.classroom.mapper.ClassroomMapper;
import org.study.classroom.model.Classroom;
import org.study.classroom.model.ClassroomLog;
import org.study.classroom.utils.Available;
import org.study.classroom.utils.Util;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class ClassroomService {
    @Resource
    private ClassroomMapper classroomMapper;
    @Resource
    private ClassroomLogMapper logMapper;
    public List<Classroom> getClassrooms(){
        return classroomMapper.selectAllClassrooms();
    }

    public int updateAllById(String id, Available available) {
        if (id.endsWith(",")) {
            id = id.substring(0, id.length() - 1);
        }
        System.out.println("service: id"+id);
        return classroomMapper.updateAllAvailable(available, id);
    }

    public int deleteAllById(String id) {
        if (Util.isNumericStr(id)) {
            return classroomMapper.deleteAllClassroomById(id);
        }
        // do nothing
        return 0;
    }

    public List<Classroom> selectByLike(String buildingName, String classroomName) {
        System.out.println("buildingName"+buildingName+": "+"classroomName"+classroomName);
        HashMap<String, String> map = new HashMap<>();
        map.put("buildingName", buildingName);
        map.put("classroomName", classroomName);
        return classroomMapper.selectClassroomsByLike(map);
    }

    public List<ClassroomLog> selectLogsByClassroomId(Long id){
        return logMapper.findLogsByClassroomId(id);
    }
}
