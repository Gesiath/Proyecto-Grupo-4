package com.WorkMerge.services;

import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.WorkMerge.entities.Photo;
import com.WorkMerge.repositories.PhotoRepository;

@Service
public class PhotoService {
	
	@Autowired
	private PhotoRepository photoRepository;
	
	 public Photo saved(MultipartFile archive) throws ServiceException {

	        if (archive != null) {
	            try {

	                Photo photo = new Photo();
	                photo.setMime(archive.getContentType());
	                photo.setName(null);
	                photo.setContent(archive.getBytes());

	                return photoRepository.save(photo);
	            } catch (Exception e) {
	                System.out.println(e.getMessage());
	            }
	        }
	        return null;
	    }

	    public Photo update(String idPhoto, MultipartFile archive) throws ServiceException {

	        if (archive != null) {
	            try {

	                Photo photo = new Photo();
	                if (idPhoto != null) {
	                    Optional<Photo> respuesta = photoRepository.findById(idPhoto);
	                    if (respuesta.isPresent()) {
	                        photo = respuesta.get();
	                    }

	                }
	                photo.setMime(archive.getContentType());
	                photo.setName(archive.getName());
	                photo.setContent(archive.getBytes());

	                return photoRepository.save(photo);
	            } catch (Exception e) {
	                System.out.println(e.getMessage());
	            }
	        }
	        return null;
	    }
	    
}
