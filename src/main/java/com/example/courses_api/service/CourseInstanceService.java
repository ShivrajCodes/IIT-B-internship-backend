package com.example.courses_api.service;

import com.example.courses_api.entity.Course;
import com.example.courses_api.entity.CourseInstance;
import com.example.courses_api.repository.CourseRepository;
import com.example.courses_api.repository.CourseInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CourseInstanceService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseInstanceRepository instanceRepository;

    public ResponseEntity<?> createInstance(CourseInstance instance) {
        Optional<Course> course = courseRepository.findByCourseId(instance.getCourse().getCourseId());
        if (course.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid course ID: " + instance.getCourse().getCourseId());
        }
        instance.setCourse(course.get());
        instanceRepository.save(instance);
        return ResponseEntity.ok(instance);
    }

    public List<CourseInstance> getInstances(int year, int semester) {
        return instanceRepository.findByYearAndSemester(year, semester);
    }

    public ResponseEntity<?> getInstanceDetail(int year, int semester, String courseId) {
        Optional<CourseInstance> instance = instanceRepository.findByYearAndSemesterAndCourse_CourseId(year, semester,
                courseId);
        return instance.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> deleteInstance(int year, int semester, String courseId) {
        Optional<CourseInstance> instance = instanceRepository.findByYearAndSemesterAndCourse_CourseId(year, semester,
                courseId);
        if (instance.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        instanceRepository.delete(instance.get());
        return ResponseEntity.ok("Course instance deleted successfully.");
    }
}
