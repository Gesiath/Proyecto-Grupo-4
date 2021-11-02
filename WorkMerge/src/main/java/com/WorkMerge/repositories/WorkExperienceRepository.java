package com.WorkMerge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.WorkMerge.entities.WorkExperience;

@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperience, String>{

}
