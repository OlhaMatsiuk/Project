package logos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import logos.dao.EvaluationRepository;
import logos.domain.Evaluation;

@Service
public class EvaluationService {

	@Autowired
	private EvaluationRepository evaluationRepository;
	
	public Evaluation save(Evaluation eval) {
		return evaluationRepository.save(eval);
	}
	
	public List<Evaluation> getAll(){
		return evaluationRepository.findAll();
	}
	
}
