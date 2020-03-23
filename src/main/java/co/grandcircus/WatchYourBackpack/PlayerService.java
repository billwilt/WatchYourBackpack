package co.grandcircus.WatchYourBackpack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.grandcircus.WatchYourBackpack.Daos.PlayerDao;
import co.grandcircus.WatchYourBackpack.Entities.Player;

@Component
public class PlayerService {
	
	@Autowired
	private PlayerDao playerDao;

	public void createPlayer(String name, String description, int type) {
		//by updating the pojo contructor we got the code down to one line ^.^
		playerDao.save(new Player(name, description, type));

		//player.setName(name);
		//player.setDescription(description);
		//player.setMoney(money);
		
		//player.setType(type);

//		if (type == 1) {
//			player.setAttack(1);
//			player.setFire(0);
//			player.setResourcefulness(0);
//		} else if (type == 2) {
//			player.setAttack(0);
//			player.setFire(1);
//			player.setResourcefulness(0);
//		} else {
//			player.setAttack(0);
//			player.setFire(0);
//			player.setResourcefulness(1);
//		}
	}
}
