package co.grandcircus.WatchYourBackpack.Models.CampgroundModel;

public class Image {
	
	private String url;
	private String altText;
	private String credit;
	private String imageId;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAltText() {
		return altText;
	}
	public void setAltText(String altText) {
		this.altText = altText;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	@Override
	public String toString() {
		return "Image [url=" + url + ", altText=" + altText + ", credit=" + credit + ", imageId=" + imageId + "]";
	}
	
	
	

}
