package co.grandcircus.WatchYourBackpack;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.grandcircus.WatchYourBackpack.Daos.OutcomesDao;
import co.grandcircus.WatchYourBackpack.Entities.BeastEvent;
import co.grandcircus.WatchYourBackpack.Entities.GameStatus;
import co.grandcircus.WatchYourBackpack.Entities.Item;
import co.grandcircus.WatchYourBackpack.Entities.Outcome;
import co.grandcircus.WatchYourBackpack.Entities.Player;
import co.grandcircus.WatchYourBackpack.Entities.WeatherEvent;

@Component
public class GameService {

	@Autowired
	OutcomesDao oDao;

	public Integer getTotalAttack(Player player1, Player player2, Item item1, Item item2, Item item3) {
		Integer itemsAttack = item1.getAttackAdd() + item2.getAttackAdd() + item3.getAttackAdd();
		return player1.getAttack() + player2.getAttack() + itemsAttack;
	}

	public Integer getTotalFire(Player player1, Player player2, Item item1, Item item2, Item item3) {
		Integer itemsFire = item1.getFireAdd() + item2.getFireAdd() + item3.getFireAdd();
		return player1.getFire() + player2.getFire() + itemsFire;
	}

	public Integer getTotalResourcefulness(Player player1, Player player2, Item item1, Item item2, Item item3) {
		Integer itemsResourcefulness = item1.getResourcefulnessAdd() + item2.getResourcefulnessAdd()
				+ item3.getResourcefulnessAdd();
		return player1.getResourcefulness() + player2.getResourcefulness() + itemsResourcefulness;
	}

	public Outcome getFinalOutcome(GameStatus gameStatus, BeastEvent event, Integer choice) {

		int theirAttackSkill = gameStatus.getTotalAttack();
		int theirFireSkill = gameStatus.getTotalFire();
		int testAttackSkill = event.getAttackThresh();
		int testFireSkill = event.getFireThresh();

		boolean attackWin = winOrNot(theirAttackSkill, testAttackSkill);
		boolean fireWin = winOrNot(theirFireSkill, testFireSkill);
		boolean runAway = winOrNot(theirFireSkill, (testFireSkill + 2));

		Outcome finalOutcome;
		switch (choice) {
		case 1:
			finalOutcome = oDao.findByBeastEventIdAndChoiceAndSurvived(event.getId(), choice, attackWin);
			break;
		case 2:
			finalOutcome = oDao.findByBeastEventIdAndChoiceAndSurvived(event.getId(), choice, fireWin);
			break;
		default:
			finalOutcome = oDao.findByBeastEventIdAndChoiceAndSurvived(event.getId(), choice, runAway);
			break;
		}
		gameStatus.setHealth(gameStatus.getHealth() + finalOutcome.getHealthChange());

		return finalOutcome;
	}
	
	public Outcome getFinalOutcome(GameStatus gameStatus, WeatherEvent event, Integer choice) {
		int yourSkill = gameStatus.getTotalResourcefulness();
		int requiredSkill = event.getRsrcThresh();
		Outcome finalOutcome = new Outcome();
		
		if (choice.equals("1")) {
			if (winOrNot(yourSkill, requiredSkill)) {
				finalOutcome = oDao.findByWeatherEventIdAndChoiceAndSurvived(event.getId(), choice, true);
			} else {
				finalOutcome = oDao.findByWeatherEventIdAndChoiceAndSurvived(event.getId(), choice, false);
				gameStatus.setHealth(gameStatus.getHealth() - 1);
			}
		} else {
			if (yourSkill > requiredSkill) {
				finalOutcome = oDao.findByWeatherEventIdAndChoiceAndSurvived(event.getId(), choice, true);
				gameStatus.setHealth(gameStatus.getHealth() + 1);
			} else {
				finalOutcome = oDao.findByWeatherEventIdAndChoiceAndSurvived(event.getId(), choice, false);
				gameStatus.setHealth(gameStatus.getHealth() - 1);
			}
		}		return finalOutcome;
	}

	public boolean winOrNot(int theirLevel, int requiredLevel) {
		Random rand = new Random();

		double a = theirLevel + 1;
		double b = requiredLevel + 1;

		if (b > a) {
			double ourRand = rand.nextDouble() * a;
			double requiredRand = rand.nextDouble() * b;
			
			return test(ourRand, requiredRand);

		} else {
			double ourRand = rand.nextDouble() * (a * 3);
			double requiredRand = rand.nextDouble() * b;
			
			return test(ourRand, requiredRand);
		}
	}

	public boolean test(double theirSkill, double requiredSkill) {
		if (theirSkill > requiredSkill) {
			return true;
		} else
			return false;
	}

	

}
