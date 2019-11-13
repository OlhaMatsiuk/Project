package logos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import logos.domain.Faculty;
import logos.service.FacultyService;

@Controller
public class FacultyAndSpecialization {

	@Autowired
	private FacultyService facultyService;

	 @RequestMapping(value = "/faculty", method = RequestMethod.GET)
	  public String faculty(Model model) {
	        model.addAttribute("faculty", new Faculty());
	       
	        return "faculty";
	  }

	@RequestMapping(value = "/addFaculty", method = RequestMethod.POST)
	public String createFaculty(Model model, @Valid @ModelAttribute("faculty") Faculty faculty, BindingResult bindingResult) {
	
		facultyService.save(faculty);
		model.addAttribute("message", "Success!");
	
		return "redirect:/home";
	}

}
