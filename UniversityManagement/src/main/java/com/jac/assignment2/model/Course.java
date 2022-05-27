package com.jac.assignment2.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String courseName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
        CascadeType.PERSIST, CascadeType.MERGE
    })
    @JoinTable(name="student_course",
            joinColumns = {@JoinColumn(name="course_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")}
    )
    private Set<Student> students =new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE
    })
    @JoinTable(name="professor_course",
            joinColumns = {@JoinColumn(name="course_id")},
            inverseJoinColumns = {@JoinColumn(name = "professor_id")}
    )
    private Set<Professor> professors =new HashSet<>();
    @OneToMany( cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH
    },mappedBy = "course")
    private List<Grade> grades=new ArrayList<>();

    public void addGrade(Grade temp){
        if (grades==null){
            grades=new ArrayList<>();
        }
        grades.add(temp);
        temp.setCourse(this);
    }

    public void addStudent(Student temp){
        if (students==null){
            students=new HashSet<>();
        }
        students.add(temp);
        temp.getCourses().add(this);
    }

    public void addProfessor(Professor temp){
        if (professors==null){
            professors=new HashSet<>();
        }
        professors.add(temp);
        temp.getCourses().add(this);
    }

    @Override
    public String toString() {
        return courseName ;
    }
}
