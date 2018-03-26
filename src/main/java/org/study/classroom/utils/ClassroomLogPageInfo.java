package org.study.classroom.utils;

import org.study.classroom.model.ClassroomLog;

import java.util.Iterator;
import java.util.List;

public class ClassroomLogPageInfo extends AbstractPageInfo<ClassroomLog> {
    public ClassroomLogPageInfo(List<ClassroomLog> data) {
        super(data);
    }

    public void deleteById(Long id) {
        if (id == null) {
            return;
        }
        Iterator iterator = this.getData().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(id)) {
                iterator.remove();
                return;
            }
        }
    }
    @Override
    public void update(ClassroomLog original, ClassroomLog destination) {

    }
}
