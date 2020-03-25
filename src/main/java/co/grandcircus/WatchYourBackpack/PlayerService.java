package co.grandcircus.WatchYourBackpack;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.grandcircus.WatchYourBackpack.Daos.PlayerDao;
import co.grandcircus.WatchYourBackpack.Entities.Item;
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

	public List<Player> getAvailableTeam(Long id) {
		List<Player> availableTeam = new ArrayList<>();
		for (Long i = 1L; i <= playerDao.count(); i++) {
			if (i != id) {
				availableTeam.add(playerDao.getOne(i));
			}
		}
		return availableTeam;
	}
}
