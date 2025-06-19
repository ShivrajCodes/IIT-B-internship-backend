package com.example.courses_api.service;

import com.example.courses_api.entity.Course;
import com.example.courses_api.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public ResponseEntity<?> createCourse(Course course) {
        List<Course> prerequisites = new ArrayList<>();
        for (Course prereq : course.getPrerequisites()) {
            Optional<Course> prereqCourse = courseRepository.findByCourseId(prereq.getCourseId());
            if (prereqCourse.isEmpty()) {
                return ResponseEntity.badRequest().body("Invalid prerequisite course ID: " + prereq.getCourseId());
            }
            prerequisites.add(prereqCourse.get());
        }
        course.setPrerequisites(prerequisites);
        courseRepository.save(course);
        return ResponseEntity.ok(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public ResponseEntity<?> getCourse(String courseId) {
        Optional<Course> course = courseRepository.findByCourseId(courseId);
        return course.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> deleteCourse(String courseId) {
        Optional<Course> courseOpt = courseRepository.findByCourseId(courseId);
        if (courseOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Course course = courseOpt.get();
        List<Course> allCourses = courseRepository.findAll();
        for (Course c : allCourses) {
            for (Course prereq : c.getPrerequisites()) {
                if (prereq.getCourseId().equals(courseId)) {
                    return ResponseEntity.status(409)
                            .body("Cannot delete. Course " + courseId + " is a prerequisite for " + c.getCourseId());
                }
            }
        }

        courseRepository.delete(course);
        return ResponseEntity.ok("Course deleted successfully.");
    }
}
