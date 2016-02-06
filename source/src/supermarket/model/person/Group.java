package supermarket.model.person;

public enum Group {
	STUDENT(1),MOTHER(2),ALCOHOLIC(3),PODGE(4);
	private final int id;
	Group(int id){
		this.id = id;
	}
	public int getId(){
		return id;
	}
}
