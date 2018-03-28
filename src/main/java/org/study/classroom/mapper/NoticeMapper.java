package org.study.classroom.mapper;

import org.study.classroom.model.Notice;

import java.util.List;

public interface NoticeMapper {
    List<Notice> getNoticesByPublisherId(Long id);

    List<Notice> getNotices();

    void addNotice(Notice notice);
}
