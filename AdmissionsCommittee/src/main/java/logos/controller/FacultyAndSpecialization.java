package logos.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import logos.dao.FacultyRepository;
import logos.domain.Faculty;
import logos.domain.Profession;
import logos.service.FacultyService;
import logos.service.ProfessionService;

@Controller
public class FacultyAndSpecialization {

	@Autowired
	private FacultyService facultyService;
	@Autowired
	private FacultyRepository facultyRepository;
	@Autowired
	private ProfessionService professionService;

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

}
