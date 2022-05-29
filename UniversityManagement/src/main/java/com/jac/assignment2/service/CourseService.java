package com.jac.assignment2.service;

import com.jac.assignment2.exception.RecordNotFoundException;
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

    public Course findCourseById(Long id) throws RecordNotFoundException {
        Optional<Course> course = repository.findById(id);
        if (course.isPresent()) {
            return course.get();
        } else {
            throw new RecordNotFoundException("There is no course of this id.");
        }
    }
}
