package logos;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import logos.domain.Faculty;
import logos.domain.Profession;
import logos.domain.Rating;
import logos.domain.User;
import logos.domain.UserRole;
import logos.domain.UserStatus;
import logos.service.FacultyService;
import logos.service.ProfessionService;
import logos.service.RatingService;
import logos.service.UserService;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ApplicationTests {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FacultyService facultyService;
	
	@Autowired
	private ProfessionService professionService;
	
	@Autowired
	private RatingService ratingService;

	@Test
	public void testSaveUser() {
	
		List<User> users = userService.getAllUsers();
		assertThat(users, hasSize(0));

		User user = new User();
		user.setEmail("1@gmail.com");
		user.setFirstName("1");
		user.setLastName("1");
		user.setPassword("1");
		user.setPasswordConfirm("1");
		userService.save(user);
		
		user.setRole(UserRole.ADMIN);

		users = userService.getAllUsers();
		assertThat(users, hasSize(1));

		User userFromDb = users.get(0);
		assertTrue(userFromDb.getEmail().equals(user.getEmail()));
		assertTrue(userFromDb.getFirstName().equals(user.getFirstName()));
		assertTrue(userFromDb.getLastName().equals(user.getLastName()));
		assertTrue(userFromDb.getRole().equals(user.getRole()));
	}
	
	@Test
	public void testSaveFaculty() {
	
		List<Faculty> faculties = facultyService.getAllFaculty();
		assertThat(faculties, hasSize(0));

		Faculty faculty = new Faculty();
		faculty.setName("test");
		
		Profession prof = new Profession();
		prof.setName("Math");
		prof.setFaculty(faculty);
				
		facultyService.save(faculty);

		faculties = facultyService.getAllFaculty();
		assertThat(faculties, hasSize(1));

		Faculty facultyFromDb = faculties.get(0);
		assertTrue(facultyFromDb.getName().equals(faculty.getName()));
		assertTrue(facultyFromDb.getProfessions().equals(faculty.getProfessions()));
		
	}
	
	@Test
	public void testSaveProfession() {
	
		List<Profession> professions = professionService.getAllProfession();
		assertThat(professions, hasSize(0));

		Faculty faculty = new Faculty();
		faculty.setName("test");
		facultyService.save(faculty);
		
		Profession prof = new Profession();
		prof.setName("Math");
		prof.setFaculty(faculty);
		prof.setPlanOfStudent(10);	
		
		professionService.save(prof);

		professions = professionService.getAllProfession();
		assertThat(professions, hasSize(1));

		Profession professionFromDb = professions.get(0);
		assertTrue(professionFromDb.getName().equals(prof.getName()));
		assertTrue(professionFromDb.getFaculty().equals(prof.getFaculty()));
		assertTrue(professionFromDb.getPlanOfStudent() == prof.getPlanOfStudent());	
	}
	
	
	@Test
	public void testApplyRating() {
	
		List<Rating> rating = ratingService.getAll();
		assertThat(rating, hasSize(0));
		
		User user = new User();
		user.setEmail("1@gmail.com");
		user.setFirstName("1");
		user.setLastName("1");
		user.setPassword("1");
		user.setPasswordConfirm("1");
		userService.save(user);

		Faculty faculty = new Faculty();
		faculty.setName("test");
		facultyService.save(faculty);
		
		Profession prof = new Profession();
		prof.setName("Math");
		prof.setFaculty(faculty);
		prof.setPlanOfStudent(10);	
		
		professionService.save(prof);
		
		ratingService.apply(user, prof);
		
		rating = ratingService.getAll();
		assertThat(rating, hasSize(1));

		Rating ratingFromDb = rating.get(0);
		assertTrue(ratingFromDb.getUser().getId() == ratingFromDb.getUser().getId());
		assertTrue(ratingFromDb.getProfession().getName().equals(ratingFromDb.getProfession().getName()));
		assertTrue(ratingFromDb.getStatus().equals(UserStatus.Review));	
		
	}
	
	
	
	
	
}