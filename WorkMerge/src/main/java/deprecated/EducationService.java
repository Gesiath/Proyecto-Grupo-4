package deprecated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationService {
	
	@Autowired
	private EducationRepository educationRepository;
}
