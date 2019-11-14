package logos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import logos.dao.ProfessionRepository;
import logos.domain.Profession;

@Service
public class ProfessionService {

	@Autowired
	private ProfessionRepository professionRepository;
	
	public Profession save(Profession profession) {
		return professionRepository.save(profession);
	}
	
	public List<Profession> getAllProfession(){
		return professionRepository.findAll();
	}
}
