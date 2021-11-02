package com.WorkMerge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WorkMerge.repositories.EducationRepository;

@Service
public class EducationService {
	
	@Autowired
	private EducationRepository educationRepository;
}
