package logos.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import logos.domain.Rating;
import logos.domain.UserStatus;
import logos.service.RatingService;

@Controller
public class NewApp {
	
	
	@Autowired
	private RatingService ratingService;
	
	@RequestMapping(value = "/newApp", method = RequestMethod.GET)
	public ModelAndView newApp() {
		
		ModelAndView model = new ModelAndView();
			
		List<Rating> rating = ratingService.getAll();
		List<Rating> ratingnNew =  new ArrayList<>();
		
		
		for (Rating rating2 : rating) {
			if(rating2.getStatus() == UserStatus.Review)
				ratingnNew.add(rating2);
		}
		
	
		model.addObject("rating", ratingnNew);

		return model;
	}
	
	@RequestMapping(value = "/allowApply", method = RequestMethod.POST)
	public String allowApply(@RequestParam("userID") int id, @RequestParam("profID") int idp)  {

		List<Rating> listU = ratingService.getByUserId(id);
		
		for (Rating rating : listU) {
			if(rating.getProfession().getId() == idp) {
				rating.setStatus(UserStatus.Allowed);
				ratingService.update(rating);
			}
		}

		return "redirect:/newApp";
	}
	
	@RequestMapping(value = "/notAllowApply", method = RequestMethod.POST)
	public String notAllowApply(@RequestParam("userID") int id, @RequestParam("profID") int idp)  {

		List<Rating> listU = ratingService.getByUserId(id);
		
		for (Rating rating : listU) {
			if(rating.getProfession().getId() == idp) {
				rating.setStatus(UserStatus.NotAllowed);
				ratingService.update(rating);
			}
		}

		return "redirect:/newApp";
	}
    

}
