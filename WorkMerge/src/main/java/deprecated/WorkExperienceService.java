package deprecated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkExperienceService {
	
	@Autowired
	private WorkExperienceRepository workExperienceRepository;
}
