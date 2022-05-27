package com.jac.assignment2.repository;

import com.jac.assignment2.model.*;
import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends CrudRepository<Grade,Long> {
}
