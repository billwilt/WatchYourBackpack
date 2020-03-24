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

	
// working on making the win or loss random, just getting lost trying to figure out how to actually use it though

	public boolean winOrNot(int theirLevel, int requiredLevel) {
		Random rand = new Random();
		
		double a = theirLevel + 1;
		double b = requiredLevel + 1;
		
		if (b > a) {
			double ourRand = rand.nextDouble() * a;
			double requiredRand = rand.nextDouble() * b;
			
			System.out.println("your random: " + ourRand);
			System.out.println("required random: " + requiredRand);
			
			return test(a, b);
			
		} else {
			double ourRand = rand.nextDouble() * (a * 3);
			double requiredRand = rand.nextDouble() * b;
			
			System.out.println("your random: " + ourRand);
			System.out.println("required random: " + requiredRand);
			
			return test(a, b);
			
		}
	}
	
	public boolean test(double theirSkill, double requiredSkill) {
		if (theirSkill > requiredSkill) {
			return true;
		} else 
			return false;
	}
	
	////////////////////// DAY 1 /////////////////////////////////////

	@RequestMapping("/day1")
	public ModelAndView day1() {
		ModelAndView mav = new ModelAndView("day1");
		GameStatus gameStatus = (GameStatus) sesh.getAttribute("gameStatus");

		// setting the players wallet to the new wallet amount
		Player player1 = gameStatus.getMainPlayer();
		Long player1Id = player1.getId();
		player1.setMoney((double) sesh.getAttribute("walletAfter"));

		int totalLevel = gameStatus.getTotalAttack() + gameStatus.getTotalFire()
				+ gameStatus.getMainPlayer().getResourcefulness() + gameStatus.getPartner().getResourcefulness();

		// setting the max days for modular days
		int maxDays = 3 + (totalLevel / 3);
		sesh.setAttribute("maxDays", maxDays);
		sesh.setAttribute("dayCount", 1);

		// adding a random beast event to the model
		BeastEvent be1 = eventsService.findRandomBeastEvent();
		sesh.setAttribute("event", be1);

		// re-setting the game status with the new changes
		sesh.setAttribute("gameStatus", gameStatus);

		return mav;
	}

	@PostMapping("/day1")
	public ModelAndView day1results(String choice) {
		ModelAndView mav = new ModelAndView("day1results");
		Random rand = new Random();

		GameStatus gameStatus = (GameStatus) sesh.getAttribute("gameStatus");
		BeastEvent event = (BeastEvent) sesh.getAttribute("event");

		// getting sesh attributes to adjust them
		GameStatus gs = (GameStatus) sesh.getAttribute("gameStatus");

		///////////// creating outcomes //////////////////
		Outcome outcome1 = new Outcome(true, "You fought courageously and won.");
		Outcome outcome2 = new Outcome(true, "You lit them on fire you maniac!!");
		Outcome outcome3 = new Outcome(true, "You managed to run away, and thank your good luck!");
		Outcome outcome4 = new Outcome(false, "You did not win, you lose 1 health");
		Outcome finalOutcome = new Outcome();

		System.out.println(event);

		int theirAttackSkill = gameStatus.getTotalAttack();
		int theirFireSkill = gameStatus.getTotalFire();
		int testAttackSkill = event.getAttackThresh();
		int testFireSkill = event.getFireThresh();
		
		if (choice.equals("1")) {
			if (winOrNot(theirAttackSkill, testAttackSkill)) {
				finalOutcome = outcome1;
			} else {
				finalOutcome = outcome4;
				gameStatus.setHealth(gameStatus.getHealth() - 1);
			}

		} else if (choice.equals("2")) {
			if (winOrNot(theirFireSkill, testFireSkill)) {
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
		sesh.setAttribute("gameStatus", gs);
		mav.addObject("outcome", finalOutcome);
		return mav;
	}

	//////////////////////////////////// BEAST DAY //////////////////////////////////////////

	@RequestMapping("/genericBeastDay")
	public ModelAndView genericBeastDay() {
		ModelAndView mav = new ModelAndView("genericBeastDay");
		int dayCount = (int) sesh.getAttribute("dayCount");
		int maxDays = (int) sesh.getAttribute("maxDays");

		///////// generating beast event and adding to session /////////////////
		BeastEvent beastEvent = eventsService.findRandomBeastEvent();
		sesh.setAttribute("event", beastEvent);

		return mav;
	}

	@PostMapping("/genericBeastDay")
	public ModelAndView genericBeastDayPost(String choice) {
		ModelAndView mav = new ModelAndView("genericBeastDayPost");
		Random rand = new Random();

		/////////// getting objects from sessison //////////
		GameStatus gameStatus = (GameStatus) sesh.getAttribute("gameStatus");
		BeastEvent event = (BeastEvent) sesh.getAttribute("event");

		///////////// creating outcomes //////////////////
		Outcome outcome1 = new Outcome(true, "You fought courageously and won.");
		Outcome outcome2 = new Outcome(true, "You lit them on fire you maniac!!");
		Outcome outcome3 = new Outcome(true, "You managed to run away, and thank your good luck!");
		Outcome outcome4 = new Outcome(false, "You did not win, you lose 1 health");
		Outcome finalOutcome = new Outcome();


		int theirAttackSkill = gameStatus.getTotalAttack();
		int theirFireSkill = gameStatus.getTotalFire();
		int testAttackSkill = event.getAttackThresh();
		int testFireSkill = event.getFireThresh();
		
		if (choice.equals("1")) {
			if (winOrNot(theirAttackSkill, testAttackSkill)) {
				finalOutcome = outcome1;
			} else {
				finalOutcome = outcome4;
				gameStatus.setHealth(gameStatus.getHealth() - 1);
			}

		} else if (choice.equals("2")) {
			if (winOrNot(theirFireSkill, testFireSkill)) {
				finalOutcome = outcome2;
			} else {
				finalOutcome = outcome4;
				gameStatus.setHealth(gameStatus.getHealth() - 1);
			}
		}

		 else {
			int random1 = rand.nextInt(100);

			if (random1 > 50) {
				finalOutcome = outcome3;
			} else {
				finalOutcome = outcome4;
				gameStatus.setHealth(gameStatus.getHealth() - 1);
			}
		}
		sesh.setAttribute("gameStatus", gameStatus);
		int dayCount = (int) sesh.getAttribute("dayCount");

		//////////// adding everything to model /////////////////////////
		mav.addObject("event", event);
		mav.addObject("outcome", finalOutcome);
		;
		mav.addObject("dayCount", dayCount);

		return mav;
	}

	///////////////////////////////// WEATHER DAY ////////////////////////////////////////////

	@RequestMapping("/genericWeatherDay")
	public ModelAndView genericWeatherDay() {
		ModelAndView mav = new ModelAndView("genericWeatherDay");
		GameStatus gameStatus = (GameStatus) sesh.getAttribute("gameStatus");

		///////// generating weather event and adding to session /////////////////
		WeatherEvent weatherEvent = eventsService.findWeatherEvent(gameStatus.getWeather().getIcon());
		sesh.setAttribute("event", weatherEvent);

		return mav;
	}

	@PostMapping("/genericWeatherDay")
	public ModelAndView genericWeatherDayPost(String choice) {
		ModelAndView mav = new ModelAndView("genericWeatherDayPost");

		////////////////// getting objects from sessison //////////////////
		GameStatus gameStatus = (GameStatus) sesh.getAttribute("gameStatus");
		WeatherEvent event = (WeatherEvent) sesh.getAttribute("event");

		//////////////// creating outcomes ////////////////////////////
		Outcome outcome1 = new Outcome(true, "You safely made it through the day");
		Outcome outcome2 = new Outcome(true, "You successfully got food, health up by 1 for your hard work");
		Outcome outcome3 = new Outcome(false, "Your risk was not rewarded, you lost one health");
		Outcome finalOutcome = new Outcome();

		int yourSkill = gameStatus.getTotalResourcefulness();
		int requiredSkill = event.getRsrcThresh();

		if (choice.equals("1")) {
			if (winOrNot(yourSkill, requiredSkill)) {
				finalOutcome = outcome1;
			} else {
				finalOutcome = outcome3;
				gameStatus.setHealth(gameStatus.getHealth() - 1);
			}
		} else {
			if (yourSkill > requiredSkill) {
				finalOutcome = outcome2;
				gameStatus.setHealth(gameStatus.getHealth() + 1);
			} else {
				finalOutcome = outcome3;
				gameStatus.setHealth(gameStatus.getHealth() - 1);
			}
		}

		sesh.setAttribute("gameStatus", gameStatus);
		int dayCount = (int) sesh.getAttribute("dayCount");

		//////////// adding everything to model /////////////////////////
		mav.addObject("event", event);
		mav.addObject("outcome", finalOutcome);
		mav.addObject("dayCount", dayCount);

		return mav;
	}

	/////////////////////////// DAY CONTROLLER ////////////////////////////////////////////////////

	@PostMapping("/dayController")
	public ModelAndView dayController() {

		////////// getting day count and max day from session /////////////////////
		int dayCount = (int) sesh.getAttribute("dayCount");
		int maxDays = (int) sesh.getAttribute("maxDays");

		dayCount += 1;
		sesh.setAttribute("dayCount", dayCount);

		///////// if it's the last day send them to conclusion ////////////////
		if (dayCount >= maxDays) {
			return new ModelAndView("redirect:/conclusion");
		}

		///////// RETURNING THE WHATEVER EVENT THEY DIDNT GET ////////////
		if (sesh.getAttribute("event").getClass() == WeatherEvent.class) {
			return new ModelAndView("redirect:/genericBeastDay");

		} else {
			return new ModelAndView("redirect:/genericWeatherDay");

		}
	}

	//////////////////////////// FINAL DAY /////////////////////////////////////////
	
	@RequestMapping("/conclusion")
	public ModelAndView conclusion() {
		GameStatus gameStatus = (GameStatus) sesh.getAttribute("gameStatus");
		Random rand = new Random();

		Player player1 = gameStatus.getMainPlayer();
		Double money = player1.getMoney();
		int moneyFound = rand.nextInt(15) + 10;
		sesh.setAttribute("moneyFound", moneyFound);
		player1.setMoney(money + moneyFound);

		if (gameStatus.getHealth() > 0) {
			return new ModelAndView("conclusion");
		} else {
			return new ModelAndView("conclusion2");
		}
	}

	@PostMapping("/backHome")
	public ModelAndView backHome(String skill) {

		GameStatus gameStatus = (GameStatus) sesh.getAttribute("gameStatus");
		Player player1 = gameStatus.getMainPlayer();

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

//
//	@RequestMapping("/day2")
//	public ModelAndView day2() {
//		ModelAndView mav = new ModelAndView("day2");
//
//		// getting the gameStatus
//		GameStatus gameStatus = (GameStatus) sesh.getAttribute("gameStatus");
//
//		// adding a weather event based on gameStatus
//		WeatherEvent we1 = eventsService.findWeatherEvent(gameStatus.getWeather().getIcon());
//		mav.addObject("event", we1);
//		sesh.setAttribute("event2", we1);
//		
//		return mav;
//	}
//
//	@PostMapping("/day2")
//	public ModelAndView day2results(String choice) {
//		ModelAndView mav = new ModelAndView("day2results");
//
//		GameStatus gameStatus = (GameStatus) sesh.getAttribute("gameStatus");
//		WeatherEvent event = (WeatherEvent) sesh.getAttribute("event2");
//
//		// getting sesh attributes to adjust them
//		GameStatus gs = (GameStatus) sesh.getAttribute("gameStatus");
//
//		// creating outcomes
//		Outcome outcome1 = new Outcome();
//		Outcome outcome2 = new Outcome();
//		Outcome outcome3 = new Outcome();
//		Outcome finalOutcome = new Outcome();
//
//		outcome1.setSurvived(true);
//		outcome2.setSurvived(true);
//		outcome3.setSurvived(false);
//
//		outcome1.setDescription("You safely made it through the day");
//		outcome2.setDescription("You successfully got food, health up by 1 for your hard work");
//		outcome3.setDescription("Your risk was not rewarded, you lost one health");
//
//		int yourSkill = gameStatus.getTotalResourcefulness();
//		int requiredSkill = event.getRsrcThresh();
//		
//		if (choice.equals("1")) {
//
//			if (yourSkill >= requiredSkill) {
//				finalOutcome = outcome1;
//			} else {
//				finalOutcome = outcome3;
//				gameStatus.setHealth(gameStatus.getHealth() - 1);
//			}
//		} else {
//
//			if (yourSkill > requiredSkill) {
//				finalOutcome = outcome2;
//				gameStatus.setHealth(gameStatus.getHealth() + 1);
//			} else {
//				finalOutcome = outcome3;
//				gameStatus.setHealth(gameStatus.getHealth() - 1);
//			}
//		}
//		sesh.setAttribute("gameStatus", gs);
//		mav.addObject("outcome", finalOutcome);
//		return mav;
//	}
//
//	@RequestMapping("/day3")
//	public ModelAndView day3() {
//		ModelAndView mav = new ModelAndView("day3");
//
//		// adding a random beast event to the model
//		BeastEvent be1 = eventsService.findRandomBeastEvent();
//		mav.addObject("event", be1);
//		sesh.setAttribute("event3", be1);
//		
//		return mav;
//	}
//
//	@PostMapping("/day3")
//	public ModelAndView day3results(String choice) {
//		ModelAndView mav = new ModelAndView("day3results");
//		Random rand = new Random();
//		BeastEvent event = (BeastEvent) sesh.getAttribute("event3");
//
//		GameStatus gameStatus = (GameStatus) sesh.getAttribute("gameStatus");
//		Player player1 = gameStatus.getMainPlayer();
//		Double money = player1.getMoney();
//		int moneyFound = rand.nextInt(15) + 10;
//		sesh.setAttribute("moneyFound", moneyFound);
//		player1.setMoney(money + moneyFound);
//
//		// creating outcomes
//		Outcome outcome1 = new Outcome();
//		Outcome outcome2 = new Outcome();
//		Outcome outcome3 = new Outcome();
//		Outcome outcome4 = new Outcome();
//		Outcome finalOutcome = new Outcome();
//
//		outcome1.setSurvived(true);
//		outcome2.setSurvived(true);
//		outcome3.setSurvived(true);
//		outcome4.setSurvived(false);
//
//		outcome1.setDescription("You fought courageously and won");
//		outcome2.setDescription("You lit them on fire you maniac");
//		outcome3.setDescription("You managed to run away, and thank your good luck");
//		outcome4.setDescription("You did not win, you lose 1 health");
//
//		int theirSkill = gameStatus.getTotalAttack();
//		int testSkill = event.getAttackThresh();
//		
//		if (choice.equals("1")) {
//
//			if (theirSkill >= testSkill) {
//				finalOutcome = outcome1;
//			} else {
//				finalOutcome = outcome4;
//				gameStatus.setHealth(gameStatus.getHealth() - 1);
//			}
//
//		} else if (choice.equals("2")) {
//			
//			if (theirSkill >= testSkill) {
//				finalOutcome = outcome2;
//			} else {
//				finalOutcome = outcome4;
//				gameStatus.setHealth(gameStatus.getHealth() - 1);
//			}
//
//		} else {
//			int random1 = rand.nextInt(100);
//
//			if (random1 > 50) {
//				finalOutcome = outcome3;
//			} else {
//				finalOutcome = outcome4;
//				gameStatus.setHealth(gameStatus.getHealth() - 1);
//			}
//		}
//
//		sesh.setAttribute("gameStatus", gameStatus);
//
//		mav.addObject("moneyFound", moneyFound);
//		mav.addObject("outcome", finalOutcome);
//		return mav;
//	}
}
