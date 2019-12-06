package logos.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import logos.dao.FacultyRepository;
import logos.domain.Faculty;

@Service
public class FacultyService {
	
	private Logger logger = LoggerFactory.getLogger(FacultyService.class);

	@Autowired
	private FacultyRepository facultyRepository;
	
	public Faculty save(Faculty faculty) {
		logger.info("Create faculty item: ");
		return facultyRepository.save(faculty);
	}
	
	public List<Faculty> getAllFaculty(){
		logger.info("Get all faculties items");
		return facultyRepository.findAll();
	}
	
}
