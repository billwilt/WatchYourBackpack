package co.grandcircus.WatchYourBackpack;

import java.util.Random;

import org.springframework.stereotype.Component;

import co.grandcircus.WatchYourBackpack.Entities.Item;
import co.grandcircus.WatchYourBackpack.Entities.Player;

@Component
public class GameService {

	public Integer getTotalAttack(Player player1, Player player2, Item item1, Item item2, Item item3) {
		Integer itemsAttack = item1.getAttackAdd() + item2.getAttackAdd() + item3.getAttackAdd();
		return player1.getAttack() + player2.getAttack() + itemsAttack;
	}
	
	public Integer getTotalFire(Player player1, Player player2, Item item1, Item item2, Item item3) {
		Integer itemsFire = item1.getFireAdd() + item2.getFireAdd() + item3.getFireAdd();
		return player1.getFire() + player2.getFire() + itemsFire;
	}

	public Integer getTotalResourcefulness(Player player1, Player player2, Item item1, Item item2, Item item3) {
		Integer itemsResourcefulness = item1.getResourcefulnessAdd() + item2.getResourcefulnessAdd() + item3.getResourcefulnessAdd();
		return player1.getResourcefulness() + player2.getResourcefulness() + itemsResourcefulness;
	}
	
	
	public boolean winOrNot(int theirLevel, int requiredLevel) {
		Random rand = new Random();
		
		double a = theirLevel + 1;
		double b = requiredLevel + 1;
		
		if (b > a) {
			double ourRand = rand.nextDouble() * a;
			double requiredRand = rand.nextDouble() * b;
			
			System.out.println("your random: " + ourRand);
			System.out.println("required random: " + requiredRand);
			
			return test(a, b);
			
		} else {
			double ourRand = rand.nextDouble() * (a * 3);
			double requiredRand = rand.nextDouble() * b;
			
			System.out.println("your random: " + ourRand);
			System.out.println("required random: " + requiredRand);
			
			return test(a, b);		
		}
	}
	
	public boolean test(double theirSkill, double requiredSkill) {
		if (theirSkill > requiredSkill) {
			return true;
		} else 
			return false;
	}
	
}
