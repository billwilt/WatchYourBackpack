package co.grandcircus.WatchYourBackpack.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Item {
	
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String itemName;
	private Integer attackAdd;
	private Integer fireAdd;
	private Integer resourcefulnessAdd;
	
	@ManyToMany   //(cascade = CascadeType.ALL)
	private List<Player> players;//I honestly don't understand why we would need this but I'm putting it in and crossing my fingers it solves my issue creating the db
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getAttackAdd() {
		return attackAdd;
	}
	public void setAttackAdd(int attackAdd) {
		this.attackAdd = attackAdd;
	}
	public int getFireAdd() {
		return fireAdd;
	}
	public void setFireAdd(int fireAdd) {
		this.fireAdd = fireAdd;
	}
	public int getResourcefulnessAdd() { 
		return resourcefulnessAdd;
	}
	public void setResourcefulnessAdd(int resourcefulnessAdd) {
		this.resourcefulnessAdd = resourcefulnessAdd;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", itemName=" + itemName + ", attackAdd=" + attackAdd + ", fireAdd=" + fireAdd
				+ ", resourcefulnessAdd=" + resourcefulnessAdd + "]";
	}

	
	
}
