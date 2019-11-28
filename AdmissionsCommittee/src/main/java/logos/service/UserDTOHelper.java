package logos.service;

import java.io.IOException;
import java.util.Base64;

import org.springframework.web.multipart.MultipartFile;

import logos.domain.User;

public class UserDTOHelper {
	
	public static User setImage(MultipartFile file, User user) throws IOException {
		
		user.setEncodedImage(Base64.getEncoder().encodeToString(file.getBytes()));
		
		return user;
	}

}
