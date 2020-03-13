package co.grandcircus.WatchYourBackpack.DSModel.NpsResponse;

public class Park {
	
	private Addresses addresses;
	private Campsites campsites;
	
	public Addresses getAddresses() {
		return addresses;
	}
	public void setAddresses(Addresses addresses) {
		this.addresses = addresses;
	}

	public Campsites getCampsites() {
		return campsites;
	}
	public void setCampsites(Campsites campsites) {
		this.campsites = campsites;
	}
	@Override
	public String toString() {
		return "Park [addresses=" + addresses + ", campsites=" + campsites + "]";
	}
}
