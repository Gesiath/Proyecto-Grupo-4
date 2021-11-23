package com.WorkMerge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.WorkMerge.entities.Curriculum;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, String> {

}
