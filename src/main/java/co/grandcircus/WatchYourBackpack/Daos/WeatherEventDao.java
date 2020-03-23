package co.grandcircus.WatchYourBackpack.Daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.WatchYourBackpack.Entities.WeatherEvent;

public interface WeatherEventDao extends JpaRepository<WeatherEvent, Long> {
	
	List<WeatherEvent> findAllByTriggerIconsContaining(String triggerIcon);
	
	WeatherEvent findByTriggerIconsContaining(String triggerIcon);
	

}
