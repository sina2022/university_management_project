package com.jac.assignment2.controller;


import com.fasterxml.jackson.databind.*;
import com.jac.assignment2.exception.RecordNotFoundException;
import com.jac.assignment2.model.*;
import com.jac.assignment2.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
public class StudentController {
    @Autowired
    private  StudentService service;
    @Autowired
    private  AddressService addressService;
    @Autowired
    private  ProgramService programService;
    @Autowired
    private CourseService courseService;

    private final ObjectMapper mapper= new ObjectMapper();



    @GetMapping("/students")
    public String getStudents(Model model){
        List<Student> students=service.getStudents();

        model.addAttribute("students",students);

        return "studentsList";
    }

    @GetMapping("/addNewStudent")
    public String addNewStudent(Model model){
        Student student=new Student();
        model.addAttribute("student", student);
        return "newStudent";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@Valid @ModelAttribute("student") Student saveStudent, BindingResult result){
        if (result.hasErrors()){
            return "newStudent";
        }
        service.saveStudent(saveStudent);

        return "redirect:/students";
    }


    @GetMapping("/programs")
    public String getPrograms(Model model){
        List<Program> programs=programService.getPrograms();
        model.addAttribute("programs",programs);
        return "allPrograms";
    }

    @GetMapping("/addProgram")
    public String addProgram(Model model){
        Program program =new Program();
        model.addAttribute("program", program);
        return "addNewProgram";
    }

    @PostMapping("/saveProgram")
    public String saveProgram(@Valid @ModelAttribute("program") Program program, BindingResult result){
        if (result.hasErrors()){
            return "addNewProgram";
        }
      programService.saveProgram(program);
      return "redirect:/programs";
    }

    @GetMapping("/programStudents/{id}")
    public String programStudents(Model model, @PathVariable Long id) throws RecordNotFoundException {
        Program program= programService.findProgramById(id);
        List<Student> students =program.getStudents();

        model.addAttribute("students",students);
        model.addAttribute("program",program);
        return "showProgram";
    }

    @GetMapping("/addProgram/{id}")
    public String addProgramToSt(Model model, @PathVariable Long id) throws RecordNotFoundException {
        Student student=service.findStudentById(id);
        List<Program> programList = programService.getPrograms();
        Program program=new Program();
        model.addAttribute("programs", programList);
        model.addAttribute("student",student);
        model.addAttribute("program",program);
        return "addStudentToProgram";
    }

    @PostMapping("/addStudentToClass")
    public String addStudentToClass(@ModelAttribute("student") Student student){
        Program program=student.getProgram();
        program.addStudent(student);
        service.saveStudent(student);
        programService.saveProgram(program);
        return "redirect:/students";
    }

    @GetMapping("/show/{id}")
    public  String showStudent(Model model,@PathVariable Long id) throws RecordNotFoundException {
        Student student=service.findStudentById(id);
        model.addAttribute("student",student);
        return "showStudent";
    }

    @GetMapping("/showprogramStudents/{id}")
    public String showProgramStudents(Model model,@PathVariable Long id) throws RecordNotFoundException {
        Program program=programService.findProgramById(id);
        List<Student> students=program.getStudents();
        model.addAttribute("program",program);
        model.addAttribute("students",students);
        return "showProgram";
    }

    @GetMapping("/courseStudents/{id}")
    public String showcourseStudents(Model model,@PathVariable Long id) throws RecordNotFoundException {
       Course course=courseService.findCourseById(id);
        Set<Student> students=course.getStudents();
        model.addAttribute("course",course);
        model.addAttribute("students",students);
        return "showcourse";
    }
    @GetMapping("/deleteStudent/{id}")
    public String deleteThroughId(@PathVariable(value = "id") long id){

        service.deleteStudent(id);
        return "redirect:/students";
    }
    @GetMapping("/showFormForUpdate/{id}")
    public String updateForm(@PathVariable(value = "id") long id, Model model) throws RecordNotFoundException {
        Student studentdb = service.findStudentById(id);
        model.addAttribute("student", studentdb);
        return "studentUpdate";
    }

    @GetMapping("/showFormForUpdateStudent/{id}")
    public String updateFormStudent(@PathVariable(value = "id") long id, Model model) throws RecordNotFoundException {
        Student studentdb = service.findStudentById(id);
        model.addAttribute("student", studentdb);
        return "studentUpdateFromStudent";
    }
    @PostMapping("/saveStudentFromStudentDashboard")
    public String saveStudentFromStudentDashboard(@Valid @ModelAttribute("student") Student saveStudent, BindingResult result,Model model){
        if (result.hasErrors()){
            return "newStudent";
        }
        service.saveStudent(saveStudent);
        model.addAttribute("student",saveStudent);

        return "studentDashboard";
    }

}
