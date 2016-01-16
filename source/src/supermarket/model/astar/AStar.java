package supermarket.model.astar;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import supermarket.model.Simulator;

/**
 * Class for path finding
 * @author melvin
 *
 */
public class AStar {
	/**
	 * A star Node
	 * @author melvin
	 *
	 */
	private class Node{
		int x,y, gCost,hCost;
		Node previousNode;
		public Node(int x, int y){
			this.x = x;
			this.y = y;
		}
		public int getFCost(){
			return gCost + hCost;
		}
	}
//	private class NodeList<T> extends ArrayList<T>{
//		
//	}
	private final int EDGEWISECOST = 14, STRAIGHTCOST = 10;
	private List<Node> closedList;
	private List<Node> openList;
	private Simulator simulator;
	public AStar(Simulator simulator){
		this.simulator = simulator;
		closedList = new ArrayList<Node>();
		openList = new ArrayList<Node>();
	}

	/**
	 * Computes the path between a list
	 * @param from
	 * @param to
	 * @param occupiedNodes
	 * @return The path if found, otherwise null
	 */
	public List<Point> computeShortestPath(Point from, Point to){
		openList.clear();
		closedList.clear();
		Node start = new Node(from.x,from.y);
		Node goal = new Node(to.x,to.y);
		//the start or finish is an occupied node
		if(!validateNode(start.x, start.y) || !validateNode(goal.x,goal.y)){
			return null;
		}
		start.gCost = 0;
		start.hCost = getDistance(start, goal);
		openList.add(start);
		Node current;
		while(openList.size() > 0){
			current = openList.get(0);
			current = getLowestFCostNode();
			if(current.x == goal.x && current.y == goal.y)
				return traceBackRoute(current, start);
			openList.remove(current);
			closedList.add(current);
			for(Node neighbour: getNeighbourNodes(current)){
//				System.out.println("closedList contains neighbour" + closedList);
				if(closedList.stream().anyMatch(neighbourNode -> neighbourNode.x == neighbour.x && neighbourNode.y == neighbour.y))
					continue;
//				System.out.println("Yes");
				int tentative_gCost = current.gCost + getDistance(current, neighbour);
				if(!openList.stream().anyMatch(neighbourNode -> neighbourNode.x == neighbour.x && neighbourNode.y == neighbour.y))
					openList.add(neighbour);
				else if(tentative_gCost >= neighbour.gCost)
					continue;//the current path to the neighbour is worse
				neighbour.previousNode = current;
				neighbour.gCost = tentative_gCost;
				neighbour.hCost = getDistance(neighbour, goal);
			}
		}
		System.out.println("There is no path to that location");
		return null;
	}
	
	private int getDistance(Node a, Node b){
		int distX = Math.abs(a.x - b.x);
		int distY = Math.abs(a.y - b.y);
		if(distX>distY)
			return EDGEWISECOST*distY + STRAIGHTCOST*(distX - distY);
		return EDGEWISECOST*distX + STRAIGHTCOST * (distY-distX);
	}
	
	private Node getLowestFCostNode(){
		Node lowestFCostNode = openList.get(0);
		for(Node n : openList){
			if(n.getFCost() < lowestFCostNode.getFCost() || n.getFCost() == lowestFCostNode.getFCost() && n.hCost < lowestFCostNode.hCost){
				lowestFCostNode = n;
			}
		}
		return lowestFCostNode;
	}
	
	/**
	 * Gets the neighbours of the current node
	 * @param current
	 * @return list of neighbours
	 */
	private List<Node> getNeighbourNodes(Node current){
		List<Node> neighbours = new ArrayList<Node>();
		//temporary neighbour
		Node neighbour;
		for(int y = -1; y < 2;y++){
			for(int x = -1; x < 2; x++){
				//the current node's position
				if(x == 0 && y ==0)
					continue;
				int checkX = current.x + x;
				int checkY = current.y + y;
				if(validateNode(checkX,checkY)){
					//if this neighbour is in closed or open list, return that one, else return new node
					try{
						neighbour = getFromNodeList(openList, checkX, checkY);
					}
					catch(NoSuchElementException notFoundInOpenList){
						//try searching the closed list
						try{
							neighbour = getFromNodeList(closedList, checkX, checkY);
						}
						catch(NoSuchElementException notFoundInClosedList){
							neighbour = new Node(checkX,checkY);
						}
					}
					catch(Exception ex){
						continue;
					}
					neighbours.add(neighbour);
				}
			}
		}
		return neighbours;
	}
	
	private boolean validateNode(int x, int y){
		return (x >= 0 && x < simulator.NUMCELLSX && y >= 0 && y < simulator.NUMCELLSY) && (!simulator.occupiedCells[y][x]);
	}
	
	/**
	 * Searches a list of nodes for the node with the specified x and y coordinates
	 * @param nodeList: List of nodes
	 * @param x
	 * @param y
	 * @return the node if found
	 * @throws NoSuchElementException
	 */
	private Node getFromNodeList(List<Node> nodeList, int x, int y) throws NoSuchElementException{
		return nodeList.stream().filter(openNode -> openNode.x == x && openNode.y == y).findFirst().get();
	}
	
	/**
	 * Traces back a route from the end node to the starting node
	 * @param endNode
	 * @param startingNode
	 * @return
	 */
	private List<Point> traceBackRoute(Node endNode, Node startingNode){
		List<Point> route = new ArrayList<Point>();
		Node temp = endNode;
		while(!(temp.x == startingNode.x && temp.y == startingNode.y)){
			//add the new item before, because it's the previous node
			route.add(0,new Point(temp.x,temp.y));
			//go to the previous node
			temp = temp.previousNode;
		}
		return route;
	}
}
