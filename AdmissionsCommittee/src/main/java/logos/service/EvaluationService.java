package logos.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import logos.dao.EvaluationRepository;
import logos.domain.Evaluation;

@Service
public class EvaluationService {

	private Logger logger = LoggerFactory.getLogger(EvaluationService.class);
	
	@Autowired
	private EvaluationRepository evaluationRepository;
	
	public Evaluation save(Evaluation eval) {
		logger.info("Create eval item: ");
		return evaluationRepository.save(eval);
	}
	
	public List<Evaluation> getAll(){
		logger.info("Get all evaluations items");
		return evaluationRepository.findAll();
	}
	
}
