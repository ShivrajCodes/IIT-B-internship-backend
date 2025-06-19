package com.example.courses_api.entity;

import jakarta.persistence.*;

@Entity
public class CourseInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int year;
    private int semester;

    @ManyToOne
    private Course course;

    public Long getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public int getSemester() {
        return semester;
    }

    public Course getCourse() {
        return course;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
