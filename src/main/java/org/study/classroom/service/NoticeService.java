package org.study.classroom.service;

import org.springframework.stereotype.Service;
import org.study.classroom.mapper.NoticeMapper;
import org.study.classroom.model.Notice;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NoticeService {
    @Resource
    private NoticeMapper noticeMapper;
    List<Notice> getNotices(Long adminId){
        return noticeMapper.getNoticesByPublisherId(adminId);
    }

    void addNotice(Notice notice){
        noticeMapper.addNotice(notice);
    }
}
