package co.grandcircus.WatchYourBackpack.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.grandcircus.WatchYourBackpack.DSApiService;
import co.grandcircus.WatchYourBackpack.ParksService;
import co.grandcircus.WatchYourBackpack.PlayerService;
import co.grandcircus.WatchYourBackpack.Daos.ItemDao;
import co.grandcircus.WatchYourBackpack.Daos.ParksDao;
import co.grandcircus.WatchYourBackpack.Daos.PlayerDao;
import co.grandcircus.WatchYourBackpack.Entities.DBPark;
import co.grandcircus.WatchYourBackpack.Entities.GameStatus;
import co.grandcircus.WatchYourBackpack.Entities.Item;
import co.grandcircus.WatchYourBackpack.Entities.Player;
import co.grandcircus.WatchYourBackpack.Models.DSModel.Currently;

@Controller
public class SetupController {

	@Autowired
	private HttpSession sesh;

	@Autowired
	private DSApiService DSApiServ;

	@Autowired
	private PlayerService playerService;

	@Autowired
	private PlayerDao playerDao;

	@Autowired
	private ItemDao itemDao;

	@Autowired
	private ParksDao pDao;
	
	@Autowired
	private ParksService parksService;

//	@RequestMapping("/addEvent")
//	public ModelAndView addEvent() {
//		WeatherEvent we1 = new WeatherEvent();
//
//		String name = "windy";
//		String description = "The wind is really picking up, hopefully notihing blows away.";
//		int rsrcThresh = 2;
//		String triggerIcons = "WIND";
//
//		we1.setDescription(description);
//		we1.setName(name);
//		we1.setTriggerIcons(triggerIcons);
//		we1.setRsrcThresh(rsrcThresh);
//		we1.setOutcomes(null);
//
//		WEDao.save(we1);
//		return new ModelAndView("redirect:/");
//	}

	@RequestMapping("/")
	public ModelAndView showHome(RedirectAttributes rd) {
		ModelAndView mav = new ModelAndView("index");

		// grabbing the list of players from the database
		mav.addObject("players", playerDao.findAll());
		
		//getting parks from database ordered by different fields
		mav.addObject("parksByName", pDao.findAllByOrderByName());
		mav.addObject("parksByState", pDao.findAllByOrderByStateCode());
		mav.addObject("parksByFee", pDao.findAllByOrderByEntranceFee());
		
		return mav;
	}

	@RequestMapping("/newPlayer")
	public ModelAndView newPlayer() {
		return new ModelAndView("newPlayer");
	}

	@PostMapping("/newPlayer")
	public ModelAndView addNewPlayer(String name, String description, Integer type) {
		
		playerService.createPlayer(name, description, type);

		return new ModelAndView("redirect:/");
	}

	@PostMapping("/start")
	public ModelAndView startGame(String parkCodeName, String parkCodeState, String parkCodeFee, Long id, RedirectAttributes rd) {
		ModelAndView mav = new ModelAndView("start");

		Player chosenPlayer = playerDao.findById(id).orElse(null);
		if (chosenPlayer.equals(null)) {
			return new ModelAndView("redirect:/", "noPlayerMessage", "No player was selected. Please choose or create a player first!");
		}
		
		String parkCode = parksService.determineParkCode(parkCodeName, parkCodeState, parkCodeFee);
		if (parkCode.equals("none")) {	
			rd.addFlashAttribute("parkMessage", "No park chosen. Please choose a park!");
			return new ModelAndView("redirect:/");
		}else if (parkCode.equals("many")) {
			return new ModelAndView("redirect:/", "parkMessage", "Okay, it's virtual, but it's not THAT virtual. You can't be in two places at once! Please choose just one park!");
		}
		
		DBPark park = pDao.findByParkCodeContaining(parkCode);	
		Currently currentWeather = DSApiServ.getWeather(park.getLatitude(), park.getLongitude());
		Double cost = (park.getEntranceFee());

		sesh.setAttribute("currentWeather", currentWeather);
		sesh.setAttribute("player1", chosenPlayer);
		sesh.setAttribute("park", park);

		// creating the available players for team list
		List<Player> allPlayers = new ArrayList<>();

		// only adding players that arent the chosen player
		for (Long i = 1L; i <= playerDao.count(); i++) {
			if (i != id) {
				allPlayers.add(playerDao.getOne(i));
			}
		}

		List<Player> possibleTeam = allPlayers;
		List<Item> items = itemDao.findAll();

		// testing the list of items
		//System.out.println(items);

		mav.addObject("items", items);
		mav.addObject("availableTeam", possibleTeam);
		mav.addObject("currentWeather", currentWeather);
		mav.addObject("park", park);
		mav.addObject("chosenPlayer", chosenPlayer);
		mav.addObject("cost", cost);

		return mav;
	}

