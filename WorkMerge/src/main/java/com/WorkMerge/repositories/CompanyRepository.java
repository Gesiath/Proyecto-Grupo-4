package com.WorkMerge.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.WorkMerge.entities.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String>{
	@Query("SELECT c FROM Company c WHERE c.active = true")
	public List<Company> findActive();

	@Query("SELECT c FROM Company c WHERE c.active = false")
	public List<Company> findNonActive();
	
	@Query("SELECT COUNT(c) > 0 FROM Company c WHERE c.email = :email")
	public boolean existByEmail(@Param("email") String email);
	
	@Query("SELECT c FROM Company c WHERE c.email = :email")
	public Company findByEmail(@Param("email") String email);
	
	@Query("SELECT c FROM Company c WHERE c.name LIKE :name GROUP BY c.name")
	public List<Company> findByName(@Param("name") String name);
	
	
}
