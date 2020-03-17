package co.grandcircus.WatchYourBackpack.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BeastEvent extends Event{

	private int attackThresh;
	private int fireThresh;
	
	
	public BeastEvent() {
		super();
		rsrcThresh = 6;
	}
	
	
	public int getAttackThresh() {
		return attackThresh;
	}
	@Override
	public String toString() {
		return "BeastEvent [attackThresh=" + attackThresh + ", fireThresh=" + fireThresh + "]";
	}
	public void setAttackThresh(int attackThresh) {
		this.attackThresh = attackThresh;
	}
	public int getFireThresh() {
		return fireThresh;
	}
	public void setFireThresh(int fireThresh) {
		this.fireThresh = fireThresh;
	}
	
	
}