	@PostMapping("/confirmSettings")
	public ModelAndView confirmPage(double price, Long id, Long item1Id, Long item2Id, Long item3Id) {
		ModelAndView mav = new ModelAndView("confirmPage");

		Item item1 = itemDao.findById(item1Id).orElse(null);
		Item item2 = itemDao.findById(item2Id).orElse(null);
		Item item3 = itemDao.findById(item3Id).orElse(null);

		Integer itemsAttack = item1.getAttackAdd() + item2.getAttackAdd() + item3.getAttackAdd();
		Integer itemsFire = item1.getFireAdd() + item2.getFireAdd() + item3.getFireAdd();
		Integer itemsResourcefulness = item1.getResourcefulnessAdd() + item2.getResourcefulnessAdd()
				+ item3.getResourcefulnessAdd();

		Player player2 = playerDao.findById(id).orElse(null);
		sesh.setAttribute("player2", player2);
		Double totalCost = 0.0;
		Player player1 = (Player) sesh.getAttribute("player1");

		// getting the total levels to add to game status
		Integer totalAttack = player1.getAttack() + player2.getAttack() + itemsAttack;
		Integer totalFire = player1.getFire() + player2.getFire() + itemsFire;
		Integer totalResourcefulness = player1.getResourcefulness() + player2.getResourcefulness()
				+ itemsResourcefulness;

		// if they don't have enough money, set price to 0 for sleeping in the leaves
		if (player1.getMoney() < price || price == 0) {
			price = 0;
			mav.addObject("sleeping", "in the leaves");
		} else if (price == 10) {
			mav.addObject("sleeping", "in a nice tent");
			totalResourcefulness += 1;
		} else {
			mav.addObject("sleeping", "in a cabin");
			totalResourcefulness += 2;
		}

		DBPark park = (DBPark) sesh.getAttribute("park");

		// making the game status
		GameStatus gameStatus = new GameStatus();

		gameStatus.setMainPlayer((Player) sesh.getAttribute("player1"));
		gameStatus.setPartner(player2);
		gameStatus.setParkcode(park.getParkCode());
		gameStatus.setWeather((Currently) sesh.getAttribute("currentWeather"));
		gameStatus.setHealth(2);
		gameStatus.setTotalAttack(totalAttack);
		gameStatus.setTotalFire(totalFire);
		gameStatus.setTotalResourcefulness(totalResourcefulness);

		sesh.setAttribute("gameStatus", gameStatus);

		// STRETCH GOAL: add the price of items as well
		totalCost += price;
		sesh.setAttribute("totalCost", totalCost);
		sesh.setAttribute("walletAfter", (player1.getMoney() - totalCost));

		mav.addObject("walletAfter", (player1.getMoney() - totalCost));
		mav.addObject("totalCost", totalCost);
		mav.addObject("player1", player1);
		mav.addObject("player2", player2);
		mav.addObject("park", sesh.getAttribute("park"));
		mav.addObject("currentWeather", sesh.getAttribute("currentWeather"));

		return mav;
	}
}
