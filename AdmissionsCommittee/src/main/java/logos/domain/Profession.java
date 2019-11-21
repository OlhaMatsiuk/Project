package logos.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "professions")
public class Profession {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "profession_id")
	private int id;
	
	@Column
	private String name;
	
	@Column
	private int planOfStudent;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "profession_of_faculty", nullable = false)
	private Faculty faculty;
	
	@ManyToMany(mappedBy = "professions")
	private Set<User> users = new HashSet<>();
	
	public Profession() {}

	public Profession(String name, int planOfstudent, Faculty faculty, Set<User> users) {
		this.name = name;
		this.planOfStudent = planOfstudent;
		this.faculty = faculty;
		this.users = users;
	}
	public Profession(String name, int planOfstudent, Faculty faculty) {
		this.name = name;
		this.planOfStudent = planOfstudent;
		this.faculty = faculty;
	}

	public Profession(int id, String name, int planOfstudent, Faculty faculty, Set<User> users) {
		this.id = id;
		this.name = name;
		this.planOfStudent = planOfstudent;
		this.faculty = faculty;
		this.users = users;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPlanOfStudent() {
		return planOfStudent;
	}

	public void setPlanOfStudent(int planOfStudent) {
		this.planOfStudent = planOfStudent;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}	


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profession other = (Profession) obj;
		if (faculty == null) {
			if (other.faculty != null)
				return false;
		} else if (!faculty.equals(other.faculty))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (planOfStudent != other.planOfStudent)
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Profession [id=" + id + ", name=" + name + ", planOfstudent=" + planOfStudent + ", faculty=" + faculty
				+ ", users=" + users + "]";
	}
}
