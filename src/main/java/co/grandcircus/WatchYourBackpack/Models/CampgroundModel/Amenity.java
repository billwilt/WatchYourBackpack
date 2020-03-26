package co.grandcircus.WatchYourBackpack.Models.CampgroundModel;

import java.util.List;

public class Amenity {

	private String cellphonereception;
	private String firewoodforsale;
	private List<String> potablewater;
	private List<String> toilets;
	
	public String getCellphonereception() {
		return cellphonereception;
	}
	public void setCellphonereception(String cellphonereception) {
		this.cellphonereception = cellphonereception;
	}
	public String getFirewoodforsale() {
		return firewoodforsale;
	}
	public void setFirewoodforsale(String firewoodforsale) {
		this.firewoodforsale = firewoodforsale;
	}
	public List<String> getPotablewater() {
		return potablewater;
	}
	public void setPotablewater(List<String> potablewater) {
		this.potablewater = potablewater;
	}
	public List<String> getToilets() {
		return toilets;
	}
	public void setToilets(List<String> toilets) {
		this.toilets = toilets;
	}
	@Override
	public String toString() {
		return "Amenity [cellphonereception=" + cellphonereception + ", firewoodforsale=" + firewoodforsale
				+ ", potablewater=" + potablewater + ", toilets=" + toilets + "]";
	}
	
}
