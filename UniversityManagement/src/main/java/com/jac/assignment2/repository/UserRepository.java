package com.jac.assignment2.repository;

import com.jac.assignment2.model.*;
import org.springframework.data.jpa.repository.*;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public User findByEmail(String email);

	@Query("SELECT u FROM User u WHERE u.id = ?1")
	public Optional<User> findById(Long id);
}
