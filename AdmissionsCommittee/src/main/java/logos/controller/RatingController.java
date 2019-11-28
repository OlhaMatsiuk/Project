package logos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import logos.domain.Profession;
import logos.domain.Rating;
import logos.domain.UserStatus;
import logos.service.RatingService;

@Controller
public class RatingController {

	@Autowired
	private RatingService ratingService;

	@RequestMapping(value = "/rating", method = RequestMethod.GET)
	public ModelAndView getAllItems() {
		ModelAndView model = new ModelAndView("rating");

		List<Rating> listAll = ratingService.getAll();
		List<Rating> listS = new ArrayList<Rating>();

		for (Rating rating : listAll) {
			if (rating.getStatus() != UserStatus.NotAllowed)
				listS.add(rating);
		}
		
		

		model.addObject("prof", getAllProfForSelect());
		model.addObject("rating", listS);
		return model;
	}

	@RequestMapping(value = "/ratingP", method = RequestMethod.GET)
	public ModelAndView sort(@RequestParam int id) {

		ModelAndView model = new ModelAndView("rating");

		List<Rating> listP = ratingService.getByProfId(id);
		List<Rating> listR = new ArrayList<Rating>();

		for (Rating rating : listP) {
			if (rating.getStatus() != UserStatus.NotAllowed)
				listR.add(rating);
		}

		model.addObject("prof", getAllProfForSelect());
		model.addObject("rating", listR);

		return model;
	}
	
	
	public List<Profession> getAllProfForSelect() {
		
		int i = 0;
		List<Rating> listAll = ratingService.getAll();

		List<Profession> listProfession = new ArrayList<Profession>();
		
		for (Rating rating : listAll) {
			if (listProfession.isEmpty())
				listProfession.add(rating.getProfession());
			else {
				for (Profession profession : listProfession) {
					if (rating.getProfession().getId() != profession.getId())
						i++;
				}
			}
			
			if(i == listProfession.size())
				listProfession.add(rating.getProfession());
			
			i = 0;
		}
		
		return listProfession;
	}
}
