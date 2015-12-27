package supermarket.model.person;

public class Alcoholic extends Customer{
	public Alcoholic(double spawnX, double spawnY){
		super(spawnX, spawnY);
	}
	@Override
	public String toString(){
		return "Alcoholic";
	}
}
