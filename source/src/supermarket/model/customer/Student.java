package supermarket.model.customer;

public class Student extends Customer{
	public Student(double spawnX, double spawnY){
		super(spawnX, spawnY);
	}
	@Override
	public String toString(){
		return "Student";
	}
}
