package com.WorkMerge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.WorkMerge.entities.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, String>{

}
