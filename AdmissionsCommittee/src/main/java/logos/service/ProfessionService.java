package logos.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import logos.dao.ProfessionRepository;
import logos.domain.Profession;

@Service
public class ProfessionService {

	private Logger logger = LoggerFactory.getLogger(ProfessionService.class);
	
	@Autowired
	private ProfessionRepository professionRepository;
	
	public Profession save(Profession profession) {
		
		logger.info("Create profession item: ");
		return professionRepository.save(profession);
	}
	
	public List<Profession> getAllProfession(){
		
		logger.info("Get all professions items");
		return professionRepository.findAll();
	}
	
	public Optional<Profession> findById(int id){
		
		logger.info("Get all evaluations items where id = ");
		return professionRepository.findById(id);
	}
}
