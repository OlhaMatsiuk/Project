package logos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import logos.domain.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Integer>{
	List<Faculty> findById(int id);
	List<Faculty> findByName(String email);
}
