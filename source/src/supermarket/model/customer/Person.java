package supermarket.model.customer;

public abstract class Person {
	public int x,y;
	public Person(int spawnX, int spawnY) {
		x = spawnX;
		y = spawnY;
	}
	public abstract String toString();
}
