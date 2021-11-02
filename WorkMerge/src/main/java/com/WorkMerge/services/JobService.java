package com.WorkMerge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WorkMerge.repositories.JobRepository;

@Service
public class JobService {
	
	@Autowired
	private JobRepository jobRepository;
}
