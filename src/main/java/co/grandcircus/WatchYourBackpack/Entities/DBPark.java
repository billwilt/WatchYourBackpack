package co.grandcircus.WatchYourBackpack.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DBPark {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 	
	
	private String name;
	private String parkCode;
	private Double entranceFee; 
	private String stateCode;
	private String longitude;
	private String latitude;
	private String url;
	private String imageUrl;
	private String imageAltText;
	private Boolean rvOption;
		
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getImageAltText() {
		return imageAltText;
	}
	public void setImageAltText(String imageAltText) {
		this.imageAltText = imageAltText;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getEntranceFee() {
		return entranceFee;
	}
	public void setEntranceFee(Double entranceFee) {
		this.entranceFee = entranceFee;
	}

	@Override
	public String toString() {
		return "DBPark [id=" + id + ", name=" + name + ", entranceFee=" + entranceFee + ", stateCode=" + stateCode
				+ ", parkCode=" + parkCode + ", longitude=" + longitude + ", latitude=" + latitude + ", url=" + url
				+ ", imageUrl=" + imageUrl + ", imageAltText=" + imageAltText + "]";
	}
	public Boolean getRvOption() {
		return rvOption;
	}
	public void setRvOption(Boolean rvOption) {
		this.rvOption = rvOption;
	}

	
	
}
