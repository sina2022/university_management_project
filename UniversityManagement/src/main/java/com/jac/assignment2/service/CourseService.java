package com.jac.assignment2.service;

import com.jac.assignment2.model.*;
import com.jac.assignment2.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class CourseService {
    @Autowired
    CourseRepository repository;

    public List<Course> getAllCourses(){
        List<Course> courses=(List<Course>)repository.findAll();
        return courses;
    }

    public void addCourse(Course course){
        repository.save(course);
    }

    public Course findCourseById(Long id){
        Course course=repository.findById(id).get();
        return course;
    }
}
