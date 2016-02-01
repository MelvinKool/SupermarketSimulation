package supermarket.model.person;

import supermarket.model.Simulator;

public class Mother extends Customer{
	public Mother(Simulator simulator, double spawnX, double spawnY){
		super(simulator, spawnX, spawnY);
	}
	@Override
	public String toString(){
		return "Mother";
	}
}
