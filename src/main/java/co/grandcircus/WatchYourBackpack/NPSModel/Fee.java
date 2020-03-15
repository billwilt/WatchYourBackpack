package co.grandcircus.WatchYourBackpack.NPSModel;

public class Fee {
	
	private String cost;
	private String title;
	
	
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Fee [cost=" + cost + ", title=" + title + "]";
	}

}
