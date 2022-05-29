package com.jac.assignment2.service;


import com.jac.assignment2.exception.RecordNotFoundException;
import com.jac.assignment2.model.*;
import com.jac.assignment2.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class GradeService {
    @Autowired
    GradeRepository repository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    StudentRepository studentRepository;


    public List<Grade> getAllGrades(){
        List<Grade> grades=( List<Grade>)repository.findAll();
        return grades;
    }

    public Grade setGrade(Student st,Course cr,Grade grade){
        Grade temp=new Grade();
        temp.setGrade(grade.getGrade());
        temp.setCourse(cr);
        temp.setStudent(st);
        repository.save(temp);
        return temp;
    }

    public void saveGrade(Grade grade){

        repository.save(grade);
    }

    public Grade getGradeById(long id) throws RecordNotFoundException {
      Optional<Grade> grade= repository.findById(id);
      if(grade.isPresent()){
          List<Grade> grades=( List<Grade>)repository.findAll();
          return grade.get();
      } else {
          throw new RecordNotFoundException("There is no grade related to this id");
      }
    }

//    public void updateGrade(Student st,Course cr,Grade grade){
//        grade.setStudent(st);
//        grade.setCourse(cr);
//        repository.save(grade);
//    }

  public void updateGrade(Grade grade){
        repository.save(grade);
  }
}
