package co.grandcircus.WatchYourBackpack.Models.CampgroundModel;

public class Campsites {
		//The following strings are actually all numbers
	//CAN'T RELY ON THESE NUMBERS some of the responses just don't make sense
	private String other;
	private String group;
	private String totalsites;
	private String electricalhookups;
	private String rvonly;
	
	
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getTotalsites() {
		return totalsites;
	}
	public void setTotalsites(String totalsites) {
		this.totalsites = totalsites;
	}
	public String getElectricalhookups() {
		return electricalhookups;
	}
	public void setElectricalhookups(String electricalhookups) {
		this.electricalhookups = electricalhookups;
	}
	public String getRvonly() {
		return rvonly;
	}
	public void setRvonly(String rvonly) {
		this.rvonly = rvonly;
	}
	@Override
	public String toString() {
		return "Campsite [other=" + other + ", group=" + group + ", totalsites=" + totalsites + ", electricalhookups="
				+ electricalhookups + ", rvonly=" + rvonly + "]";
	}
	
	
}
