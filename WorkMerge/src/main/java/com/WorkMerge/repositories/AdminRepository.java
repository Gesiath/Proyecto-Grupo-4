package com.WorkMerge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.WorkMerge.entities.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String>{
	@Query("SELECT COUNT(a) > 0 FROM Admin a WHERE a.email = :email")
	public boolean existByEmail(@Param("email") String email);
	
	@Query("SELECT a FROM Admin a WHERE a.email = :email")
	public Admin findByEmail(@Param("email") String email);

}
