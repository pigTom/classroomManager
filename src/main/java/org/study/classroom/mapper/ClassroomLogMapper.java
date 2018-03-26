package org.study.classroom.mapper;

import org.study.classroom.model.ClassroomLog;

import java.util.List;

public interface ClassroomLogMapper{

    List<ClassroomLog> selectAllLogs();

    List<ClassroomLog> findLogsByClassroomId(Long id);

    int deleteAllLogsById(String id);

    int deleteLogById(Long id);
}
