package co.grandcircus.WatchYourBackpack.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String description;// The description will show next to the character's name when user is choosing
								// character.
	// We will fill in the description of the stock characters so it gives user clue
	// to character's stats
	// We will have to explain to user when creating their own character how the
	// description will be shown
	private Integer type; // only three possible values: "fighter", "pyromaniac", "strategist"
	// so when the user chooses a type, that triggers different values for the
	// stats? Would that be a switch case inside the setters?
	private Integer attack = 0;
	private Integer fire = 0;
	private Integer resourcefulness = 0;
	private Double money = 10.00;


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



	public Integer getType() {
		return type;
	}



	public void setType(Integer type) {
		this.type = type;
	}



	public Integer getAttack() {
		return attack;
	}



	public void setAttack(Integer attack) {
		this.attack = attack;
	}



	public Integer getFire() {
		return fire;
	}



	public void setFire(Integer fire) {
		this.fire = fire;
	}



	public Integer getResourcefulness() {
		return resourcefulness;
	}



	public void setResourcefulness(Integer resourcefulness) {
		this.resourcefulness = resourcefulness;
	}



	public Double getMoney() {
		return money;
	}



	public void setMoney(Double money) {
		this.money = money;
	}
	
	public Player() {
		
	}
	
	public Player(String name, String description, Integer type) {
		super();
		this.name = name;
		this.description = description;
		this.type = type;
		switch (type) {
		case 1:
			attack = 1;
			break;
		case 2:
			fire = 1;
			break;
		case 3:
			resourcefulness = 1;
			break;
		}
	}

}
