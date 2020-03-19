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
		
		//getting parks from database ordered by name
		mav.addObject("parksByName", pDao.findAllByOrderByName());
		//getting parks from database ordered by state code
		mav.addObject("parksByState", pDao.findAllByOrderByStateCode());
		//getting parks from database ordered by entrance fee
		mav.addObject("parksByFee", pDao.findAllByOrderByEntranceFee());
		
		return mav;
	}
	
	@RequestMapping("/test")
	public ModelAndView test() {
		ModelAndView mav = new ModelAndView("test");
		
		//pServ.setRvOptionForParksInDatabase(pDao.findAll());
		
		//getting parks into database
		//only called this method once to fill and then it's done!
		//pServ.fillDatabase();
		
		//mav.addObject("parkCodes",
		NPSapiServ.getParkCodesWithCampgrounds();
		
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
