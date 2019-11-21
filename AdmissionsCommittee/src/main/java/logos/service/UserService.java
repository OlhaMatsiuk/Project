package logos.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import logos.dao.UserRepository;
import logos.domain.Profession;
import logos.domain.User;
import logos.domain.UserRole;
import logos.domain.UserStatus;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setPassword(bCryptPasswordEncoder.encode(user.getPasswordConfirm()));
		
		List<User> list = getAllUsers();
		
		if(list.isEmpty())
			user.setRole(UserRole.ADMIN);
		
		else 
			user.setRole(UserRole.USER);
		
		
		userRepository.save(user);
	}
	public void update(User user) {
		userRepository.save(user);
		
	}
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public void apply(User user, Profession prof) {
		
		Set<Profession> set = user.getProfessions();
		set.add(prof);
		user.setStatus(UserStatus.Review);
		user.setProfessions(set);
		update(user);
		
	}


}