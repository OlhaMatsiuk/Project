package logos.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Comparable<User>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private String email;
	
	@Column
	private String password;
	
	private String passwordConfirm;
	
	@Enumerated(EnumType.STRING)
	private UserRole role;
		
	@Column
	private int evaluationOfCertificate;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL,CascadeType.PERSIST,CascadeType.MERGE }, mappedBy = "user")
    @Column(nullable = false)
	private Set<Evaluation> evaluations = new HashSet<Evaluation>();
	
	@Lob
	private String encodedImage;
	
	
public User() {}
	
	public User(User user) {
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.evaluationOfCertificate = user.getEvaluationOfCertificate();
		this.evaluations = user.getEvaluations();
		this.role = user.getRole();
	}

	public User(String firstName, String lastName, String email, String password, UserRole role,  int evaluationOfCertificate,
			Set<Evaluation> evaluations) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.evaluationOfCertificate = evaluationOfCertificate;
		this.evaluations = evaluations;
		this.role = role;
	}
	
	public User(String firstName, String lastName, String email,String password, UserRole role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public User(int id, String firstName, String lastName, String email,String password, UserRole role, int evaluationOfCertificate,
			Set<Evaluation> evaluations) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.evaluationOfCertificate = evaluationOfCertificate;
		this.evaluations = evaluations;
		this.role = role;
	}
	
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPasswordConfirm() {
		return passwordConfirm;
	}


	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}


	public int getEvaluationOfCertificate() {
		return evaluationOfCertificate;
	}


	public void setEvaluationOfCertificate(int evaluationOfCertificate) {
		this.evaluationOfCertificate = evaluationOfCertificate;
	}


	public Set<Evaluation> getEvaluations() {
		return evaluations;
	}


	public void setEvaluations(Set<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}


	public String getEncodedImage() {
		return encodedImage;
	}


	public void setEncodedImage(String encodedImage) {
		this.encodedImage = encodedImage;
	}


	public UserRole getRole() {
		return role;
	}


	public void setRole(UserRole role) {
		this.role = role;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((encodedImage == null) ? 0 : encodedImage.hashCode());
		result = prime * result + evaluationOfCertificate;
		result = prime * result + ((evaluations == null) ? 0 : evaluations.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((passwordConfirm == null) ? 0 : passwordConfirm.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (encodedImage == null) {
			if (other.encodedImage != null)
				return false;
		} else if (!encodedImage.equals(other.encodedImage))
			return false;
		if (evaluationOfCertificate != other.evaluationOfCertificate)
			return false;
		if (evaluations == null) {
			if (other.evaluations != null)
				return false;
		} else if (!evaluations.equals(other.evaluations))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (passwordConfirm == null) {
			if (other.passwordConfirm != null)
				return false;
		} else if (!passwordConfirm.equals(other.passwordConfirm))
			return false;
		if (role != other.role)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", passwordConfirm=" + passwordConfirm + ", evaluationOfCertificate="
				+ evaluationOfCertificate + ", evaluations=" + evaluations + ", encodedImage=" + encodedImage
				+ ", role=" + role + "]";
	}

	@Override
	public int compareTo(User o) {
		if (getEvaluationOfCertificate() == 0 || o.getEvaluationOfCertificate() == 0) {
		      return 0;
		    }
		
		int bal1, bal2;
		int serBal1 = 0, serBal2 = 0;
		
		Set<Evaluation> set1 = this.getEvaluations();
		Set<Evaluation> set2 = o.getEvaluations();
		
		
		for (Evaluation evaluation : set1) {
			serBal1+= evaluation.getEvaluation();
		}
		
		for (Evaluation evaluation : set2) {
			serBal2+= evaluation.getEvaluation();
		}
		
		serBal1 = serBal1/set1.size();
		serBal2 = serBal2/set2.size();
		
		bal1 = this.getEvaluationOfCertificate() + serBal1;
		bal2 = o.getEvaluationOfCertificate() + serBal2;
		
		    return new Integer(bal1).compareTo(new Integer(bal2));
	}
	
}
