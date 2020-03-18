package co.grandcircus.WatchYourBackpack.Models.NPSModel;

import java.util.List;

public class Park {

	private List<Address> addresses;
	private String latitude;
	private String longitude;
	private String url;
	private String name;
	private String parkCode;
	private List<Fee> entranceFees;
	private List<Image> images;
	private String states;

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
		return "Park [addresses=" + addresses + ", latitude=" + latitude + ", longitude=" + longitude + ", url=" + url
				+ ", name=" + name + ", parkCode=" + parkCode + ", entranceFees=" + entranceFees + ", images=" + images
				+ ", states=" + states + "]";
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

	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}

	public List<Fee> getEntranceFees() {
		return entranceFees;
	}

	public void setEntranceFees(List<Fee> entranceFees) {
		this.entranceFees = entranceFees;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}

}
