package co.grandcircus.WatchYourBackpack.DSModel.NpsResponse;

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
