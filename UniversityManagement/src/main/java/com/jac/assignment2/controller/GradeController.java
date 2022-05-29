package com.jac.assignment2.controller;

import com.jac.assignment2.exception.RecordNotFoundException;
import com.jac.assignment2.model.*;
import com.jac.assignment2.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.*;

import java.util.*;
@Controller
public class GradeController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService service;
    @Autowired
    private GradeService gradeService;


    @GetMapping("/courseGrades/{id}")
    public String showcourseGrades(Model model, @PathVariable Long id) throws RecordNotFoundException {
        Course course=courseService.findCourseById(id);
        List<Grade> grades=course.getGrades();
        model.addAttribute("course",course);
        model.addAttribute("grades",grades);
        return "showGradesOfCourse";
    }

    @GetMapping("/courseStudentsForProfessor/{id}")
    public String showcourseStudentsForProfessor(Model model,@PathVariable Long id) throws RecordNotFoundException {
        Course course=courseService.findCourseById(id);
        Set<Student> students=course.getStudents();
        Grade grade=new Grade();
        grade.setCourse(course);
        model.addAttribute("course",course);
        model.addAttribute("students",students);
        model.addAttribute("Grade",grade);
        return "addGradesToCourse";
    }
    @PostMapping("/courseSt/grade/save/{id}")
    public String saveGrade(@ModelAttribute("grade") Grade grade,@PathVariable Long id, RedirectAttributes redirectAttributes) throws RecordNotFoundException {
        Student student=service.findStudentById(id);
        Course course=grade.getCourse();
        Grade savedGrade= gradeService.setGrade(student,course,grade);
        student.addGrade(savedGrade);
        course.addGrade(savedGrade);
        service.saveStudent(student);
        courseService.addCourse(course);
       long id1= course.getId();
        redirectAttributes.addAttribute("id", id1);
        return "redirect:/courseGrades/{id}";
    }

}
