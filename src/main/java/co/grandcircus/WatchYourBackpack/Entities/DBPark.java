package co.grandcircus.WatchYourBackpack.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DBPark {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 	
	
	private String name; 
	private Double entranceFee; 
	
	
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
	public Double getEntranceFee() {
		return entranceFee;
	}
	public void setEntranceFee(Double entranceFee) {
		this.entranceFee = entranceFee;
	}
	@Override
	public String toString() {
		return "DBPark [id=" + id + ", name=" + name + ", entranceFee=" + entranceFee + "]";
	}
	
	
}
