package co.grandcircus.WatchYourBackpack.Controllers;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.WatchYourBackpack.DSApiService;
import co.grandcircus.WatchYourBackpack.EventsService;
import co.grandcircus.WatchYourBackpack.ParksService;
import co.grandcircus.WatchYourBackpack.Daos.ItemDao;
import co.grandcircus.WatchYourBackpack.Daos.PlayerDao;
import co.grandcircus.WatchYourBackpack.Entities.BeastEvent;
import co.grandcircus.WatchYourBackpack.Entities.GameStatus;
import co.grandcircus.WatchYourBackpack.Entities.Outcome;
import co.grandcircus.WatchYourBackpack.Entities.Player;
import co.grandcircus.WatchYourBackpack.Entities.WeatherEvent;

@Controller
public class PlayController {

	@Autowired
	private HttpSession sesh;

	@Autowired
	private DSApiService DSApiServ;

	@Autowired
	private ParksService pService;

	@Autowired
	private PlayerDao playerDao;

	@Autowired
	private ItemDao itemDao;

	@Autowired
	private EventsService eventsService;


	@RequestMapping("/day1")
	public ModelAndView day1() {
		ModelAndView mav = new ModelAndView("day1");
		sesh.setAttribute("dayCount", 1);

		System.out.println(sesh.toString());

		// setting the players wallet to the new wallet amount
		Player player1 = (Player) sesh.getAttribute("player1");
		Player updatedPlayer = new Player();

		GameStatus gameStatus = (GameStatus) sesh.getAttribute("gameStatus");
		int totalLevel = gameStatus.getTotalAttack() + gameStatus.getTotalFire()
				+ gameStatus.getMainPlayer().getResourcefulness() + gameStatus.getPartner().getResourcefulness();

		int maxDays = 3 + (totalLevel / 3);
		sesh.setAttribute("maxDays", maxDays);

		Long player1Id = player1.getId();
		player1 = playerDao.findById(player1Id).orElse(null);

		updatedPlayer.setMoney((double) sesh.getAttribute("walletAfter"));
		updatedPlayer.setAttack(player1.getAttack());
		updatedPlayer.setFire(player1.getFire());
		updatedPlayer.setDescription(player1.getDescription());
		updatedPlayer.setId(player1.getId());
		updatedPlayer.setName(player1.getName());
		updatedPlayer.setResourcefulness(player1.getResourcefulness());
		updatedPlayer.setType(player1.getType());

		playerDao.save(updatedPlayer);

		// adding a random beast event to the model
		BeastEvent be1 = eventsService.findRandomBeastEvent();
		sesh.setAttribute("event1", be1);
		mav.addObject("event", be1);

		// adding the usual things to the model
		mav.addObject("player1", sesh.getAttribute("player1"));
		mav.addObject("player2", sesh.getAttribute("player2"));
		mav.addObject("gameStatus", gameStatus);
		mav.addObject("dayCount", 1);
		mav.addObject("maxDays", maxDays);

		// Here is a list of the items this model has
		// player1 player1 gameStatus event

		return mav;
	}

	@PostMapping("/day1")
	public ModelAndView day1results(String choice) {
		ModelAndView mav = new ModelAndView("day1results");
		GameStatus gameStatus = (GameStatus) sesh.getAttribute("gameStatus");
		BeastEvent event = (BeastEvent) sesh.getAttribute("event1");

		// getting sesh attributes to adjust them
		GameStatus gs = (GameStatus) sesh.getAttribute("gameStatus");

		// creating outcomes
		Outcome outcome1 = new Outcome();
		Outcome outcome2 = new Outcome();
		Outcome outcome3 = new Outcome();
		Outcome outcome4 = new Outcome();
		Outcome finalOutcome = new Outcome();

		outcome1.setSurvived(true);
		outcome2.setSurvived(true);
		outcome3.setSurvived(true);
		outcome4.setSurvived(false);

		outcome1.setDescription("You fought courageously and won");
		outcome2.setDescription("You outsmarted the enemy and got away safely");
		outcome3.setDescription("You managed to run away, and thank your good luck");
		outcome4.setDescription("You did not win, you lose 1 health");

		if (choice.equals("1")) {
			int theirSkill = gameStatus.getTotalAttack();
			int testSkill = event.getAttackThresh();

			if (theirSkill >= testSkill) {
				finalOutcome = outcome1;
			} else {
				finalOutcome = outcome4;
				gameStatus.setHealth(gameStatus.getHealth() - 1);
			}

		} else if (choice.equals("2")) {
			int theirSkill = gameStatus.getTotalFire();
			int testSkill = event.getFireThresh();

			if (theirSkill >= testSkill) {
				finalOutcome = outcome2;
			} else {
				finalOutcome = outcome4;
				gameStatus.setHealth(gameStatus.getHealth() - 1);
			}

		} else {
			Random rand = new Random();
			int random1 = rand.nextInt(100);

			if (random1 > 50) {
				finalOutcome = outcome3;
			} else {
				finalOutcome = outcome4;
				gameStatus.setHealth(gameStatus.getHealth() - 1);
			}
		}
		sesh.setAttribute("gameStatus", gs);
		mav.addObject("outcome", finalOutcome);
		return mav;
	}

