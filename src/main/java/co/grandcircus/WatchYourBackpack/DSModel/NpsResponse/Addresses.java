package co.grandcircus.WatchYourBackpack.DSModel.NpsResponse;

public class Addresses {
	
	private String city;
	private String stateCode;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	@Override
	public String toString() {
		return "Addresses [city=" + city + ", stateCode=" + stateCode + "]";
	}
	
}
