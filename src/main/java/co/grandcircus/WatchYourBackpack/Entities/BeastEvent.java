package co.grandcircus.WatchYourBackpack.Entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class BeastEvent extends Event{

	private int attackThresh;
	private int fireThresh;
	
	@OneToMany (mappedBy = "beastEvent")
	protected List<Outcome> outcomes;
	
	//constructor sets rsrcThresh to 6
	public BeastEvent() {
		super();
		rsrcThresh = 6;
	}
	
	public List<Outcome> getOutcomes() {
		return outcomes;
	}
	public void setOutcomes(List<Outcome> outcomes) {
		this.outcomes = outcomes;
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
