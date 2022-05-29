package com.jac.assignment2.service;

import com.fasterxml.jackson.databind.*;
import com.jac.assignment2.exception.RecordNotFoundException;
import com.jac.assignment2.model.*;
import com.jac.assignment2.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class StudentService {
    @Autowired
    private  StudentRepository repository;
    @Autowired
    private  AddressRepository addressRepository;
    @Autowired
    private  ProgramRepository programRepository;
    private ObjectMapper mapper;



    public List<Student> getStudents(){
        List<Student> students= (List<Student>) repository.findAll();
        return students;
    }

    public Student saveStudent(Student student){
        repository.save(student);
        return student;
    }
    public Student findStudentById(Long id) throws RecordNotFoundException {
        Optional<Student> student= repository.findById(id);
        if (student.isPresent()){
            return student.get();
        } else {
         throw new RecordNotFoundException("There is no student related to this id.");
        }
    }


    public Student UpdateStudent(Student student,Long id){
        Student fetched=repository.findById(id).get();
        fetched.setFName(student.getFName());
        fetched.setLName(student.getLName());
        fetched.setPhone(student.getPhone());
        fetched.setGender(student.getGender());
       fetched.setAddress(student.getAddress());
       fetched.setProgram(student.getProgram());
       fetched=repository.save(fetched);
       return fetched;

    }

    public void deleteStudent(Long id){
        repository.deleteById(id);
    }
}
