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
	private int planOfstudent;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "faculty_id", nullable = false)
	private Faculty faculty;
	
	@ManyToMany(mappedBy = "professions")
	private Set<User> users = new HashSet<>();
	
	public Profession() {}

	public Profession(String name, int planOfstudent, Faculty faculty, Set<User> users) {
		this.name = name;
		this.planOfstudent = planOfstudent;
		this.faculty = faculty;
		this.users = users;
	}

	public Profession(int id, String name, int planOfstudent, Faculty faculty, Set<User> users) {
		this.id = id;
		this.name = name;
		this.planOfstudent = planOfstudent;
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

	public int getPlanOfstudent() {
		return planOfstudent;
	}

	public void setPlanOfstudent(int planOfstudent) {
		this.planOfstudent = planOfstudent;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((faculty == null) ? 0 : faculty.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + planOfstudent;
		result = prime * result + ((users == null) ? 0 : users.hashCode());
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
		if (planOfstudent != other.planOfstudent)
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
		return "Profession [id=" + id + ", name=" + name + ", planOfstudent=" + planOfstudent + ", faculty=" + faculty
				+ ", users=" + users + "]";
	}
}