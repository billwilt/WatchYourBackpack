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

	private String triggerIcon;

	public List<Outcome> getOutcomes() {
		return outcomes;
	}
	public void setOutcomes(List<Outcome> outcomes) {
		this.outcomes = outcomes;
	}
	
	public String getTriggerIcon() {
		return triggerIcon;
	}

	public void setTriggerIcon(String triggerIcon) {
		this.triggerIcon = triggerIcon;
	}

	@Override
	public String toString() {
		return "WeatherEvent [triggerIcon=" + triggerIcon + "]";
	}
	
	
}
