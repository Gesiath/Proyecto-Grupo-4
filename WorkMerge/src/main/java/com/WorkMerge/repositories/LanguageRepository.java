package com.WorkMerge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.WorkMerge.entities.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, String>{

}
