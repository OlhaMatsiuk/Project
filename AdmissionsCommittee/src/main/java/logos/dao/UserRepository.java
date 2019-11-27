package logos.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import logos.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findById(int id);
	Optional<User> findByEmail(String email);
	
}
