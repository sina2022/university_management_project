package com.jac.assignment2.model;

import lombok.*;

import javax.persistence.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int buildingNumber;
    private String street;
    private String city;
    private String zipCode;

    @OneToOne(mappedBy = "address")
    private Student student;

    @OneToOne(mappedBy = "address")
    private Professor professor;

    public void addProfessor(Professor prof){
        this.professor=prof;
    }

    public void addStudent(Student stud){
        this.student=stud;
    }


    @Override
    public String toString() {
        return "number "+buildingNumber+","+street+","+city+","+"zip code:"+zipCode;
    }
}
