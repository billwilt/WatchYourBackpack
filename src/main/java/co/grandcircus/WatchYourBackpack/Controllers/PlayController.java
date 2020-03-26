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
import co.grandcircus.WatchYourBackpack.GameService;
import co.grandcircus.WatchYourBackpack.ParksService;
import co.grandcircus.WatchYourBackpack.Daos.ItemDao;
import co.grandcircus.WatchYourBackpack.Daos.OutcomesDao;
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
	private PlayerDao playerDao;

	@Autowired
	private  OutcomesDao oDao;

	@Autowired
	private EventsService eventsService;
	
	@Autowired
	private GameService gameService;
	
	//////////////////////////////////// BEAST DAY //////////////////////////////////////////

	@RequestMapping("/genericBeastDay")
	public ModelAndView genericBeastDay() {
		ModelAndView mav = new ModelAndView("genericBeastDay");

		///////// generating beast event and adding to session /////////////////
		BeastEvent beastEvent = eventsService.findRandomBeastEvent();
		sesh.setAttribute("event", beastEvent);

		return mav;
	}

	@PostMapping("/genericBeastDay")
	public ModelAndView genericBeastDayPost(Integer choice) {
		ModelAndView mav = new ModelAndView("genericBeastDayPost");
		ModelAndView mavLoss = new ModelAndView("conclusion2");
		Random rand = new Random();

		GameStatus gameStatus = (GameStatus) sesh.getAttribute("gameStatus");
		BeastEvent event = (BeastEvent) sesh.getAttribute("event");
		
		Outcome finalOutcome = gameService.getFinalOutcome(gameStatus, event, choice);
		gameStatus.setHealth(gameStatus.getHealth() + finalOutcome.getHealthChange());
		mav.addObject("outcome", finalOutcome);
		
		if (gameStatus.getHealth() == 0) {
			return mavLoss;
		}		
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

	@PostMapping("/genericWeatherDayPost")
	public ModelAndView genericWeatherDayPost(Integer choice) {
		ModelAndView mav = new ModelAndView("genericWeatherDayPost");
		ModelAndView mavLoss = new ModelAndView("conclusion2");

		////////////////// getting objects from sessison //////////////////
		GameStatus gameStatus = (GameStatus) sesh.getAttribute("gameStatus");
		WeatherEvent event = (WeatherEvent) sesh.getAttribute("event");

		Outcome finalOutcome = gameService.getFinalOutcome(gameStatus, event, choice);
		gameStatus.setHealth(gameStatus.getHealth() + finalOutcome.getHealthChange());
		sesh.setAttribute("gameStatus", gameStatus);
		int dayCount = (int) sesh.getAttribute("dayCount");

		//////////// adding everything to model /////////////////////////
		mav.addObject("event", event);
		mav.addObject("outcome", finalOutcome);
		mav.addObject("dayCount", dayCount);

		if (gameStatus.getHealth() == 0) {
			return mavLoss;
		}
		
		return mav;
	}

	/////////////////////////// DAY CONTROLLER ////////////////////////////////////////////////////

	@PostMapping("/dayController")
	public ModelAndView dayController() {

		////////// getting day count and max day from session /////////////////////
		Integer dayCount = (Integer) sesh.getAttribute("dayCount");
		Random rand = new Random();
		
		//Integer maxDays = (Integer) sesh.getAttribute("maxDays");
		GameStatus gameStatus = (GameStatus) sesh.getAttribute("gameStatus");
		int totalLevel = gameStatus.getTotalAttack() + gameStatus.getTotalFire()
		+ gameStatus.getMainPlayer().getResourcefulness() + gameStatus.getPartner().getResourcefulness();
		Integer maxDays = (Integer) sesh.getAttribute("maxDays");
		int randInt = rand.nextInt(100);
		///////// increment day count ////////////////
		sesh.setAttribute("dayCount", dayCount + 1);
		
		///////// if it's the last day send them to conclusion ////////////////
		if (dayCount >= maxDays) {
			return new ModelAndView("redirect:/conclusion");
		}

		///////// RETURNING WHATEVER EVENT THEY DIDNT GET ////////////
		if (randInt > 25) {
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
		return new ModelAndView("redirect:/index");
	}
	
	@RequestMapping("/phoneAFriend")
	public ModelAndView phoneAFriend() {
		ModelAndView mav = new ModelAndView("phoneAFriend");
		GameStatus gameStatus = (GameStatus) sesh.getAttribute("gameStatus");
		boolean reception = gameStatus.getPark().getReception();
		
		if (reception) {
			mav.addObject("message", "You walk around and around until you finally find reception and get the chance to call for a friend, you don't lose any health after all.");
			gameStatus.setHealth(gameStatus.getHealth() + 1);
		} else {
			mav.addObject("message", "You try to find reception but you can't. Maybe you should have picked a park with cellphone reception.");
		}
		
		return mav;
	}
}
