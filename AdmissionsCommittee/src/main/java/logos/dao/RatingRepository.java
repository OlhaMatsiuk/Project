package logos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import logos.domain.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer>{

}
