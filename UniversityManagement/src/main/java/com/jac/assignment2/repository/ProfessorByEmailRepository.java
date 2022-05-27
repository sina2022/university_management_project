package com.jac.assignment2.repository;

import com.jac.assignment2.model.*;
import org.springframework.data.jpa.repository.*;

public interface ProfessorByEmailRepository extends JpaRepository<Professor, Long> {
	@Query("SELECT p FROM Professor p WHERE p.email = ?1")
	public Professor findByEmail(String email);
	
}
