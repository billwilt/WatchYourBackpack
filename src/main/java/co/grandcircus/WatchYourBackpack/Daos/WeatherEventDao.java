package co.grandcircus.WatchYourBackpack.Daos;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.WatchYourBackpack.Entities.WeatherEvent;

public interface WeatherEventDao extends JpaRepository<WeatherEvent, Long> {

}
