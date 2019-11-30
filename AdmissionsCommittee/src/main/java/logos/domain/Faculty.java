package logos.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "faculties")
public class Faculty {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "faculty_id")
	private int id;
	
	@Column
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL,CascadeType.PERSIST,CascadeType.MERGE }, mappedBy = "faculty")
    @Column(nullable = false)
	private Set<Profession> professions = new HashSet<Profession>();
	
	public Faculty() {}

	public Faculty(String name, Set<Profession> professions) {
		this.name = name;
		this.professions = professions;
	}

	public Faculty(int id, String name, Set<Profession> professions) {
		this.id = id;
		this.name = name;
		this.professions = professions;
	}
	
	public Faculty(String name) {
		this.name = name;
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

	public Set<Profession> getProfessions() {
		return professions;
	}

	public void setProfessions(Set<Profession> professions) {
		this.professions = professions;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (professions == null) {
			if (other.professions != null)
				return false;
		} else if (!professions.equals(other.professions))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Faculty [id=" + id + ", name=" + name + ", professions=" + professions + "]";
	}
}
