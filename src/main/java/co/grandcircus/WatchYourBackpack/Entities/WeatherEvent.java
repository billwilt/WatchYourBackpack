package co.grandcircus.WatchYourBackpack.Entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class WeatherEvent extends Event{
	
	@OneToMany (mappedBy = "weatherEvent")
	protected List<Outcome> outcomes;
	private List<String> triggerIcon;

	public List<Outcome> getOutcomes() {
		return outcomes;
	}
	public void setOutcomes(List<Outcome> outcomes) {
		this.outcomes = outcomes;
	}
	@Override
	public String toString() {
		return "WeatherEvent [outcomes=" + outcomes + ", triggerIcon=" + triggerIcon + "]";
	}
	public List<String> getTriggerIcon() {
		return triggerIcon;
	}
	public void setTriggerIcon(List<String> triggerIcon) {
		this.triggerIcon = triggerIcon;
	}
	
	
}
