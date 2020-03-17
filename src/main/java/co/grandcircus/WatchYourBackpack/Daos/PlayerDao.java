package co.grandcircus.WatchYourBackpack.Daos;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.WatchYourBackpack.Entities.Player;

public interface PlayerDao extends JpaRepository<Player, Long>{
	
}
