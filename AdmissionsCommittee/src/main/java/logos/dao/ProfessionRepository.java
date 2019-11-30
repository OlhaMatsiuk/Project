package logos.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import logos.domain.Profession;

public interface ProfessionRepository extends JpaRepository<Profession, Integer>{
	Optional<Profession> findById(int id);
	List<Profession> findByName(String name);
}
