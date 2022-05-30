package com.jac.assignment2.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.*;

import static com.jac.assignment2.constant.ErrorMessage.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = FIRST_NAME_IS_REQUIRED_ERROR_MESSAGE)
    @Size(max = 65)
    @Column(name = "first_name")
    private String fName;

    @NotEmpty(message = LAST_NAME_IS_REQUIRED_ERROR_MESSAGE)
    @Size(max = 65)
    @Column(name = "last_name")
    private String lName;

    private String phone;
                                        
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;

    @Column(unique = true)
    @NotEmpty(message = EMAIL_IS_REQUIRED_ERROR_MESSAGE)
    @Pattern(regexp = "^[a-zA-Z\\d+_.-]+@[a-zA-Z\\d.-]+$", message = "please provide a valid email")
    private String email;


    @OneToOne(fetch = FetchType.LAZY,cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH
    })
    @JoinColumn(name="student_id")
    private Address address;

    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH
    }, fetch=FetchType.LAZY)
    @JoinColumn(name = "program_id" ,referencedColumnName = "id")
    private Program program;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH
    }, mappedBy = "students")
    private Set<Course> courses=new HashSet<>();

    @OneToMany( cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH
    },mappedBy = "student")
    private List<Grade> grades=new ArrayList<>();

    public void addCourse(Course temp){
        if (courses==null){
            courses=new HashSet<>();
        }
        courses.add(temp);
        temp.addStudent(this);
    }
    public void addGrade(Grade temp){
        if (grades==null){
            grades=new ArrayList<>();
        }
        grades.add(temp);
        temp.setStudent(this);
    }
}
