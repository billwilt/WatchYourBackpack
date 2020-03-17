package co.grandcircus.WatchYourBackpack.Entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	protected String name;
	protected String description;
	protected String outcome;
	protected int rsrcThresh;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOutcome() {
		return outcome;
	}
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	public int getRsrcThresh() {
		return rsrcThresh;
	}
	public void setRsrcThresh(int rsrcThresh) {
		this.rsrcThresh = rsrcThresh;
	}
	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", description=" + description + ", outcome=" + outcome
				+ ", rsrcThresh=" + rsrcThresh + "]";
	}
	

}
