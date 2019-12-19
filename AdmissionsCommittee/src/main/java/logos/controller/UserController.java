package logos.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import logos.dao.UserRepository;
import logos.domain.Evaluation;
import logos.domain.Rating;
import logos.domain.User;
import logos.service.EvaluationService;
import logos.service.RatingService;
import logos.service.UserDTOHelper;
import logos.service.UserService;

@Controller
public class UserController {

	@Autowired
    private UserService userService;
	@Autowired
    private UserRepository userRepository;
	@Autowired
	private EvaluationService evaluationService;
	@Autowired
	private RatingService ratingService;



    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {

    	
    	List<User> allUser = userService.getAllUsers();
    	
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        
        for (User user : allUser) {
			if(userForm.getEmail().equals(user.getEmail()) || userForm.getFirstName() == null || userForm.getLastName() == null )
				return "registration";	
		}
        
        userService.save(userForm);


        return "redirect:/home";
    }

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value ="/home", method = RequestMethod.GET)
   	public ModelAndView welcome() {
   		ModelAndView map = new ModelAndView("home");
   		//map.addObject("periodicals", .periodicalsServicegetAllPeriodicals());
   		
   		

   		return map;
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
		
		
		if(userUp.getEncodedImage() != null)
			modelAndView.addObject("image", "Image added!");
			
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
    
	@RequestMapping(value = "/addImage", method = RequestMethod.POST)
	public String addImage(@RequestParam MultipartFile image,  HttpServletRequest request) throws IOException {

		Principal principal = request.getUserPrincipal();
		User userUp = userRepository.findByEmail(principal.getName()).get();
			
		userService.update(UserDTOHelper.setImage(image, userUp));

		return "redirect:/information";
	}
    
	  @RequestMapping(value = "/status", method = RequestMethod.GET)
		public ModelAndView status( HttpServletRequest request) {
		  
		    Principal principal = request.getUserPrincipal();
			User userUp = userRepository.findByEmail(principal.getName()).get();
			
			ModelAndView model = new ModelAndView();
			
			List<Rating> listProfession = ratingService.getByUserId(userUp.getId());
			
			
			model.addObject("rating", listProfession);
			
			return model;
	  	}
}
