package co.grandcircus.WatchYourBackpack.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Outcome {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	private Boolean survived;
	private String description;
	@ManyToOne 
	private WeatherEvent weatherEvent;
	@ManyToOne 
	private BeastEvent beastEvent;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getSurvived() {
		return survived;
	}
	public void setSurvived(Boolean survived) {
		this.survived = survived;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Outcome [id=" + id + ", survived=" + survived + ", description=" + description + "]";
	}
	
	
}
