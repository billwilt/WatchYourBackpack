package co.grandcircus.WatchYourBackpack.Controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.WatchYourBackpack.DSApiService;
import co.grandcircus.WatchYourBackpack.NPSApiService;
import co.grandcircus.WatchYourBackpack.Daos.PlayerDao;
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
		
		mav.addObject("currentWeather", currentWeather);
		mav.addObject("park", park);
		mav.addObject("chosenPlayer", chosenPlayer);
		mav.addObject("cost", cost);
		
		return mav;
	}
}
