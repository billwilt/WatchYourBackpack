package co.grandcircus.WatchYourBackpack.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.WatchYourBackpack.DSApiService;
import co.grandcircus.WatchYourBackpack.NPSApiService;
import co.grandcircus.WatchYourBackpack.Daos.ItemDao;
import co.grandcircus.WatchYourBackpack.Daos.PlayerDao;
import co.grandcircus.WatchYourBackpack.Entities.GameStatus;
import co.grandcircus.WatchYourBackpack.Entities.Item;
import co.grandcircus.WatchYourBackpack.Entities.Player;
import co.grandcircus.WatchYourBackpack.Models.DSModel.Currently;
import co.grandcircus.WatchYourBackpack.Models.NPSModel.NpsResponse;
import co.grandcircus.WatchYourBackpack.Models.NPSModel.Park;

@Controller
public class MainController {

	@Autowired
	private NPSApiService apiServ;

	@Autowired
	private HttpSession sesh;

	@Autowired
	private DSApiService DSApiServ;

	@Autowired
	private PlayerDao playerDao;
	
	@Autowired
	private ItemDao itemDao;

	// This we will use later when we get the characters set up
	// @Autowired
	// private XDao xDao;

	@RequestMapping("/")
	public ModelAndView showHome() {
		ModelAndView mav = new ModelAndView("index");

		// grabbing the list of players from the database
		List<Player> players = playerDao.findAll();
		System.out.println(players);
//		for (Player player: players) {
//			List<String> names
//		}

		// adding the players to the model
		mav.addObject("players", players);

		// getting parks
		NpsResponse isleRoyale = apiServ.isleRoyale();
		NpsResponse yellowstone = apiServ.yellowstone();
		NpsResponse grandCanyon = apiServ.grandCanyon();

		// getting weather with the parks lng and lat
		Currently isleRoyaleWeather = DSApiServ.getWeather(isleRoyale.getData().get(0).getLatitude(),
				isleRoyale.getData().get(0).getLongitude());
		Currently yellowstoneWeather = DSApiServ.getWeather(yellowstone.getData().get(0).getLatitude(),
				yellowstone.getData().get(0).getLongitude());
		Currently grandCanyonWeather = DSApiServ.getWeather(grandCanyon.getData().get(0).getLatitude(),
				grandCanyon.getData().get(0).getLongitude());

		// testing the lng and lat
		System.out.println(isleRoyale.getData().get(0).getLatitude());
		System.out.println(isleRoyale.getData().get(0).getLongitude());

		// adding the parks to the model
		mav.addObject("isleRoyale", isleRoyale);
		mav.addObject("yellowstone", yellowstone);
		mav.addObject("grandCanyon", grandCanyon);

		// adding the weather for each park
		mav.addObject("isleRoyaleWeather", isleRoyaleWeather);
		mav.addObject("yellowstoneWeather", yellowstoneWeather);
		mav.addObject("grandCanyonWeather", grandCanyonWeather);

		return mav;
	}
	
	@RequestMapping("/newPlayer")
	public ModelAndView newPlayer() {
		return new ModelAndView("newPlayer");
	}
	
	@PostMapping("/newPlayer")
	public ModelAndView addNewPlayer(String name, String description, int type, Double money) {
		
		Player player = new Player();
		
		player.setName(name);
		player.setDescription(description);
		player.setMoney(money);
		
		if (type == 1) {
			player.setAttack(1);
			player.setFire(0);
			player.setResourcefulness(0);
		} else if (type == 2) {
			player.setAttack(0);
			player.setFire(1);
			player.setResourcefulness(0);			
		} else {
			player.setAttack(0);
			player.setFire(0);
			player.setResourcefulness(1);			
		}
		
		playerDao.save(player);
		
		return new ModelAndView("redirect:/");
	}

	@PostMapping("/start")
	public ModelAndView startGame(String parkCode, Long id) {
		ModelAndView mav = new ModelAndView("start");
		
		System.out.println(id);
		//apparently id is a reserved word, at first we had id and it didnt work :(
		Long id1 = id;
				//Long.parseLong(id);
		Player chosenPlayer = playerDao.findById(id1).orElse(null);
		
		System.out.println(chosenPlayer);
		
		if (chosenPlayer.equals(null)) {
			return new ModelAndView("redirect:/");
		}
		
		Park park = apiServ.findByParkCode(parkCode);
		Currently currentWeather = DSApiServ.getWeather(park.getLatitude(), park.getLongitude());
		String cost = (park.getEntranceFees().get(0).getCost());
		
		System.out.println(currentWeather);
		
		sesh.setAttribute("currentWeather", currentWeather);
		sesh.setAttribute("player1", chosenPlayer);
		sesh.setAttribute("park", park);
		
		//creating the available players for team list
		List<Player> allPlayers = new ArrayList<>();

		//only adding players that arent the chosen player
		for (Long i = 1L; i <= playerDao.count(); i ++) {
			if (i != id) {
				allPlayers.add(playerDao.getOne(i));
			}
		}
		
		List<Player> possibleTeam = allPlayers;
		List<Item> items = itemDao.findAll();
		
		//testing the list of items
		System.out.println(items);
		
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
		Integer itemsResourcefulness = item1.getResourcefulnessAdd() + item2.getResourcefulnessAdd() + item3.getResourcefulnessAdd();
		
		Player player2 = playerDao.findById(id).orElse(null);
		sesh.setAttribute("player2", player2);
		Double totalCost = 0.0;
		Player player1 = (Player) sesh.getAttribute("player1");
		
		//getting the total levels to add to game status
		Integer totalAttack = player1.getAttack() + player2.getAttack() + itemsAttack;
		Integer totalFire = player1.getFire() + player2.getFire() + itemsFire;
		Integer totalResourcefulness = player1.getResourcefulness() + player2.getResourcefulness() + itemsResourcefulness;
		
		//if they don't have enough money, set price to 0 for sleeping in the leaves
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
		
		Park park = (Park) sesh.getAttribute("park");
		
		//making the game status
		GameStatus gameStatus = new GameStatus();
		
		gameStatus.setMainPlayer((Player) sesh.getAttribute("player1"));
		gameStatus.setPartner(player2);
		gameStatus.setParkcode(park.getParkCode());
		gameStatus.setWeather((Currently) sesh.getAttribute("currentWeather"));
		gameStatus.setHealth(3);
		gameStatus.setTotalAttack(totalAttack);
		gameStatus.setTotalFire(totalFire);
		gameStatus.setTotalResourcefulness(totalResourcefulness);
		
		sesh.setAttribute("gameStatus", gameStatus);
		
		//STRETCH GOAL: add the price of items as well
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
	
	@RequestMapping("/play")
	public ModelAndView day1() {
		ModelAndView mav = new ModelAndView("day1");
		
		//setting the players wallet to the new wallet amount
		Player player1 = (Player) sesh.getAttribute("player1");
		Long player1Id = player1.getId();
		Player player1b = playerDao.findById(player1Id).orElse(null);
		player1.setMoney((double) sesh.getAttribute("walletAfter"));
		
		//adding the usual things to the model
		mav.addObject("player1", sesh.getAttribute("player1"));
		mav.addObject("player2", sesh.getAttribute("player2"));
		mav.addObject("gameStatus", sesh.getAttribute("gameStatus"));
		
		return mav;
	}
}
