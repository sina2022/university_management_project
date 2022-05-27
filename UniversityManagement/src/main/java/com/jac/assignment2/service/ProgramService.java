package com.jac.assignment2.service;

import com.fasterxml.jackson.databind.*;
import com.jac.assignment2.model.*;
import com.jac.assignment2.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class ProgramService {
    @Autowired
    private  ProgramRepository programRepository;
    @Autowired
    private  StudentRepository repository;

    private ObjectMapper mapper;



    public List<Program> getClasses(){
        List<Program> programList =(List<Program>) programRepository.findAll();
        return programList;
    }

    public void saveClass(Program program){
        programRepository.save(program);
    }

    public Program findProgramById(Long id){
       return programRepository.findById(id).get();
    }
}
