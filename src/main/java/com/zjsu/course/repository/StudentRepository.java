package com.zjsu.course.repository;

import com.zjsu.course.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 学生数据访问层
 */
@Repository
public class StudentRepository {
    private final Map<String, Student> students = new ConcurrentHashMap<>();

    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }

    public Optional<Student> findById(String id) {
        return Optional.ofNullable(students.get(id));
    }

    public Student save(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    public void deleteById(String id) {
        students.remove(id);
    }

    public boolean existsById(String id) {
        return students.containsKey(id);
    }

    public Optional<Student> findByStudentId(String studentId) {
        return students.values().stream()
                .filter(student -> student.getStudentId().equals(studentId))
                .findFirst();
    }

    public boolean existsByStudentId(String studentId) {
        return students.values().stream()
                .anyMatch(student -> student.getStudentId().equals(studentId));
    }
}
