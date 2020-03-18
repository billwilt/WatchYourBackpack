package co.grandcircus.WatchYourBackpack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.grandcircus.WatchYourBackpack.Daos.BeastEventDao;
import co.grandcircus.WatchYourBackpack.Daos.ParksDao;
import co.grandcircus.WatchYourBackpack.Daos.WeatherEventDao;
import co.grandcircus.WatchYourBackpack.Entities.BeastEvent;
import co.grandcircus.WatchYourBackpack.Entities.DBPark;
import co.grandcircus.WatchYourBackpack.Entities.WeatherEvent;
import co.grandcircus.WatchYourBackpack.Models.NPSModel.Park;

@Component
public class ParksService {

	@Autowired
	private ParksDao pDao;
	@Autowired
	private NPSApiService NPSapiServ;
	@Autowired
	private BeastEventDao bedao;
	@Autowired
	private WeatherEventDao wedao;

	public void fillDatabase() {
		// Creating a set of park codes from the campground endpoint
		// List<String> parkCodes = NPSapiServ.getParkCodesWithCampgrounds();

		// Load database of park names and entrance fees
		List<Park> parks = NPSapiServ.getParks();
		// used for undoing an accidental duplicate db add
//				for (Long i = 349L; i < 425; i++) {
//					pDao.deleteById(i);
//				}

		// List<String> parkCodes = Arrays.asList("fiis", "hale", "seki", "crla",
		// "ciro", "chic", "tapr", "grpo", "band", "cebr", "neri", "fomr", "gumo",
		// "dewa", "orpi", "dino", "badl", "cwdw", "blri", "ever", "cuis", "glca",
		// "redw", "klgo", "care", "laro", "havo", "natr", "samo", "pore", "indu",
		// "grsa", "labe", "wrst", "pinn", "prwi", "chir", "chis", "mora", "caco",
		// "ozar", "slbe", "moja", "nabr", "cato", "zion", "arch", "katm", "buff",
		// "crmo", "brca", "obed", "olym", "wica", "thro", "cany", "sagu", "rabr",
		// "shen", "whis", "deto", "buis", "hosp", "gari", "elmo", "drto", "guis",
		// "romo", "grte", "kefj", "nava", "jotr", "cuva", "yell", "apis", "grsm",
		// "orca", "grca", "lake", "cana", "maca", "deva", "piro", "colm", "hove",
		// "bica", "biso", "choh", "yose", "bicy", "glba", "bith", "isro", "dena",
		// "lavo", "cuga", "bibe", "whsa", "lamr", "amme", "noca", "gett", "pais",
		// "asis", "goga", "glac", "caha", "bisc", "gree", "gate", "niob", "ebla");

		Set<String> parkCodes = new HashSet<String>(Arrays.asList("fiis", "hale", "seki", "crla", "ciro", "chic",
				"tapr", "grpo", "band", "cebr", "neri", "fomr", "gumo", "dewa", "orpi", "dino", "badl", "cwdw", "blri",
				"ever", "cuis", "glca", "redw", "klgo", "care", "laro", "havo", "natr", "samo", "pore", "indu", "grsa",
				"labe", "wrst", "pinn", "prwi", "chir", "chis", "mora", "caco", "ozar", "slbe", "moja", "nabr", "cato",
				"zion", "arch", "katm", "buff", "crmo", "brca", "obed", "olym", "wica", "thro", "cany", "sagu", "rabr",
				"shen", "whis", "deto", "buis", "hosp", "gari", "elmo", "drto", "guis", "romo", "grte", "kefj", "nava",
				"jotr", "cuva", "yell", "apis", "grsm", "orca", "grca", "lake", "cana", "maca", "deva", "piro", "colm",
				"hove", "bica", "biso", "choh", "yose", "bicy", "glba", "bith", "isro", "dena", "lavo", "cuga", "bibe",
				"whsa", "lamr", "amme", "noca", "gett", "pais", "asis", "goga", "glac", "caha", "bisc", "gree", "gate",
				"niob", "ebla"));

		for (Park park : parks) {
			if (parkCodes.contains(park.getParkCode())) {
				
				
			
			DBPark dbPark = new DBPark();
			Double entranceFee = (!park.getEntranceFees().isEmpty()
					? Double.parseDouble(park.getEntranceFees().get(0).getCost())
					: null);
			dbPark.setEntranceFee(entranceFee);
			dbPark.setParkCode(park.getParkCode());
			dbPark.setStateCode(park.getStateCode().substring(0, 2));
			dbPark.setLongitude(park.getLongitude());
			dbPark.setLatitude(park.getLatitude());
			dbPark.setUrl(park.getUrl());
			dbPark.setImageUrl(park.getImages().get(0).getUrl());
			
			
			

			dbPark.setName(park.getName());
			pDao.save(dbPark);
			}
		}
	}
	
	public WeatherEvent findWeatherEvent(String triggerIcon) {
		WeatherEvent we1 = new WeatherEvent();
		
		we1 = wedao.findByTriggerIconsContaining(triggerIcon);
		
		return we1;
	}
	
	public BeastEvent findRandomBeastEvent() {
		BeastEvent be1 = new BeastEvent();
		Random r1 = new Random();

		List<BeastEvent> list1 = bedao.findAll();
		int index = r1.nextInt(list1.size() - 1);
		be1 = list1.get(index);
		
		return be1;
	}
	
}
