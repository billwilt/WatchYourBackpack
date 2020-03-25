package co.grandcircus.WatchYourBackpack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.grandcircus.WatchYourBackpack.Daos.PlayerDao;
import co.grandcircus.WatchYourBackpack.Entities.Player;

@Component
public class PlayerService {
	
	@Autowired
	private PlayerDao playerDao;

	public Player createPlayer(String name, String description, int type) {
		
				Player newPlayer = new Player(name, description, type);
				playerDao.save(newPlayer);
				return newPlayer;
	}
}
