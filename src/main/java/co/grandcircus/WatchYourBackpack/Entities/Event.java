package co.grandcircus.WatchYourBackpack.Entities;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

@MappedSuperclass
public abstract class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	protected String name;
	protected String description;
	protected int rsrcThresh;
	protected String eventImageUrl;
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
	public int getRsrcThresh() {
		return rsrcThresh;
	}
	public void setRsrcThresh(int rsrcThresh) {
		this.rsrcThresh = rsrcThresh;
	}
	public String getEventImageUrl() {
		return eventImageUrl;
	}
	public void setEventImageUrl(String eventImageUrl) {
		eventImageUrl = eventImageUrl;
	}
	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", description=" + description + ", rsrcThresh=" + rsrcThresh
				+ ", EventImageUrl=" + eventImageUrl + "]";
	}
	

	

}
