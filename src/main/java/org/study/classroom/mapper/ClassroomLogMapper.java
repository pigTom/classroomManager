package org.study.classroom.mapper;

import org.study.classroom.model.ClassroomLog;

import java.util.Date;
import java.util.List;

public interface ClassroomLogMapper{

    List<ClassroomLog> selectAllLogs();

    List<ClassroomLog> findLogsByClassroomId(Long id);

    List<ClassroomLog> findLogsByClassroomName(String name);

    List<ClassroomLog> findLogsByUserId(Long userId);

    int deleteAllLogsById(String id);

    int deleteLogById(Long id);

    void addLog(Long classroomId, Long userId, String logName, Date logDate, Integer logTime);

    List<ClassroomLog> selectLogsByIdAndDate(Long id, Date date);
}
