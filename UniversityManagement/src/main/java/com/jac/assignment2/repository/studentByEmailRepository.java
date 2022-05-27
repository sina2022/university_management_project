package com.jac.assignment2.repository;

import com.jac.assignment2.model.*;
import org.springframework.data.jpa.repository.*;

public interface studentByEmailRepository extends JpaRepository<Student, Long> {
	@Query("SELECT s FROM Student s WHERE s.email = ?1")
	public Student findByEmail(String email);
	
}
