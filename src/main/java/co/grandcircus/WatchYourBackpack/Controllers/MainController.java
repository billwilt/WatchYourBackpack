package co.grandcircus.WatchYourBackpack.Controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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

	// This we will use later when we get the characters set up
	// @Autowired
	// private XDao xDao;

	@RequestMapping("/npstest")
	public ModelAndView test() {
		NpsResponse test = apiServ.isleRoyale();
		System.out.println(test);
		return new ModelAndView("test", "test", test);
	}

	@RequestMapping("/")
	public ModelAndView testWeather() {
		ModelAndView mav = new ModelAndView("index");

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
	public ModelAndView startGame(String parkCode, String user) {
		ModelAndView mav = new ModelAndView("start");
		
		mav.addObject("park", apiServ.findByParkCode(parkCode));
		mav.addObject("user", user);
		
		return mav;
	}

}