	@RequestMapping("/day2")
	public ModelAndView day2() {
		ModelAndView mav = new ModelAndView("day2");

		// getting the gameStatus
		GameStatus gameStatus = new GameStatus();
		gameStatus = (GameStatus) sesh.getAttribute("gameStatus");

		// adding a weather event based on gameStatus
		WeatherEvent we1 = eventsService.findWeatherEvent(gameStatus.getWeather().getIcon());
		System.out.println(gameStatus.getWeather().getIcon());
		mav.addObject("event", we1);
		sesh.setAttribute("event2", we1);

		// adding the usual things to the model
		mav.addObject("player1", sesh.getAttribute("player1"));
		mav.addObject("player2", sesh.getAttribute("player2"));
		mav.addObject("gameStatus", gameStatus);

		// Here is the list of objects this model has
		// player1 player2 gamestatus event

		return mav;
	}

	@PostMapping("/day2")
	public ModelAndView day2results(String choice) {
		ModelAndView mav = new ModelAndView("day2results");

		GameStatus gameStatus = (GameStatus) sesh.getAttribute("gameStatus");
		WeatherEvent event = (WeatherEvent) sesh.getAttribute("event2");

		// getting sesh attributes to adjust them
		GameStatus gs = (GameStatus) sesh.getAttribute("gameStatus");

		// creating outcomes
		Outcome outcome1 = new Outcome();
		Outcome outcome2 = new Outcome();
		Outcome outcome3 = new Outcome();
		Outcome finalOutcome = new Outcome();

		outcome1.setSurvived(true);
		outcome2.setSurvived(true);
		outcome3.setSurvived(false);

		outcome1.setDescription("You safely made it through the day");
		outcome2.setDescription("You successfully got food, health up by 1 for your hard work");
		outcome3.setDescription("Your risk was not rewarded, you lost one health");

		if (choice.equals("1")) {
			int yourSkill = gameStatus.getTotalResourcefulness();
			int requiredSkill = event.getRsrcThresh();

			if (yourSkill >= requiredSkill) {
				finalOutcome = outcome1;
			} else {
				finalOutcome = outcome3;
				gameStatus.setHealth(gameStatus.getHealth() - 1);
			}
		} else {
			int yourSkill = gameStatus.getTotalResourcefulness();
			int requiredSkill = event.getRsrcThresh();

			if (yourSkill > requiredSkill) {
				finalOutcome = outcome2;
				gameStatus.setHealth(gameStatus.getHealth() + 1);
			} else {
				finalOutcome = outcome3;
				gameStatus.setHealth(gameStatus.getHealth() - 1);
			}
		}
		sesh.setAttribute("gameStatus", gs);
		mav.addObject("outcome", finalOutcome);
		return mav;
	}

	@RequestMapping("/day3")
	public ModelAndView day3() {
		ModelAndView mav = new ModelAndView("day3");
		Player player1 = (Player) sesh.getAttribute("player1");

		// adding a random beast event to the model
		BeastEvent be1 = eventsService.findRandomBeastEvent();
		mav.addObject("event", be1);
		sesh.setAttribute("event3", be1);

		// adding the usual things to the model
		mav.addObject("player1", player1);
		mav.addObject("player2", sesh.getAttribute("player2"));
		mav.addObject("gameStatus", sesh.getAttribute("gameStatus"));

		// Here is the list of objects the model has
		// player1 player2 gameStatus event

		return mav;
	}

