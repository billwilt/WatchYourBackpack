package co.grandcircus.WatchYourBackpack.DSModel;

public class NpsResponse {
	
	private Park park;

	public Park getPark() {
		return park;
	}

	public void setPark(Park park) {
		this.park = park;
	}

	@Override
	public String toString() {
		return "NpsResponse [park=" + park + "]";
	}

}
