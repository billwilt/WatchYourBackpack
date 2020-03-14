package co.grandcircus.WatchYourBackpack.DSModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Currently {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 	
	private String summary;
	private String icon;
	private Double temperature;
	
	//rework the below to work with National Parks?
	//@OneToOne(mappedBy = "weather")
	//private FavEvent favEvent;
	
	
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	
	@Override
	public String toString() {
		return "Currently [summary=" + summary + ", icon=" + icon + ", temperature=" + temperature + "]";
	}

	
	
}
