package com.jac.assignment2.controller;

import com.jac.assignment2.exception.RecordNotFoundException;
import com.jac.assignment2.model.Address;
import com.jac.assignment2.model.Course;
import com.jac.assignment2.model.Professor;
import com.jac.assignment2.model.Student;
import com.jac.assignment2.service.AddressService;
import com.jac.assignment2.service.CourseService;
import com.jac.assignment2.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;

@Controller
public class ProfessorController {

    @Autowired
    private ProfessorService service;
    @Autowired
    private AddressService addressService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/showProfessorList")
    public String getProfessors(Model model){
        List<Professor> professors=service.getProfessors();

        model.addAttribute("professors",professors);

        return "showProfessor";
    }

    @GetMapping("/addNewProfessor")
    public String addNewEmployee(Model model){
        Professor professor = new Professor();
        model.addAttribute("professor", professor);
        return "newProfessor";
    }

    @PostMapping("/saveProfessor")
    public String saveStudent(@Valid @ModelAttribute("professor") Professor saveProfessor, BindingResult result){

        if (result.hasErrors()){
            return "newProfessor";
        }
        service.saveProfessor(saveProfessor);

        return "redirect:/showProfessorList";
    }
    @GetMapping("/showFormForUpdateprofessor/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) throws RecordNotFoundException {
        Professor professordb = service.findProfessorById(id);
        model.addAttribute("professor", professordb);
        return "updateProfessor";
    }
    @GetMapping("/showFormForUpdateprofessorFromProfessor/{id}")
    public String updatePofessor(@PathVariable(value = "id") long id, Model model) throws RecordNotFoundException {
        Professor professordb = service.findProfessorById(id);
        model.addAttribute("professor", professordb);
        return "updateProfessorForP";

    }
    @PostMapping("/saveProfessorForP")
    public String saveProfessor(@Valid @ModelAttribute("professor") Professor saveProfessor, BindingResult result,Model model){

        if (result.hasErrors()){
            return "newProfessor";
        }
        service.saveProfessor(saveProfessor);

        model.addAttribute("professor",saveProfessor);
        return "redirect:/professorDashboard";
    }

    @GetMapping("/addCourseToProfessor/{id}")
    public String addCourseToProfessor(@PathVariable Long id ,Model model) throws RecordNotFoundException {
        Professor professor=service.findProfessorById(id);
        List<Course> courses=courseService.getAllCourses();
        Professor newProfessor=new Professor();
        model.addAttribute("courses",courses);
        model.addAttribute("professor",professor);
        model.addAttribute("newProfessor",newProfessor);
        return "addCourseToProfessor";

    }


    @PostMapping("/saveCourseToProfessor/{id}")
    public String saveCourseToProfessor(@Valid @ModelAttribute("newProfessor") Professor newProfessor,Long id, BindingResult result) throws RecordNotFoundException {
        if (result.hasErrors()){
            return "newProfessor";
        }
        Professor dbProfessor=service.findProfessorById(id);
                //studentService.findStudentById(id);
        Course course=newProfessor.getCourses().stream().findFirst().get();
        dbProfessor.getCourses().add(course);
        course.getProfessors().add(dbProfessor);
       service.saveProfessor(dbProfessor);

        //Set<Course> courses= student.getCourses();
        return "redirect:/showProfessorList";
    }


    @GetMapping("/deleteProfessor/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id){

        service.deleteProfessor(id);
        return "redirect:/showProfessorList";
    }

    @GetMapping("/professorDashboard/{id}")
    public String professorDashboard(@PathVariable long id,Model model) throws RecordNotFoundException {
       Professor professor= service.findProfessorById(id);
       model.addAttribute("professor",professor);
        return "redirect:/professorDashboard";
    }

}
