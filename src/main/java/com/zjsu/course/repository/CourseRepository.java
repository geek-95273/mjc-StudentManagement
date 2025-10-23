package com.zjsu.course.repository;

import com.zjsu.course.model.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 课程数据访问层
 */
@Repository
public class CourseRepository {
    private final Map<String, Course> courses = new ConcurrentHashMap<>();

    public List<Course> findAll() {
        return new ArrayList<>(courses.values());
    }

    public Optional<Course> findById(String id) {
        return Optional.ofNullable(courses.get(id));
    }

    public Course save(Course course) {
        courses.put(course.getId(), course);
        return course;
    }

    public void deleteById(String id) {
        courses.remove(id);
    }

    public boolean existsById(String id) {
        return courses.containsKey(id);
    }

    public Optional<Course> findByCode(String code) {
        return courses.values().stream()
                .filter(course -> course.getCode().equals(code))
                .findFirst();
    }
}
