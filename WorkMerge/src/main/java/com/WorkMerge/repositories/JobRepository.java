package com.WorkMerge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.WorkMerge.entities.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, String>{

}
