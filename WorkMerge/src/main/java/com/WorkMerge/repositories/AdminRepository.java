package com.WorkMerge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.WorkMerge.entities.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String>{
	
}
