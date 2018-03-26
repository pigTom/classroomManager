package org.study.classroom.utils;

import org.study.classroom.model.Classroom;

import java.util.Iterator;
import java.util.List;

public class ClassroomPageInfo extends AbstractPageInfo<Classroom> {

    public ClassroomPageInfo(List<Classroom> users) {
        super(users);
    }
    public void updateById(Classroom classroom1){
        if (classroom1 == null) {
            return;
        }
        List<Classroom> data = this.getData();
        if (data==null || data.size()==0)
            return;
        Iterator<Classroom> iterator = data.iterator();
        while (iterator.hasNext()){
            Classroom classroom2 = iterator.next();
            if(classroom1.getId().equals(classroom2.getId())){

                update(classroom2, classroom1);
            }
        }
    }

    /**
     *
     * @param classroom1 to be updated
     * @param classroom2 supply attrs to update the classroom in this list
     */
    @Override
    public void update(Classroom classroom1, Classroom classroom2){
        if (classroom2.getClassroomName() != null) {
            classroom1.setClassroomName(classroom2.getClassroomName());
        }

        if (classroom2.getClassroomSeats() != null) {
            classroom1.setClassroomSeats(classroom2.getClassroomSeats());
        }

        if (classroom2.getAvailable() != null) {
            classroom1.setAvailable(classroom2.getAvailable());
        }

        if (classroom2.getBuildingName() != null) {
            classroom1.setBuildingName(classroom2.getBuildingName());
        }
    }

}
