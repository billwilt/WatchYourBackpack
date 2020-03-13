package co.grandcircus.WatchYourBackpack.DSModel.NpsResponse;

public class Park {
	
	private Addresses addresses;
	private Campsites campsites;
	private String latitude;
	private String longitude;
	
	public Addresses getAddresses() {
		return addresses;
	}
	
	public void setAddresses(Addresses addresses) {
		this.addresses = addresses;
	}
	
	public Campsites getCampsites() {
		return campsites;
	}
	
	public void setCampsites(Campsites campsites) {
		this.campsites = campsites;
	}
	
	public String getLatitude() {
		return latitude;
	}
	
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	public String getLongitude() {
		return longitude;
	}
	
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	@Override
	public String toString() {
		return "Park [addresses=" + addresses + ", campsites=" + campsites + ", latitude=" + latitude + ", longitude="
				+ longitude + "]";
	}
	
}
