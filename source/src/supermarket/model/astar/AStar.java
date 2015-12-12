package supermarket.model.astar;

public class AStar {
	private final int GRIDWIDTH,GRIDHEIGHT;
	private final int EDGEWISECOST = 14, STRAIGHTCOST = 10;
	public AStar(int gridWidth, int gridHeight){
		GRIDWIDTH = gridWidth;
		GRIDHEIGHT = gridHeight;
	}
	
	/**
	 * Calculates the heuristic from one point to another point
	 * @return the heuristic
	 */
	private int heuristic(){
		return 0;
	}
}
