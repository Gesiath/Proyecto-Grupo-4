package com.WorkMerge.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.WorkMerge.entities.Photo;
import com.WorkMerge.repositories.PhotoRepository;
import com.WorkMerge.services.PhotoService;

@Controller
	@RequestMapping("/photo")
	public class PhotoController {

		@Autowired
		private PhotoService photoService;
		@Autowired
		private PhotoRepository photoRepository;
		@GetMapping("/load/{id}")
		public ResponseEntity<byte[]> photo(@PathVariable String id) {
			Photo photo = photoRepository.getOne(id);
			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_PNG);
			return new ResponseEntity<>(photo.getContent(),headers, HttpStatus.OK);
		}
		
	}	


