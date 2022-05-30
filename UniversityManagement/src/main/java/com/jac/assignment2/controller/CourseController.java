package com.jac.assignment2.controller;

import com.fasterxml.jackson.databind.*;
import com.jac.assignment2.exception.RecordNotFoundException;
import com.jac.assignment2.model.*;
import com.jac.assignment2.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class CourseController {

    @Autowired
     private   CourseService service;

    @Autowired
    private   StudentService studentService;

    private final ObjectMapper mapper= new ObjectMapper();


    @GetMapping("/course")
    public String getAllCourses(Model model){
       List<Course> courses= service.getAllCourses();
       model.addAttribute("courses",courses);
        return "allCourses";
    }
    @GetMapping("/addCourse")
    public String addCourse(Model model){
        Course course=new Course();
        model.addAttribute("course",course);
        return"addNewCourse";
    }

    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute("course") Course course){
        service.addCourse(course);
        return "redirect:/course";
    }

    @GetMapping("/addCourse/{id}")
    public String addCourseToStudent(@PathVariable Long id ,Model model) throws RecordNotFoundException {
        Student student=studentService.findStudentById(id);
       List<Course> courses=service.getAllCourses();
       Student newStudent=new Student();
        model.addAttribute("courses",courses);
        model.addAttribute("student",student);
        model.addAttribute("newStudent",newStudent);
        return "addCourseToStudent";

    }

    @PostMapping("/saveCourseToStudent/{id}")
    public String saveCourseToStudent(@ModelAttribute("newStudent") Student newStudent,Long id) throws RecordNotFoundException {
        Student dbStudent=studentService.findStudentById(id);
        Course course=newStudent.getCourses().stream().findFirst().get();
        dbStudent.getCourses().add(course);
        course.getStudents().add(dbStudent);
      studentService.saveStudent(dbStudent);
      //Set<Course> courses= student.getCourses();
       return "redirect:/students";
    }

    @GetMapping("/addCourses/{id}")
    public String addStudentToCourse(@PathVariable Long id ,Model model) throws RecordNotFoundException {
        Student student=studentService.findStudentById(id);
        List<Course> coursesList=new ArrayList<>();
        Student newStudent=new Student();
        List<Course> courses=service.getAllCourses();
        model.addAttribute("courses",courses);
        model.addAttribute("coursesList",coursesList);
        model.addAttribute("student",student);
        model.addAttribute("newStudent",newStudent);
        return "addStudentsToCourse";

    }
    @PostMapping("/saveCoursesToStudent/{id}")
    public String saveCoursesToStudent(@ModelAttribute("coursesList") List<Course> coursesList,@PathVariable Long id) throws RecordNotFoundException {
        Student dbStudent=studentService.findStudentById(id);
        List<Course> courses= coursesList;
        for (Course course:courses){
            dbStudent.getCourses().add(course);
        }
//        dbStudent.getCourses().add(course);
//        course.getStudents().add(dbStudent);
        studentService.saveStudent(dbStudent);
        //Set<Course> courses= student.getCourses();
        return "redirect:/students";
    }

}
