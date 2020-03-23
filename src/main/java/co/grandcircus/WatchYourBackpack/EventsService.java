package co.grandcircus.WatchYourBackpack;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.grandcircus.WatchYourBackpack.Daos.BeastEventDao;
import co.grandcircus.WatchYourBackpack.Daos.WeatherEventDao;
import co.grandcircus.WatchYourBackpack.Entities.BeastEvent;
import co.grandcircus.WatchYourBackpack.Entities.WeatherEvent;

@Component
public class EventsService {
	
	@Autowired
	private BeastEventDao bedao;
	@Autowired
	private WeatherEventDao wedao;
	
	public WeatherEvent findWeatherEvent(String triggerIcon) {
		WeatherEvent we1 = new WeatherEvent();
		//System.out.println(wedao.findByTriggerIconsContaining(triggerIcon));
		try {
		we1 = (wedao.findByTriggerIconsContaining(triggerIcon));
		} catch (Exception e) {
			we1=wedao.findById(2L).orElse(null);
		}
		if (we1 == null) {
			we1 = wedao.findById(2L).orElse(null);
		}
		
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
