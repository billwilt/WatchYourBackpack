package co.grandcircus.WatchYourBackpack.Entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class WeatherEvent extends Event{
	
	@OneToMany (mappedBy = "weatherEvent")
	protected List<Outcome> outcomes;
	private String triggerIcons;
	
	public List<Outcome> getOutcomes() {
		return outcomes;
	}
	public void setOutcomes(List<Outcome> outcomes) {
		this.outcomes = outcomes;
	}
	
	@Override
	public String toString() {
		return "WeatherEvent [outcomes=" + outcomes + ", triggerIcon=" + triggerIcons + "]";
	}
	public String getTriggerIcons() {
		return triggerIcons;
	}
	public void setTriggerIcons(String triggerIcons) {
		this.triggerIcons = triggerIcons;
	}
}
