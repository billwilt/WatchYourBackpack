package co.grandcircus.WatchYourBackpack;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.WatchYourBackpack.DSModel.Currently;
import co.grandcircus.WatchYourBackpack.DSModel.DSResponse;


@Component
public class DSApiService {
	
	@Value("${DS-api}")
	private String apiKeyDS;
	

	private RestTemplate rt;

	{
		ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
			request.getHeaders().add(HttpHeaders.USER_AGENT, "ooeeee!");
			return execution.execute(request, body);
		};
		rt = new RestTemplateBuilder().additionalInterceptors(interceptor).build();
	}

	public Currently getWeather(String lat, String lon) {
		String url = "https://api.darksky.net/forecast/" + apiKeyDS + "/" + lat + "," + lon;
		DSResponse response = rt.getForObject(url, DSResponse.class);
		return response.getCurrently();
	}
	
}
