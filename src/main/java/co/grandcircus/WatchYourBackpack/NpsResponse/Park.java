package co.grandcircus.WatchYourBackpack.NpsResponse;

import java.util.List;

public class Park {

	private List<Address> addresses;
	private Campsites campsites;
	private String latitude;
	private String longitude;
	private String url;
	private String name;

	

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
				+ longitude + ", url=" + url + ", name=" + name + "]";
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
