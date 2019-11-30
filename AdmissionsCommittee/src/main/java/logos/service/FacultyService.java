package logos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import logos.dao.FacultyRepository;
import logos.domain.Faculty;

@Service
public class FacultyService {

	@Autowired
	private FacultyRepository facultyRepository;
	
	public Faculty save(Faculty faculty) {
		return facultyRepository.save(faculty);
	}
	
	public List<Faculty> getAllFaculty(){
		return facultyRepository.findAll();
	}
	
}
