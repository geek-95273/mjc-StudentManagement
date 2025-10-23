package com.zjsu.course.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

/**
 * 选课记录实体类
 */
public class Enrollment {
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("courseId")
    private String courseId;
    
    @JsonProperty("studentId")
    private String studentId;
    
    @JsonProperty("enrolledAt")
    private LocalDateTime enrolledAt;

    public Enrollment() {}

    public Enrollment(String id, String courseId, String studentId, LocalDateTime enrolledAt) {
        this.id = id;
        this.courseId = courseId;
        this.studentId = studentId;
        this.enrolledAt = enrolledAt;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }

    public void setEnrolledAt(LocalDateTime enrolledAt) {
        this.enrolledAt = enrolledAt;
    }
}
