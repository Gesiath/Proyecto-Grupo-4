package com.WorkMerge.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.WorkMerge.entities.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, String>{
	// @Query("Select j from Job j where j.title LIKE :q or j.description LIKE :q") //LIKE PERMITE BUSCAR UN TEXTO LETRA POR LETRA y tambien por nombre de ciudad
	// List<Job> findJobByTitle(@Param("q") String q);
	 
	@Query("SELECT j FROM Job j WHERE j.active = true")
	public List<Job> findActive();

	@Query("SELECT j FROM Job j WHERE j.active = false")
	public List<Job> findNonActive();
	
	@Query("SELECT j FROM Job j WHERE j.title LIKE :q or j.description LIKE :q or j.category LIKE :q")
	List<Job> findActiveByQ(@Param("q") String q);
	
}
