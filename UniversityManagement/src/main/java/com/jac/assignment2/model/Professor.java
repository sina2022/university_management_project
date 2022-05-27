package com.jac.assignment2.model;

import lombok.*;
import org.hibernate.validator.constraints.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

import static com.jac.assignment2.constant.ErrorMessage.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "professors")
public class Professor {

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

    @NotEmpty(message = EMAIL_IS_REQUIRED_ERROR_MESSAGE)
    @Column(unique = true)
    @Pattern(regexp = "^[a-zA-Z\\d+_.-]+@[a-zA-Z\\d.-]+$", message = "please provide a valid email")
    private String email;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="professor_id")
    private Address address;


    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH
    }, mappedBy = "professors")
    private Set<Course> courses=new HashSet<>();

    //@ManyToMany with Course

    public void addCourse(Course temp){
        if (courses==null){
            courses=new HashSet<>();
        }
        courses.add(temp);
        temp.addProfessor(this);
    }
    public void addAddress(Address temp){
        if (address==null){
            address=temp;
        }
      //  courses.add(temp);
        temp.addProfessor(this);
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", phone='" + phone + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", address=" + address +
                '}';
    }
}
