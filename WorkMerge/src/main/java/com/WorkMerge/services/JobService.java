package com.WorkMerge.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.WorkMerge.entities.Job;
import com.WorkMerge.repositories.JobRepository;

@Service
public class JobService {
	
	@Autowired
	private JobRepository jobRepository;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Job newJob(String title, Date datepost, String availability, String category, String description,
			Integer salary, String experienceRequired) {
		
		Job newJob = new Job();
		newJob.setTitle(title);
		newJob.setDatepost(datepost);
		newJob.setAvailability(availability);
		newJob.setCategory(category);
		newJob.setDescription(description);
		newJob.setSalary(salary);
		newJob.setExperienceRequired(experienceRequired);
		return jobRepository.save(newJob);
	}
}
