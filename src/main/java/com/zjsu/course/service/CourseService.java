package com.zjsu.course.service;

import com.zjsu.course.exception.BusinessException;
import com.zjsu.course.exception.ResourceNotFoundException;
import com.zjsu.course.model.Course;
import com.zjsu.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * 课程业务逻辑层
 */
@Service
public class CourseService {
    
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(String id) {
    // 尝试按内部 id 查找，找不到时再尝试按 code 查找，兼容客户端传入 code 或 id 的情况
    if (id == null) {
        throw new ResourceNotFoundException("Course not found with id: " + id);
    }
    String key = id.trim();
    return courseRepository.findById(key)
        .orElseGet(() -> courseRepository.findByCode(key)
            .orElseThrow(() -> new ResourceNotFoundException("Course not found with id or code: " + id)));
    }

    public Course createCourse(Course course) {
        // 检查课程代码是否已存在
        if (courseRepository.findByCode(course.getCode()).isPresent()) {
            throw new BusinessException("Course code already exists: " + course.getCode());
        }
        
        // 生成唯一ID
        course.setId(UUID.randomUUID().toString());
        
        return courseRepository.save(course);
    }

    public Course updateCourse(String id, Course courseDetails) {
        Course course = getCourseById(id);
        
        // 检查新的课程代码是否与其他课程冲突
        if (!course.getCode().equals(courseDetails.getCode())) {
            if (courseRepository.findByCode(courseDetails.getCode()).isPresent()) {
                throw new BusinessException("Course code already exists: " + courseDetails.getCode());
            }
        }
        
        course.setCode(courseDetails.getCode());
        course.setTitle(courseDetails.getTitle());
        course.setInstructor(courseDetails.getInstructor());
        course.setSchedule(courseDetails.getSchedule());
        course.setCapacity(courseDetails.getCapacity());
        
        return courseRepository.save(course);
    }

    public void deleteCourse(String id) {
        Course course = getCourseById(id);
        courseRepository.deleteById(id);
    }

    public boolean existsById(String id) {
        if (id == null) return false;
        String key = id.trim();
        // 既支持按内部 id，也支持按 course code
        return courseRepository.existsById(key) || courseRepository.findByCode(key).isPresent();
    }

    public void incrementEnrolled(String courseId) {
        Course course = getCourseById(courseId);
        course.setEnrolled(course.getEnrolled() + 1);
        courseRepository.save(course);
    }

    public void decrementEnrolled(String courseId) {
        Course course = getCourseById(courseId);
        if (course.getEnrolled() > 0) {
            course.setEnrolled(course.getEnrolled() - 1);
            courseRepository.save(course);
        }
    }
}
