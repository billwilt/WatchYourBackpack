package co.grandcircus.WatchYourBackpack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.WatchYourBackpack.Models.CampgroundModel.Campground;
import co.grandcircus.WatchYourBackpack.Models.CampgroundModel.CampgroundResponse;
import co.grandcircus.WatchYourBackpack.Models.NPSModel.NpsResponse;
import co.grandcircus.WatchYourBackpack.Models.NPSModel.Park;

@Component
public class NPSApiService {
	
	@Value("${NPS-api}")
	private String apiNPS;

	private RestTemplate rt;

	// initialization block that runs when a new instance of the class is created
	// loaded before the constructor
	{
		ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
			request.getHeaders().add(HttpHeaders.USER_AGENT, "ooeeee!");
			return execution.execute(request, body);
		};
		rt = new RestTemplateBuilder().additionalInterceptors(interceptor).build();
	}
	
	public NpsResponse isleRoyale() {
		
		String token = apiNPS;
		String url1 = "https://developer.nps.gov/api/v1/parks?stateCode=MI&parkCode=isro&api_key=" + token;
		NpsResponse response = null;
		response = rt.getForObject(url1, NpsResponse.class);
		return response;
	}
	
	public NpsResponse yellowstone() {
		
		String token = apiNPS;
		NpsResponse response = null;
		String url2 = "https://developer.nps.gov/api/v1/parks?stateCode=WY&parkCode=yell&api_key=" + token;
		response = rt.getForObject(url2, NpsResponse.class);
		return response;
	}
	
	public NpsResponse grandCanyon() {
		
		String token = apiNPS;
		String url3 = "https://developer.nps.gov/api/v1/parks?stateCode=AZ&parkCode=grca&api_key=" + token;
		NpsResponse response = null;
		response = rt.getForObject(url3, NpsResponse.class);
		return response;
	}
	
	public Park findByParkCode(String parkCode) {
		String token = apiNPS;
		String url3 = "https://developer.nps.gov/api/v1/parks?parkCode=" + parkCode + "&api_key=" + token;
		NpsResponse response = null;
		response = rt.getForObject(url3, NpsResponse.class);
		Park responsePark = response.getData().get(0);
		return responsePark;
	}
//	public Park findByStateCode(String stateCode) {
//		String token = apiNPS;
//		String url3 = "https://developer.nps.gov/api/v1/parks?parkCode=" + parkCode + "&api_key=" + token;
//		NpsResponse response = null;
//		response = rt.getForObject(url3, NpsResponse.class);
//		Park responsePark = response.getData().get(0);
//		return responsePark;
//	}

	public List<Park> getParks(){
		List<Park> parks = new ArrayList<>();
		for (int i = 1; i < 498; i = i +20) {	  
			String url = "https://api.nps.gov/api/v1/parks?limit=20&start=" + i + "&api_key=" + apiNPS;
			NpsResponse response = rt.getForObject(url, NpsResponse.class);
			parks.addAll(response.getData());
		}
		return parks;
	}

	
	public Set<String> getParkCodesWithCampgrounds() {
		
		Set<String> parkCodes = new HashSet<>();
		for (int i = 1; i < 501; i=i+20) {
			String url = "https://api.nps.gov/api/v1/campgrounds?limit=20&start=" + i + "&api_key=" + apiNPS;
			CampgroundResponse response = rt.getForObject(url, CampgroundResponse.class);
			for (Campground campground: response.getData()) {
				parkCodes.add(campground.getParkCode());				
			}
		}
		//System.out.println(parkCodes);
		return parkCodes;
	}
}




