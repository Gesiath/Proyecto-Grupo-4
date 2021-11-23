package com.WorkMerge.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.WorkMerge.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
	@Query("SELECT c FROM Client c WHERE c.active = true")
	public List<Client> findActive();

	@Query("SELECT c FROM Client c WHERE c.active = false")
	public List<Client> findNonActive();
	
	@Query("SELECT COUNT(c) > 0 FROM Client c WHERE c.email = :email")
	public boolean existByEmail(@Param("email") String email);
	
	@Query("SELECT c FROM Client c WHERE c.email = :email")
	public Client findByEmail(@Param("email") String email);
	
}
