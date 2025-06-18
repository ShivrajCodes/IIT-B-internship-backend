package com.example.courses_api.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseId;
    private String title;
    private String description;

    @ManyToMany
    private List<Course> prerequisites = new ArrayList<>();
}
