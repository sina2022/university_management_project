package com.jac.assignment2.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jac.assignment2.exception.RecordNotFoundException;
import com.jac.assignment2.model.*;
import com.jac.assignment2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository repository;
    @Autowired
    private  ProgramRepository programRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;

    private ObjectMapper mapper;

    public List<Professor> getProfessors(){
        List<Professor> professors= (List<Professor>) repository.findAll();
        return professors;
    }
    public Professor saveProfessor(Professor professor){
        repository.save(professor);
        return professor;
    }
    public Professor findProfessorById(Long id) throws RecordNotFoundException {
        Optional<Professor> professor= repository.findById(id);
        if(professor.isPresent()){
            return professor.get();
        } else {
            throw new RecordNotFoundException("There is no professor related to this id.");
        }
    }

    public Professor updateProfessor(Professor professor, Long id){
        Professor fetched= repository.findById(id).get();
        fetched.setFName(professor.getFName());
        fetched.setLName(professor.getLName());
        fetched.setPhone(professor.getPhone());
        fetched.setGender(professor.getGender());
        fetched.setEmail(professor.getEmail());
        fetched.setAddress(professor.getAddress());
        return fetched;
    }

    public void deleteProfessor(Long id){

        repository.deleteById(id);
    }



}
