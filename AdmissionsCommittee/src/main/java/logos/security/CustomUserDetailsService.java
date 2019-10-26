package logos.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import logos.dao.UserRepository;
import logos.domain.User;

@Service("customUserDetailsService")
public class CustomUserDetailsService {

	
@Autowired
private UserRepository userRepository;

public UserDetails loadUserByUserEmail(String email) throws UsernameNotFoundException {

	User userOptional = userRepository.findByEmail(email);

	if (userOptional != null) {
		User user = userOptional;
		return new CustomUserDetails(user, Collections.singletonList(user.getRole().toString()));
	}

	throw new UsernameNotFoundException("No user present with useremail:" + email);
}

}