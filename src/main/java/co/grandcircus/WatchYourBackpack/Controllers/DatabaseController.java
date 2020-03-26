package co.grandcircus.WatchYourBackpack.Controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.WatchYourBackpack.CampsiteApiService;
import co.grandcircus.WatchYourBackpack.DSApiService;
import co.grandcircus.WatchYourBackpack.EventsService;
import co.grandcircus.WatchYourBackpack.NPSApiService;
import co.grandcircus.WatchYourBackpack.ParksService;
import co.grandcircus.WatchYourBackpack.Daos.ParksDao;
import co.grandcircus.WatchYourBackpack.Daos.PlayerDao;
import co.grandcircus.WatchYourBackpack.Entities.BeastEvent;
import co.grandcircus.WatchYourBackpack.Entities.WeatherEvent;
import co.grandcircus.WatchYourBackpack.Models.DSModel.Currently;
import co.grandcircus.WatchYourBackpack.Models.NPSModel.NpsResponse;

@Controller
public class DatabaseController {
	
	@Autowired
	private NPSApiService NPSapiServ;
	@Autowired
	private ParksDao pDao;
	@Autowired
	private EventsService eventsService;
	@Autowired
	private CampsiteApiService cServ;
	@Autowired
	private PlayerDao playerDao;
	@Autowired
	private DSApiService DSApiServ;
	@Autowired
	private HttpSession sesh;
	@Autowired
	private ParksService pServ;
	

	
	@RequestMapping("/databaseTest")
	public ModelAndView test() {
		ModelAndView mav = new ModelAndView("redirect:/");
		
		//pServ.setReceptionForParksInDatabase(pDao.findAll());
		//pServ.setRvOptionForParksInDatabase(pDao.findAll());
		
		//getting parks into database
		//only called this method once to fill and then it's done!
		//pServ.fillDatabase();
		
		
		//mav.addObject("parkCodes",
		NPSapiServ.getParkCodesWithCampgrounds();
		
		//list possible entrance fee values
		List<Double> distinctFees = pDao.findDistinctEntranceFees();
		mav.addObject("fees", distinctFees);
		return mav;
	}

}
