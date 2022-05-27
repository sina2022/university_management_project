package com.jac.assignment2.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jac.assignment2.model.Professor;
import com.jac.assignment2.model.Student;
import com.jac.assignment2.repository.AddressRepository;
import com.jac.assignment2.repository.ProfessorRepository;
import com.jac.assignment2.repository.ProgramRepository;
import com.jac.assignment2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    private ObjectMapper mapper;

    public List<Professor> getProfessors(){
        List<Professor> professors= (List<Professor>) repository.findAll();
        return professors;
    }
    public Professor saveProfessor(Professor professor){
        repository.save(professor);
        return professor;
    }
    public Professor findProfessorById(Long id){
        Professor professor= repository.findById(id).get();
        return professor;
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
