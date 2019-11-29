package logos.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rating")
public class Rating implements Comparable<Rating>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "prof_id", referencedColumnName = "profession_id")
	private Profession profession;
	
	@Column
	private UserStatus status;

	public Rating() {}

	public Rating(int id, User user, Profession profession, UserStatus status) {
		super();
		this.id = id;
		this.user = user;
		this.profession = profession;
		this.status = status;
	}

	public Rating(User user, Profession profession, UserStatus status) {
		super();
		this.user = user;
		this.profession = profession;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Profession getProfession() {
		return profession;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((profession == null) ? 0 : profession.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Rating other = (Rating) obj;
		if (id != other.id)
			return false;
		if (profession == null) {
			if (other.profession != null)
				return false;
		} else if (!profession.equals(other.profession))
			return false;
		if (status != other.status)
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
		return "Rating [id=" + id + ", user=" + user + ", profession=" + profession + ", status=" + status + "]";
	}

	@Override
	public int compareTo(Rating o) {
		if (getUser() == null || o.getUser() == null) {
		      return 0;
		    }
		    return getUser().compareTo(o.getUser());
	}
}
