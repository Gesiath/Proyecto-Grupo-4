package com.WorkMerge.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.WorkMerge.entities.Admin;
import com.WorkMerge.entities.Client;
import com.WorkMerge.entities.Company;
import com.WorkMerge.entities.Job;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String>{
	@Query("SELECT COUNT(a) > 0 FROM Admin a WHERE a.email = :email")
	public boolean existByEmail(@Param("email") String email);
	
	@Query("SELECT a FROM Admin a WHERE a.email = :email")
	public Admin findByEmail(@Param("email") String email);
	
	@Query("SELECT a FROM Admin a WHERE a.nickname LIKE :q or a.email LIKE :q")
	List<Admin> findActiveByQ(@Param("q") String q);
	
	@Query("SELECT c FROM Company c WHERE c.name LIKE :q or c.email LIKE :q")
	List<Company> findCompanyByQ(@Param("q") String q);
	
	@Query("SELECT cl FROM Client cl JOIN Curriculum cu ON cl.curriculum.id = cu.id WHERE cu.name LIKE :q or cl.email LIKE :q")
	List<Client> findClientByQ(@Param("q") String q);
	
	@Query("SELECT j FROM Job j WHERE j.title LIKE :q or j.salary LIKE :q or j.description LIKE :q or j.category LIKE :q or j.availability LIKE :q")
	List<Job> findJobByQ(@Param("q") String q);

}
