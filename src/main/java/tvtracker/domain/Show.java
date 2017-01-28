package tvtracker.domain;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "shows")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Show {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	private String name;
	
	@JsonIgnore
    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Subscription> subscriptions = new HashSet<>();
	
	private int tvmazeId;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Image image;
	
	@Column(length = 1535)
	private String summary;
	
	// Constructor
	public Show() {
		
	}
	
	// Getters and setters
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

	public Set<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Set<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public int getTvmazeId() {
		return tvmazeId;
	}

	public void setTvmazeId(int tvmazeId) {
		this.tvmazeId = tvmazeId;
	}
	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Override
	public String toString() {
		return "Show [id=" + id + ", name=" + name + ", subscriptions=" + subscriptions + ", tvmazeId=" + tvmazeId
				+ ", image=" + image + ", summary=" + summary + "]";
	}
	
	
	
	
	
}


