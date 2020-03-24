package co.grandcircus.WatchYourBackpack.Controllers;

import java.util.ArrayList;
import java.util.List;

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
	
	@PostMapping("/setPlayer")
	public ModelAndView setPlayer(Long id) {
		ModelAndView mav = new ModelAndView("redirect:/");
		
		//checking if gamestatus is already added to session, then adding gameStatus object
		GameStatus gameStatus = (GameStatus) sesh.getAttribute("gameStatus");
		if (sesh.getAttribute("gameStatus") == null) {
			gameStatus = new GameStatus();
		sesh.setAttribute("gameStatus", gameStatus);
		}
		Player mainPlayer = playerDao.findById(id).orElse(null);
		gameStatus.setMainPlayer(mainPlayer);
		return mav;
	}

	@RequestMapping("/newPlayer")
	public ModelAndView newPlayer() {
		return new ModelAndView("newPlayer");
	}

	@PostMapping("/newPlayer")
	public ModelAndView addNewPlayer(String name, String description, Integer type) {

		Player newPlayer = playerService.createPlayer(name, description, type);
		GameStatus gameStatus = new GameStatus();
		gameStatus.setMainPlayer(newPlayer);
		sesh.setAttribute("gameStatus", gameStatus);
		//rd.addAttribute("newPlayer", newPlayer);
		return new ModelAndView("redirect:/");
	}

	@PostMapping("/start")
	public ModelAndView startGame(String parkCodeName, String parkCodeState, String parkCodeFee, RedirectAttributes rd) {
		ModelAndView mav = new ModelAndView("start");
		ModelAndView mavRd = new ModelAndView("redirect:/");
		
		//checking if gamestatus is already added to session, then adding gameStatus object
		//System.out.println(sesh.getAttribute("gameStatus"));
		GameStatus gameStatus;
		if (sesh.getAttribute("gameStatus") == null) {
			gameStatus = new GameStatus();
			sesh.setAttribute("gameStatus", gameStatus);
		}else {
			gameStatus = (GameStatus) sesh.getAttribute("gameStatus");
		}
		//Adding Player to GameStatus
		
		if (gameStatus.getMainPlayer() == null) {
			rd.addFlashAttribute("noPlayerMessage", "No player was selected. Please choose or create a player first!");
			return mavRd;
		}
		
		//Adding Park to GameStatus
		String parkCode = parksService.determineParkCode(parkCodeName, parkCodeState, parkCodeFee);
		if (parkCode.equals("none")) {	
			rd.addFlashAttribute("parkMessage", "No park chosen. Please choose a park!");
			return mavRd;
		}else if (parkCode.equals("many")) {
			rd.addFlashAttribute("parkMessage", "Okay, it's virtual, but it's not THAT virtual. You can't be in two places at once! Please choose just one park!");
			return mavRd;
		}		
		DBPark park = pDao.findByParkCodeContaining(parkCode);
		gameStatus.setPark(park);
		
		//Adding Weather to GameStatus
		gameStatus.setWeather(DSApiServ.getWeather(park.getLatitude(), park.getLongitude()));
		
		//Adjusting Player's Wallet
		gameStatus.getMainPlayer().setMoney(gameStatus.getMainPlayer().getMoney()-park.getEntranceFee());

		// creating the available players for team list--only adding players that arent the chosen player
		List<Player> availableTeam = new ArrayList<>();
		System.out.println(gameStatus.getMainPlayer().getId());
		for (Long i = 1L; i <= playerDao.count(); i++) {
			
			if (i != gameStatus.getMainPlayer().getId()) {
				availableTeam.add(playerDao.getOne(i));
			}
		}
		
		mav.addObject("availableTeam", availableTeam);
		
		//adding list of items to mav
		List<Item> items = itemDao.findAll();
		mav.addObject("items", items);

		return mav;
	}

	@PostMapping("/confirmSettings")
	public ModelAndView confirmPage(double price, Long id, Long item1Id, Long item2Id, Long item3Id) {
		ModelAndView mav = new ModelAndView("confirmPage");
		GameStatus gameStatus = (GameStatus) sesh.getAttribute("gameStatus");
		
		Item item1 = itemDao.findById(item1Id).orElse(null);
		Item item2 = itemDao.findById(item2Id).orElse(null);
		Item item3 = itemDao.findById(item3Id).orElse(null);

		Integer itemsAttack = item1.getAttackAdd() + item2.getAttackAdd() + item3.getAttackAdd();
		Integer itemsFire = item1.getFireAdd() + item2.getFireAdd() + item3.getFireAdd();
		Integer itemsResourcefulness = item1.getResourcefulnessAdd() + item2.getResourcefulnessAdd()
				+ item3.getResourcefulnessAdd();
		Double totalCost = 0.0;//for future use if items cost money later
		
		Player player2 = playerDao.findById(id).orElse(null);
		gameStatus.setPartner(player2);
	
		Player player1 = (Player) gameStatus.getMainPlayer();

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

		//DBPark park = (DBPark) sesh.getAttribute("park");

		// making the game status
		//GameStatus gameStatus = new GameStatus();

		//gameStatus.setMainPlayer((Player) sesh.getAttribute("player1"));
		gameStatus.setPartner(player2);
		//gameStatus.setParkcode(park.getParkCode());
		//gameStatus.setWeather((Currently) sesh.getAttribute("currentWeather"));
		//gameStatus.setHealth(2);
		gameStatus.setTotalAttack(totalAttack);
		gameStatus.setTotalFire(totalFire);
		gameStatus.setTotalResourcefulness(totalResourcefulness);

		//sesh.setAttribute("gameStatus", gameStatus);

		// STRETCH GOAL: add the price of items as well
		totalCost += price;
		sesh.setAttribute("totalCost", totalCost);
		sesh.setAttribute("walletAfter", (player1.getMoney() - totalCost));

		return mav;
	}
}
