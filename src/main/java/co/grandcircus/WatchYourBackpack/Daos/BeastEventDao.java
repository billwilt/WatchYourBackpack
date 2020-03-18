package co.grandcircus.WatchYourBackpack.Daos;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.WatchYourBackpack.Entities.BeastEvent;

public interface BeastEventDao extends JpaRepository<BeastEvent, Long> {
	
}
