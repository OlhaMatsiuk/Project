package logos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import logos.domain.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer>{
	List<UserRole> findById(int id);
	List<UserRole> findByRole(String email);
}
