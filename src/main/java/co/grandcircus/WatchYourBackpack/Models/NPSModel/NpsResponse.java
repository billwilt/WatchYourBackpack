package co.grandcircus.WatchYourBackpack.Models.NPSModel;

import java.util.List;

public class NpsResponse {
	
	private List<Park> data;

	@Override
	public String toString() {
		return "NpsResponse [data=" + data + "]";
	}

	public List<Park> getData() {
		return data;
	}

	public void setData(List<Park> data) {
		this.data = data;
	}


}
