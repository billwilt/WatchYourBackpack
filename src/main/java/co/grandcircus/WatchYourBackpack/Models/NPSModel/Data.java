package co.grandcircus.WatchYourBackpack.Models.NPSModel;

import java.util.List;

public class Data {
	
	private List<Park> park;

	public List<Park> getPark() {
		return park;
	}

	public void setPark(List<Park> park) {
		this.park = park;
	}

	@Override
	public String toString() {
		return "Data [park=" + park + "]";
	}

}
