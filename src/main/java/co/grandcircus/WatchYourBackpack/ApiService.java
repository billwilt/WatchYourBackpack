package co.grandcircus.WatchYourBackpack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.WatchYourBackpack.DSModel.NpsResponse.NpsResponse;

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

	public NpsResponse findNpsResponse() {

		String token = apiNPS;
		String url = "https://developer.nps.gov/api/v1/parks?stateCode=MI&api_key=" + token;

		NpsResponse response = null;
		response = rt.getForObject(url, NpsResponse.class);
		
		return response;

	}

}


















