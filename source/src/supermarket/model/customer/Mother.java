package supermarket.model.customer;

public class Mother extends Customer{
	public Mother(int spawnX, int spawnY){
		super(spawnX, spawnY);
	}
	@Override
	public String toString(){
		return "Mother";
	}
}
