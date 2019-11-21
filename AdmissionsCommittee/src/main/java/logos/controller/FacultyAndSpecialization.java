package logos.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
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
import logos.service.EvaluationService;
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
	@Autowired
	private EvaluationService evaluationService;

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
	
	@RequestMapping(value = "/information", method = RequestMethod.GET)
	public ModelAndView information( HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView();
		Principal principal = request.getUserPrincipal();
		User userUp = userRepository.findByEmail(principal.getName()).get();	
		
		if(userUp.getEvaluationOfCertificate() > 0)
			modelAndView.addObject("certifEr", userUp.getEvaluationOfCertificate());
		
		List<Evaluation> listEval = evaluationService.getAll();
		List<Evaluation> listEval1 = new ArrayList<>();
		
		for (Evaluation evaluation : listEval) {
			if(evaluation.getUser().getId() == userUp.getId()) 
				listEval1.add(evaluation);
		}
		
		for (Evaluation evaluation : listEval1) {
			
			if(evaluation.getNameSubject().equals("Math"))
				modelAndView.addObject("mathEr", evaluation.getEvaluation());
			
			if(evaluation.getNameSubject().equals("Language"))
				modelAndView.addObject("lanEr", evaluation.getEvaluation());
			
			if(evaluation.getNameSubject().equals("History"))
				modelAndView.addObject("hisEr", evaluation.getEvaluation());
		}
			
		modelAndView.addObject("user", new User());
		modelAndView.addObject("evaluation", new Evaluation());
		
		return modelAndView;
	}

	@RequestMapping(value = "/addInformationCertifiacate", method = RequestMethod.POST)
	public String addInformationCertificate( @ModelAttribute("user") User user, BindingResult bindingResult , Authentication aut) {

		User userUp = userRepository.findByEmail(user.getEmail()).get();
		
		userUp.setEvaluationOfCertificate(user.getEvaluationOfCertificate());
		userService.update(userUp);

		return "redirect:/information";
	}
	
	@RequestMapping(value = "/addEvaluations", method = RequestMethod.POST)
	public String addEvaluations( @ModelAttribute("evaluation") Evaluation evaluation, BindingResult bindingResult, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();
		User userUp = userRepository.findByEmail(principal.getName()).get();
		evaluation.setUser(userUp);
		evaluationService.save(evaluation);
		
		return "redirect:/information";
	}
	
	@RequestMapping(value = "/apply", method = RequestMethod.GET)
	public ModelAndView apply(HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		
		Principal principal = request.getUserPrincipal();
		User userUp = userRepository.findByEmail(principal.getName()).get();
		
		List<Profession> listProfession = professionService.getAllProfession();
		Set<Profession> setProfession = userUp.getProfessions();
		List<Profession> listProfession2 = new ArrayList<Profession>();
		
		for (Profession profession : listProfession) {
			for (Profession profession1 : setProfession) {
				
				if(profession1.equals(profession))
					listProfession2.add(profession1);
			}
		}
		
		
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
		
		
		userService.apply(userUp, profession);
		
		return "redirect:/apply";
	}

}
