package co.grandcircus.WatchYourBackpack.DSModel;

public class DSResponse {
	

	private Currently currently;

	public Currently getCurrently() {
		return currently;
	}

	public void setCurrently(Currently currently) {
		this.currently = currently;
	}

	@Override
	public String toString() {
		return "DSResponse [currently=" + currently + "]";
	}
	
	
}
