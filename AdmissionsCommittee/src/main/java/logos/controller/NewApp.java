package logos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import logos.domain.User;
import logos.domain.UserStatus;
import logos.service.UserService;

@Controller
public class NewApp {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/newApp", method = RequestMethod.GET)
	public ModelAndView newApp() {
		
		ModelAndView model = new ModelAndView();
		List<User> list = userService.getAllUsers();
		List<User> listNew = new ArrayList<User>();
		
		for (User user : list) {
			if(user.getStatus() == UserStatus.Review)
				listNew.add(user);
		}
		
		model.addObject("list", list);

		return model;
	}

}
