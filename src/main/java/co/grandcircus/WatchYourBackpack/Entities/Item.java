package co.grandcircus.WatchYourBackpack.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item {
	
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String itemName;
	private int attackAdd;
	private int fireAdd;
	private int resourcefulnessAdd;
	
	
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
