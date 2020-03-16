package co.grandcircus.WatchYourBackpack.Models.NPSModel;

public class Image {
	
	private String url;
	private String altText;
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
	@Override
	public String toString() {
		return "Image [url=" + url + ", altText=" + altText + "]";
	}
	

}
