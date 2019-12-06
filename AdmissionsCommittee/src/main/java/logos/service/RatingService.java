package logos.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import logos.dao.RatingRepository;
import logos.domain.Profession;
import logos.domain.Rating;
import logos.domain.User;
import logos.domain.UserStatus;

@Service
public class RatingService {
	
	private Logger logger = LoggerFactory.getLogger(RatingService.class);

	@Autowired
	private RatingRepository ratingRepository;

	public List<Rating> getAll() {
		logger.info("Get all ratings items");
		return ratingRepository.findAll();
	}

	public void delete(Rating rating) {
		logger.info("Delete rating item");
		ratingRepository.delete(rating);
	}

	public Rating add(Rating rating) {
		logger.info("Create rating item");
		return ratingRepository.save(rating);
	}
	
	public void update(Rating rating) {
		logger.info("Update rating item");
		ratingRepository.save(rating);
	}

	public void apply(User user, Profession prof) {
		
		logger.info("Create rating item");

		List<Rating> list = getAll();
		int i = 0;

		if (list.size() == 0) {
			ratingRepository.save(new Rating(user, prof, UserStatus.Review));

		} else {
			for (Rating rating : list) {
				if (rating.getUser().getId() == user.getId() && rating.getProfession().getId() == prof.getId())
					i = 0;
				else
					i = i + 1;
			}
		}

		if (i == list.size() && i != 0) {
			ratingRepository.save(new Rating(user, prof, UserStatus.Review));
		}

	}

	public List<Profession> getAllProfByUser(User user) {
		
		logger.info("Get all ratings items where user = " );

		List<Rating> list = getAll();
		List<Rating> listByUser = new ArrayList<Rating>();

		for (Rating rating : list) {
			if (rating.getUser().equals(user))
				listByUser.add(rating);
		}

		List<Profession> listProf = new ArrayList<Profession>();

		for (Rating rating : listByUser) {
			listProf.add(rating.getProfession());
		}

		return listProf;
	}
	
	public List<Rating> getByUserId(int id) {
		
		logger.info("Get all ratings items where userId = " );
		
		List<Rating> list = getAll();
		List<Rating> listNew = new ArrayList<Rating>();
		
		for (Rating rating : list) {
			if(rating.getUser().getId() == id)
				listNew.add(rating);
		}
		
		return listNew;
	}
	
public List<Rating> getByProfId(int id) {
	
	logger.info("Get all ratings items where professionId = ");
		
		List<Rating> list = getAll();
		List<Rating> listNew = new ArrayList<Rating>();
		
		for (Rating rating : list) {
			if(rating.getProfession().getId() == id)
				listNew.add(rating);
		}
		
		return listNew;
	}

}
