package logos.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import logos.dao.UserRepository;
import logos.domain.Evaluation;
import logos.domain.Faculty;
import logos.domain.Profession;
import logos.domain.User;
import logos.service.FacultyService;
import logos.service.ProfessionService;
import logos.service.RatingService;
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
	@Autowired
	private RatingService ratingService;

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

		return "redirect:/faculty";
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
		return "redirect:/profession";
	}
	
	
	
	@RequestMapping(value = "/apply", method = RequestMethod.GET)
	public ModelAndView apply(HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		
		Principal principal = request.getUserPrincipal();
		User userUp = userRepository.findByEmail(principal.getName()).get();
		
		List<Profession> listProfession = professionService.getAllProfession();
		List<Profession> setProfession = ratingService.getAllProfByUser(userUp);
		List<Profession> listProfession2 = new ArrayList<Profession>();
		
		for (Profession profession : listProfession) {
			for (Profession profession1 : setProfession) {
				
				if(profession1.equals(profession))
					listProfession2.add(profession1);
			}
		}
		
		
		
		Set<Evaluation> setEv = userUp.getEvaluations();
		int i = 0;
		
		for (Evaluation evaluation : setEv) {
			if(evaluation.getEvaluation() > 0)
				i++;
		}
		
		if(userUp.getEvaluationOfCertificate()>0 && i==3)
			model.addObject("security", 1);
		
		
		model.addObject("prof", listProfession);
		model.addObject("profGod", listProfession2);
		model.addObject("profession", new Profession());

		return model;
	}

	@RequestMapping(value = "/addBid", method = RequestMethod.POST)
	public String createBid(@Valid @ModelAttribute("profession") Profession prof, BindingResult bindingResult, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();
		User userUp = userRepository.findByEmail(principal.getName()).get();
		
		Profession profession = professionService.findById(prof.getId()).get();
		
		
		ratingService.apply(userUp, profession);
		
		return "redirect:/apply";
	}

}
