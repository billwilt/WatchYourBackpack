package co.grandcircus.WatchYourBackpack.Daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.WatchYourBackpack.Entities.Outcome;
import co.grandcircus.WatchYourBackpack.Entities.WeatherEvent;

public interface OutcomesDao extends JpaRepository<Outcome, Long> {
	
	List<Outcome> findAllByBeastEventId(Long EventId);
	List<Outcome> findAllByWeatherEventId(Long EventId);
	

}
