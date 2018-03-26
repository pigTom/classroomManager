package org.study.classroom.service;

import org.springframework.stereotype.Service;
import org.study.classroom.mapper.ClassroomLogMapper;
import org.study.classroom.model.ClassroomLog;
import org.study.classroom.utils.Util;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LogsService {
    @Resource
    private ClassroomLogMapper logMapper;

    public List<ClassroomLog> selectAllLogs(){
        return logMapper.selectAllLogs();
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
}
