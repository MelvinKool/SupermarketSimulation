package supermarket.model.person;

public abstract class Person {
	public double x,y;
	public Person(double spawnX, double spawnY) {
		x = spawnX;
		y = spawnY;
	}
	public abstract String toString();
}
