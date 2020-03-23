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
import co.grandcircus.WatchYourBackpack.Daos.ParksDao;
import co.grandcircus.WatchYourBackpack.Daos.PlayerDao;
import co.grandcircus.WatchYourBackpack.Entities.BeastEvent;
import co.grandcircus.WatchYourBackpack.Entities.WeatherEvent;
import co.grandcircus.WatchYourBackpack.Models.DSModel.Currently;
import co.grandcircus.WatchYourBackpack.Models.NPSModel.NpsResponse;

@Controller
public class TestController {
	
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
	@RequestMapping("/testWeather")
	public ModelAndView testEvent() {
		ModelAndView mav = new ModelAndView("test1");
		///////////////////////////////////////////////////////////////////////////////
		
		Currently current = (Currently) sesh.getAttribute("currentWeather");

		String triggerIcon = current.getIcon();
		//System.out.println(triggerIcon);
		WeatherEvent we1 = eventsService.findWeatherEvent(triggerIcon);
		//System.out.println(pServ.findWeatherEvent(" rain,"));

		mav.addObject("Event2", we1);
		///////////////////////////////////////////////////////////////////////////////
		BeastEvent be1 = new BeastEvent();
		be1 = eventsService.findRandomBeastEvent();
		mav.addObject("triggerIcon", triggerIcon);
		mav.addObject("Event3", be1);
		mav.addObject("summary", current.getSummary());
		return mav;
	}
}
