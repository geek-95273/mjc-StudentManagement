package com.zjsu.course.service;

import com.zjsu.course.exception.BusinessException;
import com.zjsu.course.exception.ResourceNotFoundException;
import com.zjsu.course.model.Enrollment;
import com.zjsu.course.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * 选课业务逻辑层
 */
@Service
public class EnrollmentService {
    
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private StudentService studentService;

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Enrollment getEnrollmentById(String id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + id));
    }

    public Enrollment createEnrollment(Enrollment enrollment) {
        // 手动验证必填字段
        if (enrollment.getCourseId() == null || enrollment.getCourseId().trim().isEmpty()) {
            throw new BusinessException("课程ID不能为空");
        }
        if (enrollment.getStudentId() == null || enrollment.getStudentId().trim().isEmpty()) {
            throw new BusinessException("学生ID不能为空");
        }
        
        // 验证课程是否存在
        String courseKey = enrollment.getCourseId() == null ? null : enrollment.getCourseId().trim();
        if (!courseService.existsById(courseKey)) {
            throw new ResourceNotFoundException("Course not found with id: " + enrollment.getCourseId());
        }
        
        // 验证学生是否存在
        String studentKey = enrollment.getStudentId() == null ? null : enrollment.getStudentId().trim();
        // StudentService supports checking by external studentId via existsByStudentId
        if (!studentService.existsByStudentId(studentKey)) {
            throw new ResourceNotFoundException("Student not found with studentId: " + enrollment.getStudentId());
        }
        
        // 检查是否已经选过这门课
        if (enrollmentRepository.existsByCourseIdAndStudentId(courseKey, studentKey)) {
            throw new BusinessException("Student has already enrolled in this course");
        }
        
        // 检查课程容量
    long currentEnrollments = enrollmentRepository.countByCourseId(courseKey);
        // 这里需要获取课程信息来检查容量，但为了简化，我们假设课程容量检查在Controller层处理
        
        // 生成唯一ID和选课时间
    enrollment.setId(UUID.randomUUID().toString());
        enrollment.setEnrolledAt(LocalDateTime.now());
        
    // Ensure we save trimmed keys in the enrollment record
    enrollment.setCourseId(courseKey);
    enrollment.setStudentId(studentKey);

    Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        
        // 更新课程的已选人数
        courseService.incrementEnrolled(enrollment.getCourseId());
        
        return savedEnrollment;
    }

    public void deleteEnrollment(String id) {
        Enrollment enrollment = getEnrollmentById(id);
        
        // 删除选课记录
        enrollmentRepository.deleteById(id);
        
        // 更新课程的已选人数
        courseService.decrementEnrolled(enrollment.getCourseId());
    }

    public List<Enrollment> getEnrollmentsByCourseId(String courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }

    public List<Enrollment> getEnrollmentsByStudentId(String studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    public boolean existsByCourseIdAndStudentId(String courseId, String studentId) {
        return enrollmentRepository.existsByCourseIdAndStudentId(courseId, studentId);
    }

    public long countByCourseId(String courseId) {
        return enrollmentRepository.countByCourseId(courseId);
    }
}
