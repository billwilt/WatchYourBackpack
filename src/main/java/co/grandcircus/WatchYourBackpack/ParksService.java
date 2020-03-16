package co.grandcircus.WatchYourBackpack;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.grandcircus.WatchYourBackpack.Daos.ParksDao;
import co.grandcircus.WatchYourBackpack.Entities.DBPark;
import co.grandcircus.WatchYourBackpack.Models.NPSModel.Park;

@Component
public class ParksService {
	
	@Autowired
	private ParksDao pDao;
	@Autowired
	private NPSApiService NPSapiServ;

	public void fillDatabase() {
		//Load database of park names and entrance fees
				List<Park> parks = NPSapiServ.getParks();
				//used for undoing an accidental duplicate db add
//				for (Long i = 349L; i < 425; i++) {
//					pDao.deleteById(i);
//				}
				for (Park park : parks) {
					DBPark dbPark = new DBPark();
					Double entranceFee = (!park.getEntranceFees().isEmpty() ? Double.parseDouble(park.getEntranceFees().get(0).getCost()) : null);
					dbPark.setEntranceFee(entranceFee);
					//the above ternary operator should work the same as below if all goes according 
//					if (!park.getEntranceFees().isEmpty()) {
//						dbPark.setEntranceFee(Double.parseDouble(park.getEntranceFees().get(0).getCost()));
//					}else {
//						dbPark.setEntranceFee(null);
//					}
					dbPark.setName(park.getName());
					pDao.save(dbPark);
				}
	}
}
