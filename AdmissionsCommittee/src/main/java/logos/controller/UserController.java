package logos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import logos.dao.UserRepository;
import logos.domain.User;
import logos.domain.UserRole;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("userForm", new User());

		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model,
			String email, String firstName) {

		if (bindingResult.hasErrors()) {
			return "registration";
		}

		userForm.setEmail(email);
		userForm.setFirstName(firstName);
		userForm.setRole(UserRole.USER);
		userRepository.save(userForm);
		
		model.addAttribute("pass", "You id = " + userForm.getId() + ", this is your password!");

		return  "redirect:/login";
	}

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String loginPage(Model model) {
		return "login";
	}

	@PostMapping(value = "/home")
	public String login(Model model, @RequestParam String email, @RequestParam String password) {
	
		User user = userRepository.findByEmail(email);
		
		int id = Integer.parseInt(password);
		
		if(user!= null && user.getId() == id) {
			model.addAttribute("name", user.getFirstName());
			return "home";
		}
		return "redirect:/login";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String welcome(@ModelAttribute("name") String name, Model model) {

		model.addAttribute("name", name);
		return "home";
	}

}
