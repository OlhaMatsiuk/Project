package logos.controller;

import java.util.ArrayList;
import java.util.Collections;
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
import logos.service.ProfessionService;
import logos.service.RatingService;

@Controller
public class RatingController {

	@Autowired
	private RatingService ratingService;
	@Autowired
	private ProfessionService professionService;

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

	@RequestMapping(value = "/end", method = RequestMethod.GET)
	public String end() {

		List<Rating> listAllRating = ratingService.getAll();
		List<Profession> listAllProfession = professionService.getAllProfession();
		List<Rating> ratingOneProf = new ArrayList<Rating>();

		for (Profession profession : listAllProfession) {
			for (Rating rating : listAllRating) {

				if (rating.getProfession().getId() == profession.getId() && rating.getStatus() != UserStatus.Review &&
																		rating.getStatus() != UserStatus.NotAllowed) {
					ratingOneProf.add(rating);
				}
			}

			Collections.sort(ratingOneProf, Collections.reverseOrder());

			if (profession.getPlanOfStudent() < ratingOneProf.size()) {

				for (int i = 0; i < profession.getPlanOfStudent(); i++) {

					if (ratingOneProf.get(i) != null)
						ratingOneProf.get(i).setStatus(UserStatus.Credited);
				}

				for (int i = profession.getPlanOfStudent(); i < ratingOneProf.size(); i++) {

					if (ratingOneProf.get(i) != null) {
						ratingOneProf.get(i).setStatus(UserStatus.NotCredited);
						ratingService.update(ratingOneProf.get(i));
					}
				}

			} else {

				for (int i = 0; i < ratingOneProf.size(); i++) {

					if (ratingOneProf.get(i) != null) {
						ratingOneProf.get(i).setStatus(UserStatus.Credited);
						ratingService.update(ratingOneProf.get(i));
					}
				}
			}

			ratingOneProf.removeAll(ratingOneProf);

		}

		return "redirect:/rating";
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

			if (i == listProfession.size())
				listProfession.add(rating.getProfession());

			i = 0;
		}

		return listProfession;
	}
}
