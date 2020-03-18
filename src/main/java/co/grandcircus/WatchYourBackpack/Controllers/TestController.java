package co.grandcircus.WatchYourBackpack.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.WatchYourBackpack.CampsiteApiService;
import co.grandcircus.WatchYourBackpack.DSApiService;
import co.grandcircus.WatchYourBackpack.NPSApiService;
import co.grandcircus.WatchYourBackpack.ParksService;
import co.grandcircus.WatchYourBackpack.Daos.ParksDao;
import co.grandcircus.WatchYourBackpack.Daos.PlayerDao;
import co.grandcircus.WatchYourBackpack.Entities.DBPark;
import co.grandcircus.WatchYourBackpack.Entities.Player;
import co.grandcircus.WatchYourBackpack.Models.NPSModel.NpsResponse;

@Controller
public class TestController {
	
	@Autowired
	private NPSApiService NPSapiServ;
	@Autowired
	private ParksDao pDao;
	@Autowired
	private ParksService pServ;
	@Autowired
	private CampsiteApiService cServ;
	@Autowired
	private PlayerDao playerDao;
	@Autowired
	private DSApiService DSApiServ;
	
	@RequestMapping("/")
	public ModelAndView showHome() {
		ModelAndView mav = new ModelAndView("index");

		// grabbing the list of players from the database
		List<Player> players = playerDao.findAll();
		mav.addObject("players", players);

		//getting parks into database
		//only called this method once to fill and then it's done!
		//pServ.fillDatabase();
		
		List<String> stateCodes = pDao.findDistinctStateCodes();
		mav.addObject("stateCodes", stateCodes);
		System.out.println(stateCodes);
		
		//getting parks from database ordered by name
		List<DBPark> parksByName = pDao.findAllByOrderByName();
		mav.addObject("parksByName", parksByName);
		//getting parks from database ordered by state code
		List<DBPark> parksByState = pDao.findAllByOrderByStateCode();
		mav.addObject("parksByState", parksByState);
		
		//getting parks by stateCode
		List<DBPark> parksInState = pDao.findByStateCode("MI");
		mav.addObject("parksInState", parksInState);
		
//		NpsResponse isleRoyale = NPSapiServ.isleRoyale();
//		NpsResponse yellowstone = NPSapiServ.yellowstone();
//		NpsResponse grandCanyon = NPSapiServ.grandCanyon();

//		// getting weather with the parks lng and lat
//		Currently isleRoyaleWeather = DSApiServ.getWeather(isleRoyale.getData().get(0).getLatitude(),
//				isleRoyale.getData().get(0).getLongitude());
//		Currently yellowstoneWeather = DSApiServ.getWeather(yellowstone.getData().get(0).getLatitude(),
//				yellowstone.getData().get(0).getLongitude());
//		Currently grandCanyonWeather = DSApiServ.getWeather(grandCanyon.getData().get(0).getLatitude(),
//				grandCanyon.getData().get(0).getLongitude());
//
//		// testing the lng and lat
//		System.out.println(isleRoyale.getData().get(0).getLatitude());
//		System.out.println(isleRoyale.getData().get(0).getLongitude());
//
//		// adding the parks to the model
//		mav.addObject("isleRoyale", isleRoyale);
//		mav.addObject("yellowstone", yellowstone);
//		mav.addObject("grandCanyon", grandCanyon);
//
//		// adding the weather for each park
//		mav.addObject("isleRoyaleWeather", isleRoyaleWeather);
//		mav.addObject("yellowstoneWeather", yellowstoneWeather);
//		mav.addObject("grandCanyonWeather", grandCanyonWeather);

		return mav;
	}
	
	@RequestMapping("/test")
	public ModelAndView test() {
		ModelAndView mav = new ModelAndView("test");
		
		//mav.addObject("parkCodes",
		NPSapiServ.getParkCodesWithCampgrounds();
		
		//fill database. Only wanted to do this once to grab info, so commented out
		//pServ.fillDatabase();
		
		//list possible entrance fee values
		List<Double> distinctFees = pDao.findDistinctEntranceFees();
		mav.addObject("fees", distinctFees);
		
		//testing NPS api
		NpsResponse test = NPSapiServ.isleRoyale();
		mav.addObject("test", test);
		
		//testing Campground endpoint
		mav.addObject("campgrounds", cServ.getCampgrounds(20));
		return mav;
	}
}
