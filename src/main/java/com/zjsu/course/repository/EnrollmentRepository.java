package com.zjsu.course.repository;

import com.zjsu.course.model.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 选课记录数据访问层
 */
@Repository
public class EnrollmentRepository {
    private final Map<String, Enrollment> enrollments = new ConcurrentHashMap<>();

    public List<Enrollment> findAll() {
        return new ArrayList<>(enrollments.values());
    }

    public Optional<Enrollment> findById(String id) {
        return Optional.ofNullable(enrollments.get(id));
    }

    public Enrollment save(Enrollment enrollment) {
        enrollments.put(enrollment.getId(), enrollment);
        return enrollment;
    }

    public void deleteById(String id) {
        enrollments.remove(id);
    }

    public boolean existsById(String id) {
        return enrollments.containsKey(id);
    }

    public List<Enrollment> findByCourseId(String courseId) {
        return enrollments.values().stream()
                .filter(enrollment -> enrollment.getCourseId().equals(courseId))
                .collect(Collectors.toList());
    }

    public List<Enrollment> findByStudentId(String studentId) {
        return enrollments.values().stream()
                .filter(enrollment -> enrollment.getStudentId().equals(studentId))
                .collect(Collectors.toList());
    }

    public boolean existsByCourseIdAndStudentId(String courseId, String studentId) {
        return enrollments.values().stream()
                .anyMatch(enrollment -> enrollment.getCourseId().equals(courseId) 
                        && enrollment.getStudentId().equals(studentId));
    }

    public long countByCourseId(String courseId) {
        return enrollments.values().stream()
                .filter(enrollment -> enrollment.getCourseId().equals(courseId))
                .count();
    }

    public long countByStudentId(String studentId) {
        return enrollments.values().stream()
                .filter(enrollment -> enrollment.getStudentId().equals(studentId))
                .count();
    }
}