	@PostMapping("/day3")
	public ModelAndView day3results(String choice) {
		ModelAndView mav = new ModelAndView("day3results");
		Random rand = new Random();

		GameStatus gameStatus = (GameStatus) sesh.getAttribute("gameStatus");
		BeastEvent event = (BeastEvent) sesh.getAttribute("event1");
		Player player1 = (Player) sesh.getAttribute("player1");

		Double money = player1.getMoney();
		int moneyFound = rand.nextInt(15) + 10;
		sesh.setAttribute("moneyFound", moneyFound);
		player1.setMoney(money + moneyFound);

		// creating outcomes
		Outcome outcome1 = new Outcome();
		Outcome outcome2 = new Outcome();
		Outcome outcome3 = new Outcome();
		Outcome outcome4 = new Outcome();
		Outcome finalOutcome = new Outcome();

		outcome1.setSurvived(true);
		outcome2.setSurvived(true);
		outcome3.setSurvived(true);
		outcome4.setSurvived(false);

		outcome1.setDescription("You fought courageously and won");
		outcome2.setDescription("You lit them on fire you maniac");
		outcome3.setDescription("You managed to run away, and thank your good luck");
		outcome4.setDescription("You did not win, you lose 1 health");

		if (choice.equals("1")) {
			int theirSkill = gameStatus.getTotalAttack();
			int testSkill = event.getAttackThresh();

			if (theirSkill >= testSkill) {
				finalOutcome = outcome1;
			} else {
				finalOutcome = outcome4;
				gameStatus.setHealth(gameStatus.getHealth() - 1);
			}

		} else if (choice.equals("2")) {
			int theirSkill = gameStatus.getTotalFire();
			int testSkill = event.getFireThresh();

			if (theirSkill >= testSkill) {
				finalOutcome = outcome2;
			} else {
				finalOutcome = outcome4;
				gameStatus.setHealth(gameStatus.getHealth() - 1);
			}

		} else {
			int random1 = rand.nextInt(100);

			if (random1 > 50) {
				finalOutcome = outcome3;
			} else {
				finalOutcome = outcome4;
				gameStatus.setHealth(gameStatus.getHealth() - 1);
			}
		}

		sesh.setAttribute("gameStatus", gameStatus);
		sesh.setAttribute("player1", player1);

		System.out.println(finalOutcome);

		mav.addObject("moneyFound", moneyFound);
		mav.addObject("outcome", finalOutcome);
		return mav;
	}

	@RequestMapping("/conclusion")
	public ModelAndView conclusion() {

		ModelAndView mav = new ModelAndView();

		Player player1 = (Player) sesh.getAttribute("player1");
		GameStatus gameStatus = (GameStatus) sesh.getAttribute("gameStatus");

		if (gameStatus.getHealth() > 0) {
			ModelAndView mav1 = new ModelAndView("conclusion");
			mav = mav1;
		} else {
			ModelAndView mav2 = new ModelAndView("conclusion2");
			mav = mav2;
		}

		int moneyFound = (int) sesh.getAttribute("moneyFound");

		mav.addObject("moneyFound", moneyFound);
		mav.addObject("gameStatus", gameStatus);
		mav.addObject("player1", player1);
		return mav;
	}

	@PostMapping("/backHome")
	public ModelAndView backHome(String skill) {

		Player player1 = (Player) sesh.getAttribute("player1");

		if (skill.equals("1")) {
			player1.setAttack(player1.getAttack() + 1);
		} else if (skill.equals("2")) {
			player1.setFire(player1.getFire() + 1);
		} else if (skill.equals("3")) {
			player1.setResourcefulness(player1.getResourcefulness() + 1);
		}

		playerDao.save(player1);
		return new ModelAndView("redirect:/");
	}



///////////////////////////////////////////////////////////BEAST DAY///////////////////////////////////////////////////////////////////

