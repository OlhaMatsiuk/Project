package logos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import logos.domain.Profession;

public interface ProfessionRepository extends JpaRepository<Profession, Integer>{
	List<Profession> findById(int id);
	List<Profession> findByName(String email);
}
