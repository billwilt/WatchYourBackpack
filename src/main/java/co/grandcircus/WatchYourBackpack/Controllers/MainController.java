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
		NpsResponse test = apiServ.isleRoyale();
		System.out.println(test);
		return new ModelAndView("test", "test", test);
	}
	

	@RequestMapping ("/")
	public ModelAndView testWeather() {
		ModelAndView mav = new ModelAndView("index");
		
		NpsResponse isleRoyale = apiServ.isleRoyale();
		NpsResponse yellowstone = apiServ.yellowstone();
		NpsResponse grandCanyon = apiServ.grandCanyon();
		
		Currently isleRoyalWeather = DSApiServ.getWeather(isleRoyale.getData().get(0).getLatitude(), isleRoyale.getData().get(0).getLongitude());
		Currently yellowstoneWeather = DSApiServ.getWeather(yellowstone.getData().get(0).getLatitude(), yellowstone.getData().get(0).getLongitude());
		Currently grandCanyonWeather = DSApiServ.getWeather(grandCanyon.getData().get(0).getLatitude(), grandCanyon.getData().get(0).getLongitude());
		
		mav.addObject(isleRoyale);
		mav.addObject(yellowstone);
		mav.addObject(grandCanyon);
		
		mav.addObject(isleRoyalWeather);
		mav.addObject(yellowstoneWeather);
		mav.addObject(grandCanyonWeather);
		
		return mav;		
	}
	
	

}
