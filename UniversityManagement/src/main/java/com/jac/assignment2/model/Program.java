package com.jac.assignment2.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "programs")
public class Program {
//    public Program(String programName, List<Student> students, Set<Professor> professors) {
//        this.programName = programName;
//        this.students = students;
//        this.professors = professors;
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 65)
    @Column(name = "program_name")
    private String programName;

    @OneToMany( cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH
    },mappedBy = "program")
    private List<Student> students=new ArrayList<>();

//    @ManyToMany(fetch = FetchType.LAZY, cascade = {
//            CascadeType.PERSIST, CascadeType.MERGE
//    }, mappedBy = "programs")
//    private Set<Professor> professors=new HashSet<>();

    public void addStudent(Student temp){
        if (students==null){
            students=new ArrayList<>();
        }
        students.add(temp);
        temp.setProgram(this);
    }

    @Override
    public String toString() {
        return programName;
    }
}
