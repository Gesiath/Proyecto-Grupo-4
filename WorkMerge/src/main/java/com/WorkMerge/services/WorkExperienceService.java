package com.WorkMerge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WorkMerge.repositories.WorkExperienceRepository;

@Service
public class WorkExperienceService {
	
	@Autowired
	private WorkExperienceRepository workExperienceRepository;
}
