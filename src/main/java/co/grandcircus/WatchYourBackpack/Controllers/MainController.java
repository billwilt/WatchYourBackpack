package co.grandcircus.WatchYourBackpack.Controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.grandcircus.WatchYourBackpack.DSApiService;


@Controller
public class MainController {

	@Autowired
	private HttpSession sesh;

	@Autowired
	private DSApiService DSApiServ;

	//@Autowired
	//private XDao xDao;
	
	
}
