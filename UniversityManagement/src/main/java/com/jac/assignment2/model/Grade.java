package com.jac.assignment2.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "grades")

public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Float grade;

    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name = "course_id" ,referencedColumnName = "id")
    private Course course;

    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name = "student_id" ,referencedColumnName = "id")
    private Student student;

    @Override
    public String toString() {
        return
                "grade=" +grade+
                ", course="+course.getCourseName()
                ;
    }
}
