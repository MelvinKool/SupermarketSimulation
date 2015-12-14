package supermarket.model.astar;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class AStar {
	private class Node{
		int x,y,fCost;
		Node previousNode;
		public Node(int x, int y){
			this.x = x;
			this.y = y;
		}
		public void setFCost(int fCost){
			this.fCost = fCost;
		}
	}
	private final int GRIDWIDTH,GRIDHEIGHT;
	private final int EDGEWISECOST = 14, STRAIGHTCOST = 10;
	private List<Node> closedList;
	private List<Node> openList;
	public AStar(int gridWidth, int gridHeight){
		GRIDWIDTH = gridWidth;
		GRIDHEIGHT = gridHeight;
	}
	
	private List<Point> computeShortestPath(Point from, Point to, boolean[][] occupiedNodes){
		closedList = new ArrayList<Node>();
		openList = new ArrayList<Node>();
		//add the start node to the open list
		Node startingNode = new Node(from.x,from.y);
		openList.add(startingNode);
		Node currentNode;
		while(true){
			currentNode = getLowestFCostNode();
			openList.remove(currentNode);
			closedList.add(currentNode);
			if(currentNode.x == to.x && currentNode.y == to.y)
				return traceBackRoute(currentNode,startingNode);
			/*foreach neighbour of the current node*/
				//if neighbour is not traversable or neighbour is in closed list
					//skip to the next neighbour
				//if new path to neighbour is shorter or neighbour is not in open list
					//set f_cost of neighbour
					//set parent of neighbour to current
					//if neighbour is not in open 
						//add neighbour to open
		}
	}
	
	/**
	 * Calculates the heuristic from one point to another point
	 * @return the heuristic
	 */
	private int heuristic(){
		return 0;
	}
	
	private Node getLowestFCostNode(){
		Node lowestNode = openList.get(0);
		for(Node n : openList){
			if(n.fCost < lowestNode.fCost){
				lowestNode = n;
			}
		}
		return lowestNode;
	}
	
	/**
	 * Traces back a route from the end node to the starting node
	 * @param endNode
	 * @param startingNode
	 * @return
	 */
	private List<Point> traceBackRoute(Node endNode, Node startingNode){
		List<Point> route = new ArrayList<Point>();
		route.add(new Point(endNode.x,endNode.y));
		Node temp = endNode;
		while(temp.x != startingNode.x && temp.y != startingNode.y){
			//add the new item before, because it's the previous node
			route.add(0,new Point(temp.x,temp.y));
			//go to the previous node
			temp = temp.previousNode;
		}
		return route;
	}
}
