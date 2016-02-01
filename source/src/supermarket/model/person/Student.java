package supermarket.model.person;

import supermarket.model.Simulator;

public class Student extends Customer{
	public Student(Simulator simulator, double spawnX, double spawnY){
		super(simulator, spawnX, spawnY);
	}
	@Override
	public String toString(){
		return "Student";
	}
}
