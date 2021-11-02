package com.WorkMerge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.WorkMerge.entities.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String>{

}
