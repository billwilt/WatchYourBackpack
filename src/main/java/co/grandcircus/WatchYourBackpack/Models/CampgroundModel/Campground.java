package co.grandcircus.WatchYourBackpack.Models.CampgroundModel;

import java.util.List;

public class Campground {
		
	private String name;
	private String latitude;
	private String longitude;
	private String parkCode;
	private String id;
	
	//this object is plural because actually the object itself just contains a binch of fields detailing numbers of campsites
	private Campsites campsites;
	private List<Image> images;
	private Amenity amenities;
	
	
	public Campsites getCampsites() {
		return campsites;
	}
	public void setCampsites(Campsites campsites) {
		this.campsites = campsites;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public Amenity getAmenities() {
		return amenities;
	}
	public void setAmenities(Amenity amenities) {
		this.amenities = amenities;
	}
	@Override
	public String toString() {
		return "Campground [name=" + name + ", latitude=" + latitude + ", longitude=" + longitude + ", parkCode="
				+ parkCode + ", id=" + id + ", campsites=" + campsites + ", images=" + images + ", amenities="
				+ amenities + "]";
	}
	
	
}
