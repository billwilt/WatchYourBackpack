package co.grandcircus.WatchYourBackpack.Controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.WatchYourBackpack.ApiService;
import co.grandcircus.WatchYourBackpack.DSApiService;
import co.grandcircus.WatchYourBackpack.DSModel.NpsResponse.NpsResponse;


@Controller
public class MainController {
	
	@Autowired
	private ApiService apiServ;
	
	@Autowired
	private HttpSession sesh;

	@Autowired
	private DSApiService DSApiServ;

	//@Autowired
	//private XDao xDao;
	
	
	@RequestMapping("/npstest")
	public ModelAndView test() {
		
		NpsResponse test = apiServ.findNpsResponse();
		
		return new ModelAndView("test", "test", test);
	}
	
}
