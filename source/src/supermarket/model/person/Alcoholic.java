package supermarket.model.person;

import supermarket.model.Simulator;

public class Alcoholic extends Customer{
	public Alcoholic(Simulator simulator, double spawnX, double spawnY){
		super(simulator,spawnX, spawnY);
	}
	@Override
	public String toString(){
		return "Alcoholic";
	}
}
