package co.grandcircus.WatchYourBackpack.Entities;

import java.util.List;

import co.grandcircus.WatchYourBackpack.Models.DSModel.Currently;

public class GameStatus {
	
	private Player mainPlayer;
	private Player partner;
	private int health = 3;
	private List<Item> items;
	private Currently weather;
	private String parkcode;
	private Integer totalAttack;
	private Integer totalFire;
	private Integer	totalResourcefulness;
	private DBPark park;
	
	public Player getMainPlayer() {
		return mainPlayer;
	}
	public void setMainPlayer(Player mainPlayer) {
		this.mainPlayer = mainPlayer;
	}
	public Player getPartner() {
		return partner;
	}
	public void setPartner(Player partner) {
		this.partner = partner;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public Currently getWeather() {
		return weather;
	}
	public void setWeather(Currently weather) {
		this.weather = weather;
	}
	
	
	@Override
	public String toString() {
		return "GameStatus [mainPlayer=" + mainPlayer + ", partner=" + partner + ", health=" + health + ", items="
				+ items + ", weather=" + weather + ", parkcode=" + parkcode + ", totalAttack=" + totalAttack
				+ ", totalFire=" + totalFire + ", totalResourcefulness=" + totalResourcefulness + ", park=" + park
				+ "]";
	}
	public String getParkcode() {
		return parkcode;
	}
	public void setParkcode(String parkcode) {
		this.parkcode = parkcode;
	}
	public Integer getTotalAttack() {
		return totalAttack;
	}
	public void setTotalAttack(Integer totalAttack) {
		this.totalAttack = totalAttack;
	}
	public Integer getTotalFire() {
		return totalFire;
	}
	public void setTotalFire(Integer totalFire) {
		this.totalFire = totalFire;
	}
	public Integer getTotalResourcefulness() {
		return totalResourcefulness;
	}
	public void setTotalResourcefulness(Integer totalResourcefulness) {
		this.totalResourcefulness = totalResourcefulness;
	}
	public DBPark getPark() {
		return park;
	}
	public void setPark(DBPark park) {
		this.park = park;
	}


}
