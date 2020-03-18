package co.grandcircus.WatchYourBackpack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.grandcircus.WatchYourBackpack.Daos.ParksDao;
import co.grandcircus.WatchYourBackpack.Daos.PlayerDao;
import co.grandcircus.WatchYourBackpack.Entities.DBPark;
import co.grandcircus.WatchYourBackpack.Entities.Player;
import co.grandcircus.WatchYourBackpack.Models.NPSModel.Park;

@Component
public class ParksService {

	@Autowired
	private ParksDao pDao;
	@Autowired
	private NPSApiService NPSapiServ;

	public void fillDatabase() {
		// Creating a set of park codes from the campground endpoint
		// List<String> parkCodes = NPSapiServ.getParkCodesWithCampgrounds();

		// Load database of park names and entrance fees
		List<Park> parks = NPSapiServ.getParks();
		//System.out.println(parks);

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
			System.out.println(park);
			if (parkCodes.contains(park.getParkCode())) {
				
			DBPark dbPark = new DBPark();
			
			//.isEmpty works for the following because entranceFees is a List
			Double entranceFee = (!park.getEntranceFees().isEmpty()
					? Double.parseDouble(park.getEntranceFees().get(0).getCost())
					: null);			
			dbPark.setEntranceFee(entranceFee);
			
			//.isEmpty does not work for the following because parkCode is a string. (it's not a null check)
			//we're just going to assume it's not null
			dbPark.setParkCode(park.getParkCode());
			
			//this one needs a null check because parks in DC don't have a state code XD		
			String stateCode = (park.getStates() != null ? park.getStates().substring(0, 2) : null);
			dbPark.setStateCode(stateCode);
			
			//String lon = (!park.getLongitude().isEmpty() ? park.getLongitude() : null);			
			dbPark.setLongitude(park.getLongitude());			
			//String lat = (!park.getLatitude().isEmpty() ? park.getLatitude() : null);		
			dbPark.setLatitude(park.getLatitude());			
			//String url = (!park.getUrl().isEmpty() ? park.getLongitude() : null);		
			dbPark.setUrl(park.getUrl());			
			//String iUrl = (!park.getImages().get(0).getUrl().isEmpty() ? park.getImages().get(0).getUrl() : null);		
			dbPark.setImageUrl(park.getImages().get(0).getUrl());			
			//String name = (!park.getName().isEmpty() ? park.getName() : null);		
			dbPark.setName(park.getName());		
			pDao.save(dbPark);
			}
		}
	}
}
