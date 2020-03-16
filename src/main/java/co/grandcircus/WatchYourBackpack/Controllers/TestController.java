package co.grandcircus.WatchYourBackpack.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.WatchYourBackpack.ApiService;
import co.grandcircus.WatchYourBackpack.DSApiService;
import co.grandcircus.WatchYourBackpack.ParksService;
import co.grandcircus.WatchYourBackpack.Daos.ParksDao;
import co.grandcircus.WatchYourBackpack.NPSModel.NpsResponse;

@Controller
public class TestController {
	
	@Autowired
	private DSApiService DSApiServ;
	@Autowired
	private ApiService NPSapiServ;
	@Autowired
	private ParksDao pDao;
	@Autowired
	private ParksService pServ;
	
	@RequestMapping("/test")
	public ModelAndView test() {
		ModelAndView mav = new ModelAndView("test");
		
		//fill database. Only wanted to do this once to grab info, so commented out
		//pServ.fillDatabase();
		
		//list possible entrance fee values
		List<Double> distinctFees = pDao.findDistinctEntranceFees();
		mav.addObject("fees", distinctFees);
		
		//testing NPS api
		NpsResponse test = NPSapiServ.isleRoyale();
		mav.addObject("test", test);
		return mav;
	}
}
