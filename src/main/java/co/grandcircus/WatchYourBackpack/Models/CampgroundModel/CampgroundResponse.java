package co.grandcircus.WatchYourBackpack.Models.CampgroundModel;

import java.util.List;

public class CampgroundResponse {

	private List<Campground> data;

	public List<Campground> getData() {
		return data;
	}

	public void setData(List<Campground> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "CampgroundResponse [data=" + data + "]";
	}
	
	
}
