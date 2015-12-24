package supermarket.model.customer;

public class Student extends Customer{
	public Student(int spawnX, int spawnY){
		super(spawnX, spawnY);
	}
	@Override
	public String toString(){
		return "Student";
	}
}
