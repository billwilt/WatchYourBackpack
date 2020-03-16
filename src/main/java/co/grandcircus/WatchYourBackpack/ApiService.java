package co.grandcircus.WatchYourBackpack;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.WatchYourBackpack.NPSModel.NpsResponse;
import co.grandcircus.WatchYourBackpack.NPSModel.Park;

@Component
public class ApiService {
	
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

	public List<Park> getParks(){
				  //  https://api.nps.gov/api/v1/parks?limit=1&start=8&api_key=GKRdDKf9YMfEFYsJ5SaTZfTWbqzjNz5ylf6TBcw2
		String url = "https://api.nps.gov/api/v1/parks?limit=20&start=497&api_key=" + apiNPS;
		NpsResponse response = rt.getForObject(url, NpsResponse.class);
		return response.getData();
	}
}




