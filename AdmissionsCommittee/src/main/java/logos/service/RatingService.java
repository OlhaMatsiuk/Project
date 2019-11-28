package logos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import logos.dao.RatingRepository;
import logos.domain.Profession;
import logos.domain.Rating;
import logos.domain.User;
import logos.domain.UserStatus;

@Service
public class RatingService {

	@Autowired
	private RatingRepository ratingRepository;

	public List<Rating> getAll() {
		return ratingRepository.findAll();
	}

	public void delete(Rating rating) {
		ratingRepository.delete(rating);
	}

	public Rating add(Rating rating) {
		return ratingRepository.save(rating);
	}
	
	public void update(Rating rating) {
		ratingRepository.save(rating);
	}

	public void apply(User user, Profession prof) {

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
		
		List<Rating> list = getAll();
		List<Rating> listNew = new ArrayList<Rating>();
		
		for (Rating rating : list) {
			if(rating.getUser().getId() == id)
				listNew.add(rating);
		}
		
		return listNew;
	}
	
public List<Rating> getByProfId(int id) {
		
		List<Rating> list = getAll();
		List<Rating> listNew = new ArrayList<Rating>();
		
		for (Rating rating : list) {
			if(rating.getProfession().getId() == id)
				listNew.add(rating);
		}
		
		return listNew;
	}

}
