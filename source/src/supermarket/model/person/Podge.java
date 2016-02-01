package supermarket.model.person;

import supermarket.model.Simulator;

public class Podge extends Customer{
	public Podge(Simulator simulator, double spawnX, double spawnY){
		super(simulator, spawnX, spawnY);
	}
	@Override
	public String toString(){
		return "Podge";
	}
}
