package co.grandcircus.WatchYourBackpack.NpsResponse;

public class Campsites {
	
	private int totalsites;
	private int tentonly;
	private int rvonly;
	
	public int getTotalsites() {
		return totalsites;
	}
	public void setTotalsites(int totalsites) {
		this.totalsites = totalsites;
	}
	public int getTentonly() {
		return tentonly;
	}
	public void setTentonly(int tentonly) {
		this.tentonly = tentonly;
	}
	public int getRvonly() {
		return rvonly;
	}
	public void setRvonly(int rvonly) {
		this.rvonly = rvonly;
	}
	@Override
	public String toString() {
		return "Campsites [totalsites=" + totalsites + ", tentonly=" + tentonly + ", rvonly=" + rvonly + "]";
	}

}
