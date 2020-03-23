package co.grandcircus.WatchYourBackpack;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.WatchYourBackpack.Models.CampgroundModel.Campground;
import co.grandcircus.WatchYourBackpack.Models.CampgroundModel.CampgroundResponse;

@Component
public class CampsiteApiService {
	
	@Value("${NPS-api}")
	private String apiKeyNPS;
	
	private RestTemplate rt;

	{
		ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
			request.getHeaders().add(HttpHeaders.USER_AGENT, "ooeeee!");
			return execution.execute(request, body);
		};
		rt = new RestTemplateBuilder().additionalInterceptors(interceptor).build();
	}
	
	public List<Campground> getCampgrounds(int limit){
		String url = "https://api.nps.gov/api/v1/campgrounds?limit=" + limit + "&apikey=" + apiKeyNPS;
		return rt.getForObject(url, CampgroundResponse.class).getData();
	}
}
