package supermarket.model.customer;

public class Mother extends Customer{
	public Mother(double spawnX, double spawnY){
		super(spawnX, spawnY);
	}
	@Override
	public String toString(){
		return "Mother";
	}
}