	@RequestMapping("/genericBeastDay")
	public ModelAndView genericBeastDay() {
		ModelAndView mav = new ModelAndView("genericBeastDay");
		int dayCount = (int) sesh.getAttribute("dayCount");
		int maxDays = (int) sesh.getAttribute("maxDays");

		/////////// getting objects from sessison //////////
		GameStatus gameStatus = (GameStatus) sesh.getAttribute("gameStatus");
		Player player1 = (Player) sesh.getAttribute("player1");
		Player player2 = (Player) sesh.getAttribute("player2");

		///////// generating beast event and adding to session /////////////////
		BeastEvent beastEvent = eventsService.findRandomBeastEvent();
		sesh.setAttribute("event", beastEvent);

		//////////// adding everything to model /////////////////////////
		mav.addObject("player1", player1);
		mav.addObject("player2", player2);
		mav.addObject("event", beastEvent);
		mav.addObject("gameStatus", gameStatus);
		mav.addObject("dayCount", dayCount);
		mav.addObject("maxDays", maxDays);

		return mav;
	}

	@PostMapping("/genericBeastDay")
	public ModelAndView genericBeastDayPost(String choice) {
		ModelAndView mav = new ModelAndView("genericBeastDayPost");
		int maxDays = (int) sesh.getAttribute("maxDays");

		/////////// getting objects from sessison //////////
		GameStatus gameStatus = (GameStatus) sesh.getAttribute("gameStatus");
		BeastEvent event = (BeastEvent) sesh.getAttribute("event");
		Player player1 = (Player) sesh.getAttribute("player1");
		Player player2 = (Player) sesh.getAttribute("player2");

		///////////// creating outcomes //////////////////
		Outcome outcome1 = new Outcome();
		Outcome outcome2 = new Outcome();
		Outcome outcome3 = new Outcome();
		Outcome outcome4 = new Outcome();
		Outcome finalOutcome = new Outcome();

		outcome1.setSurvived(true);
		outcome2.setSurvived(true);
		outcome3.setSurvived(true);
		outcome4.setSurvived(false);

		outcome1.setDescription("You fought courageously and won.");
		outcome2.setDescription("You lit them on fire you maniac!!");
		outcome3.setDescription("You managed to run away, and thank your good luck!");
		outcome4.setDescription("You did not win, you lose 1 health");

		if (choice.equals("1")) {
			int theirSkill = gameStatus.getTotalAttack();
			int testSkill = event.getAttackThresh();

			if (theirSkill >= testSkill) {
				finalOutcome = outcome1;
			} else {
				finalOutcome = outcome4;
				gameStatus.setHealth(gameStatus.getHealth() - 1);
			}

		} else if (choice.equals("2")) {
			int theirSkill = gameStatus.getTotalFire();
			int testSkill = event.getFireThresh();

			if (theirSkill >= testSkill) {
				finalOutcome = outcome2;
			} else {
				finalOutcome = outcome4;
				gameStatus.setHealth(gameStatus.getHealth() - 1);
			}

		} else {
			Random rand = new Random();
			int random1 = rand.nextInt(100);

			if (random1 > 50) {
				finalOutcome = outcome3;
			} else {
				finalOutcome = outcome4;
				gameStatus.setHealth(gameStatus.getHealth() - 1);
			}
		}
		sesh.setAttribute("gameStatus", gameStatus);

		/////////////// adding to the day count ///////////////////
		int dayCount = (int) sesh.getAttribute("dayCount");
		dayCount += 1;
		sesh.setAttribute("dayCount", dayCount);

		//////////// adding everything to model /////////////////////////
		mav.addObject("player1", player1);
		mav.addObject("player2", player2);
		mav.addObject("event", event);
		mav.addObject("outcome", finalOutcome);
		mav.addObject("gameStatus", gameStatus);
		mav.addObject("dayCount", dayCount);
		mav.addObject("maxDays", maxDays);

		return mav;
	}

	/////////////////////////////////////////// WEATHER DAY//////////////////////////////////////////////////////

