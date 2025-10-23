package com.zjsu.course.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 课程实体类
 */
public class Course {
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("code")
    private String code;
    
    @JsonProperty("title")
    private String title;
    
    @JsonProperty("instructor")
    private Instructor instructor;
    
    @JsonProperty("schedule")
    private ScheduleSlot schedule;
    
    @JsonProperty("capacity")
    private Integer capacity;
    
    @JsonProperty("enrolled")
    private Integer enrolled;

    public Course() {
        this.enrolled = 0;
    }

    public Course(String id, String code, String title, Instructor instructor, 
                  ScheduleSlot schedule, Integer capacity) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.instructor = instructor;
        this.schedule = schedule;
        this.capacity = capacity;
        this.enrolled = 0;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public ScheduleSlot getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleSlot schedule) {
        this.schedule = schedule;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(Integer enrolled) {
        this.enrolled = enrolled;
    }
}
