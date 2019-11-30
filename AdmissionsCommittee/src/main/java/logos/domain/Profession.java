package logos.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "profession")
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
	
	
	public Profession() {}

	public Profession(String name, int planOfstudent, Faculty faculty) {
		this.name = name;
		this.planOfStudent = planOfstudent;
		this.faculty = faculty;
		
	}

	public Profession(int id, String name, int planOfstudent, Faculty faculty) {
		this.id = id;
		this.name = name;
		this.planOfStudent = planOfstudent;
		this.faculty = faculty;
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



//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((faculty == null) ? 0 : faculty.hashCode());
//		result = prime * result + id;
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		result = prime * result + planOfStudent;
//		return result;
//	}

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
		return true;
	}

	@Override
	public String toString() {
		return "Profession [id=" + id + ", name=" + name + ", planOfstudent=" + planOfStudent + ", faculty=" + faculty
				+ "]";
	}
}
