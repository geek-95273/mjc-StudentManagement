package com.zjsu.course.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

/**
 * 学生实体类（简化版，不依赖validation注解）
 */
public class StudentSimple {
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("studentId")
    private String studentId;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("major")
    private String major;
    
    @JsonProperty("grade")
    private Integer grade;
    
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    public StudentSimple() {}

    public StudentSimple(String id, String studentId, String name, String major, 
                   Integer grade, String email, LocalDateTime createdAt) {
        this.id = id;
        this.studentId = studentId;
        this.name = name;
        this.major = major;
        this.grade = grade;
        this.email = email;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
