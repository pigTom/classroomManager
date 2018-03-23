package org.study.classroom.model;

import org.study.classroom.utils.Available;


public class Classroom {
    private Long id;
    private String buildingName;
    private String classroomName;
    private Integer classroomSeats;
    private Available available;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public Available getAvailable() {
        return available;
    }

    public void setAvailable(Available available) {
        this.available = available;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public Integer getClassroomSeats() {
        return classroomSeats;
    }

    public void setClassroomSeats(Integer classroomSeats) {
        this.classroomSeats = classroomSeats;
    }
}
