package co.grandcircus.WatchYourBackpack.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import co.grandcircus.WatchYourBackpack.DSModel.Currently;


@Entity
public class Character {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String type; //only three possible values: "fighter", "pyromaniac", "strategist"
	//so when the user chooses a type, that triggers different values for the stats? Would that be a switch case inside the setters?
	private int attack;
	private int fire;
	private int resourcefulness;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Item> items;
	
	
	private String startDate;//set the start date when Character is created. 
	//Don't forget to reset the start date when character plays again

	@OneToOne (cascade = CascadeType.ALL)
	private Currently weather;
	
	

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getFire() {
		return fire;
	}

	public void setFire(int fire) {
		this.fire = fire;
	}

	public int getResourcefulness() {
		return resourcefulness;
	}

	public void setResourcefulness(int resourcefulness) {
		this.resourcefulness = resourcefulness;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public Currently getWeather() {
		return weather;
	}

	public void setWeather(Currently weather) {
		this.weather = weather;
	}

	@Override
	public String toString() {
		return "Character [id=" + id + ", name=" + name + ", type=" + type + ", attack=" + attack + ", fire=" + fire
				+ ", resourcefulness=" + resourcefulness + ", items=" + items + ", startDate=" + startDate
				+ ", weather=" + weather + "]";
	}
	
	
	
	
}
