package co.grandcircus.WatchYourBackpack.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WeatherEvent extends Event{
	

	private String triggerIcon;
	//if weather events don't have attack or fire thresholds... can they be null or do they have to be set to zero?

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
