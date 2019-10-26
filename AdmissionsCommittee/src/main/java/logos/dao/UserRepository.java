package logos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import logos.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findById(int id);
	User findByEmail(String email);
	List<User> findByStatus(String status);
}
