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
	private String type; // only three possible values: "fighter", "pyromaniac", "strategist"
	// so when the user chooses a type, that triggers different values for the
	// stats? Would that be a switch case inside the setters?
	private Integer attack;
	private Integer fire;
	private Integer resourcefulness;
	private Double money;

	//we don't actually need to store the items in the player since they get reset after each game.
//	@ManyToMany
//	private List<Item> items;


	// @OneToOne (cascade = CascadeType.ALL)//I commented this out bc I don't think
	// we need Currently to be an entity
	// private Currently weather;//I don't think it needs to be stored in the
	// Character at all because it is only called once, right?

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

//	public List<Item> getItems() {
//		return items;
//	}
//
//	public void setItems(List<Item> items) {
//		this.items = items;
//	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", description=" + description + ", type=" + type + ", attack="
				+ attack + ", fire=" + fire + ", resourcefulness=" + resourcefulness + ", money=" + money + "]";
}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public void setAttack(Integer attack) {
		this.attack = attack;
	}

	public void setFire(Integer fire) {
		this.fire = fire;
	}

	public void setResourcefulness(Integer resourcefulness) {
		this.resourcefulness = resourcefulness;
	}

}
