package logos.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import logos.dao.UserRepository;
//import logos.dao.FacultyRepository;
import logos.domain.Faculty;
import logos.domain.Profession;
import logos.domain.User;
import logos.service.FacultyService;
import logos.service.ProfessionService;
import logos.service.UserService;

@Controller
public class FacultyAndSpecialization {

	@Autowired
	private FacultyService facultyService;
	@Autowired
	private ProfessionService professionService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/faculty", method = RequestMethod.GET)
	public String faculty(Model model) {
		model.addAttribute("faculty", new Faculty());

		return "faculty";
	}

	@RequestMapping(value = "/addFaculty", method = RequestMethod.POST)
	public String createFaculty(Model model, @Valid @ModelAttribute("faculty") Faculty faculty,
			BindingResult bindingResult) {

		facultyService.save(faculty);
		model.addAttribute("message", "Success!");

		return "redirect:/home";
	}

	@RequestMapping(value = "/profession", method = RequestMethod.GET)
	public ModelAndView profession() {

		ModelAndView model = new ModelAndView();
		List<Faculty> list = facultyService.getAllFaculty();

		model.addObject("faculties", list);
		model.addObject("profession", new Profession());

		return model;
	}

	@RequestMapping(value = "/addProf", method = RequestMethod.POST)
	public String createProfession(Model model, @Valid @ModelAttribute("profession") Profession profession,
			BindingResult bindingResult) {

		professionService.save(profession);
		return "redirect:/home";
	}

//	@RequestMapping(value = "/apply", method = RequestMethod.GET)
//	public ModelAndView apply() {
//
//		ModelAndView model = new ModelAndView();
//		List<Profession> listProfession = professionService.getAllProfession();
//
//		model.addObject("prof", listProfession);
//		model.addObject("user", new User());
//
//		return model;
//	}
//
//	@RequestMapping(value = "/addBid", method = RequestMethod.POST)
//	public String createBid(Model model, @Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
//
//		return "redirect:/home";
//	}

	
	@RequestMapping(value = "/information", method = RequestMethod.GET)
	public String information(Model model) {
		
		model.addAttribute("user", new User());
		
		return "information";
	}

	@RequestMapping(value = "/addInformationCertifiacate", method = RequestMethod.POST)
	public String addInformationCertificate( @ModelAttribute("user") User user, BindingResult bindingResult , Authentication aut) {

		User userUp = userRepository.findByEmail(user.getEmail()).get();
		
		userUp.setEvaluationOfCertificate(user.getEvaluationOfCertificate());
		userService.update(userUp);

		return "redirect:/home";
	}

}
