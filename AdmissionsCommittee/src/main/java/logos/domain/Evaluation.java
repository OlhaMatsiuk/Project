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
@Table(name = "evaluations")
public class Evaluation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "evaluation_id")
	private int id;
	
	@Column
	private String nameSubject;
	
	@Column
	private int evaluation;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Evaluation() {}

	public Evaluation(String nameSubject, int evaluation, User user) {
		this.nameSubject = nameSubject;
		this.evaluation = evaluation;
		this.user = user;
	}
	
	public Evaluation(int id, String nameSubject, int evaluation, User user) {
		this.id = id;
		this.nameSubject = nameSubject;
		this.evaluation = evaluation;
		this.user = user;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameSubject() {
		return nameSubject;
	}

	public void setNameSubject(String nameSubject) {
		this.nameSubject = nameSubject;
	}

	public int getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + evaluation;
//		result = prime * result + id;
//		result = prime * result + ((nameSubject == null) ? 0 : nameSubject.hashCode());
//		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Evaluation other = (Evaluation) obj;
		if (evaluation != other.evaluation)
			return false;
		if (id != other.id)
			return false;
		if (nameSubject == null) {
			if (other.nameSubject != null)
				return false;
		} else if (!nameSubject.equals(other.nameSubject))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Evaluation [id=" + id + ", nameSubject=" + nameSubject + ", evaluation=" + evaluation + ", user=" + user
				+ "]";
	}
}