	@RequestMapping("/genericWeatherDay")
	public ModelAndView genericWeatherDay() {
		ModelAndView mav = new ModelAndView("genericWeatherDay");
		int dayCount = (int) sesh.getAttribute("dayCount");
		int maxDays = (int) sesh.getAttribute("maxDays");

		/////////// getting objects from sessison //////////
		GameStatus gameStatus = (GameStatus) sesh.getAttribute("gameStatus");
		Player player1 = (Player) sesh.getAttribute("player1");
		Player player2 = (Player) sesh.getAttribute("player2");

		///////// generating beast event and adding to session /////////////////
		WeatherEvent weatherEvent = eventsService.findWeatherEvent(gameStatus.getWeather().getIcon());
		sesh.setAttribute("event", weatherEvent);

		//////////// adding everything to model /////////////////////////
		mav.addObject("player1", player1);
		mav.addObject("player2", player2);
		mav.addObject("event", weatherEvent);
		mav.addObject("gameStatus", gameStatus);
		mav.addObject("dayCount", dayCount);
		mav.addObject("maxDays", maxDays);

		return mav;
	}

	@PostMapping("/genericWeatherDay")
	public ModelAndView genericWeatherDayPost(String choice) {
		ModelAndView mav = new ModelAndView("genericWeatherDay");
		int maxDays = (int) sesh.getAttribute("maxDays");

		////////////////// getting objects from sessison //////////////////
		GameStatus gameStatus = (GameStatus) sesh.getAttribute("gameStatus");
		WeatherEvent event = (WeatherEvent) sesh.getAttribute("event");
		Player player1 = (Player) sesh.getAttribute("player1");
		Player player2 = (Player) sesh.getAttribute("player2");

		//////////////// creating outcomes ////////////////////////////
		Outcome outcome1 = new Outcome();
		Outcome outcome2 = new Outcome();
		Outcome outcome3 = new Outcome();
		Outcome finalOutcome = new Outcome();

		outcome1.setSurvived(true);
		outcome2.setSurvived(true);
		outcome3.setSurvived(false);

		outcome1.setDescription("You safely made it through the day");
		outcome2.setDescription("You successfully got food, health up by 1 for your hard work");
		outcome3.setDescription("Your risk was not rewarded, you lost one health");

		if (choice.equals("1")) {
			int yourSkill = gameStatus.getTotalResourcefulness();
			int requiredSkill = event.getRsrcThresh();

			if (yourSkill >= requiredSkill) {
				finalOutcome = outcome1;
			} else {
				finalOutcome = outcome3;
				gameStatus.setHealth(gameStatus.getHealth() - 1);
			}
		} else {
			int yourSkill = gameStatus.getTotalResourcefulness();
			int requiredSkill = event.getRsrcThresh();

			if (yourSkill > requiredSkill) {
				finalOutcome = outcome2;
				gameStatus.setHealth(gameStatus.getHealth() + 1);
			} else {
				finalOutcome = outcome3;
				gameStatus.setHealth(gameStatus.getHealth() - 1);
			}
		}

		sesh.setAttribute("gameStatus", gameStatus);
		sesh.setAttribute("player1", player1);

		/////////////// adding to the day count ///////////////////
		int dayCount = (int) sesh.getAttribute("dayCount");
		dayCount += 1;
		sesh.setAttribute("dayCount", dayCount);

		//////////// adding everything to model /////////////////////////
		mav.addObject("player1", player1);
		mav.addObject("player2", player2);
		mav.addObject("event", event);
		mav.addObject("outcome", finalOutcome);
		mav.addObject("gameStatus", gameStatus);
		mav.addObject("dayCount", dayCount);
		mav.addObject("maxDays", maxDays);

		return mav;
	}

	/////////////////////////////////////// DAY CONTROLLER /////////////////////////////////////////////////////////////////

	@PostMapping("/dayController")
	public ModelAndView dayController() {

		////////// getting day count and max day from session /////////////////////
		int dayCount = (int) sesh.getAttribute("dayCount");
		int maxDays = (int) sesh.getAttribute("maxDays");

		///////// if it's the last day send them to conclusion ////////////////
		if (dayCount >= maxDays) {
			return new ModelAndView("redirect:/conclusion");
		}

		///////// RETURNING THE WHATEVER EVENT THEY DIDNT GET ////////////
		if (sesh.getAttribute("event").getClass() == WeatherEvent.class) {
			return new ModelAndView("redirect:/genericBeastEvent");

		} else {
			return new ModelAndView("redirect:/genericWeatherEvent");
			
		}
	}
}
