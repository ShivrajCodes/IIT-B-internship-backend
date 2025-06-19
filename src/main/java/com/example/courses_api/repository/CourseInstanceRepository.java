package com.example.courses_api.repository;

import com.example.courses_api.entity.CourseInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface CourseInstanceRepository extends JpaRepository<CourseInstance, Long> {
    List<CourseInstance> findByYearAndSemester(int year, int semester);

    Optional<CourseInstance> findByYearAndSemesterAndCourse_CourseId(int year, int semester, String courseId);
}
