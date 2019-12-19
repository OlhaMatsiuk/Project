package logos.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import logos.dao.UserRepository;
import logos.domain.User;
import logos.domain.UserRole;

@Service
public class UserService {
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	public void save(User user) {
		
		logger.info("Create user item: " );
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
		
		logger.info("Update user item: " );
		userRepository.save(user);
		
	}
	public List<User> getAllUsers(){
		
		logger.info("Get all users items");
		return userRepository.findAll();
	}


}