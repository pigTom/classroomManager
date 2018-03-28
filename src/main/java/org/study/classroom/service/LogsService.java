package org.study.classroom.service;

import org.springframework.stereotype.Service;
import org.study.classroom.mapper.ClassroomLogMapper;
import org.study.classroom.model.Classroom;
import org.study.classroom.model.ClassroomLog;
import org.study.classroom.utils.Util;
import sun.util.resources.CalendarData;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class LogsService {
    @Resource
    private ClassroomLogMapper logMapper;

    public List<ClassroomLog> selectAllLogs(){
        return logMapper.selectAllLogs();
    }


    public List<ClassroomLog> selectAllLogsByClassName(String name){
        return logMapper.findLogsByClassroomName(name);
    }

    public List<ClassroomLog> selectLogsByUserId(Long userId) {
        return logMapper.findLogsByUserId(userId);
    }

    public int deleteLogById(Long id) {
        return logMapper.deleteLogById(id);
    }

    public int deleteAllLogsById(String id) {
        if (Util.isNumericStr(id)) {
            return logMapper.deleteAllLogsById(id);
        }
        return 0;
    }

    public void addOrder(Long classId, String logName, Integer date, Integer time, Long userId){
        Long day = new Date().getTime()/1000/3600/24+date;
        logMapper.addLog(classId, userId, logName, new Date(24*1000*3600*day), time);
    }
    public List<ClassroomLog> getLogsByIdAndDate(Long id, Integer iDate){
        Long day = new Date().getTime()/1000/3600/24+iDate;
        return logMapper.selectLogsByIdAndDate(id, new Date(24*1000*3600*day));
    }

}
