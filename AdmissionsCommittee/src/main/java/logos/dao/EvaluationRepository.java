package logos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import logos.domain.Evaluation;

public interface EvaluationRepository  extends JpaRepository<Evaluation, Integer>{
	List<Evaluation> findById(int id);
	List<Evaluation> findByNameSubject(String email);
}
