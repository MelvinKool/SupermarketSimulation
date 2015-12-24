package supermarket.model.customer;

public class Alcoholic extends Customer{
	public Alcoholic(int spawnX, int spawnY){
		super(spawnX, spawnY);
	}
	@Override
	public String toString(){
		return "Alcoholic";
	}
}
