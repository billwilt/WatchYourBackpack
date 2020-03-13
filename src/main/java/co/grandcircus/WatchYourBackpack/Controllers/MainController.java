package co.grandcircus.WatchYourBackpack.Controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.WatchYourBackpack.ApiService;
import co.grandcircus.WatchYourBackpack.DSApiService;
import co.grandcircus.WatchYourBackpack.DSModel.Currently;
import co.grandcircus.WatchYourBackpack.NpsResponse.NpsResponse;



@Controller
public class MainController {	
	
	@Autowired
	private ApiService apiServ;
	
	@Autowired
	private HttpSession sesh;

	@Autowired
	private DSApiService DSApiServ;

	//This we will use later when we get the characters set up
	//@Autowired
	//private XDao xDao;
	

	
	@RequestMapping("/npstest")
	public ModelAndView test() {
		NpsResponse test = apiServ.isleRoyal();
		System.out.println(test);
		return new ModelAndView("test", "test", test);
	}
	

	@RequestMapping ("/")
	public ModelAndView testWeather() {
		ModelAndView mav = new ModelAndView("index");
		Currently weather = DSApiServ.getWeather("42.3601", "-71.0589");
		mav.addObject("weather", weather);
		return mav;
		 
		
	}

}
