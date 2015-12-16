package supermarket.model.astar;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import supermarket.model.Simulator;

public class AStar {
	private class Node{
		int x,y, gCost ,hCost;
		Node previousNode;
		public Node(int x, int y){
			this.x = x;
			this.y = y;
		}
		public int getFCost(){
			return gCost + hCost;
		}
	}
	private final int EDGEWISECOST = 14, STRAIGHTCOST = 10;
	private List<Node> closedList;
	private List<Node> openList;
	private Simulator simulator;
	public AStar(Simulator simulator){
		this.simulator = simulator;
	}
	
	/**
	 * Computes the path between a list
	 * @param from
	 * @param to
	 * @param occupiedNodes
	 * @return
	 */
	public List<Point> computeShortestPath(Point from, Point to){
		closedList = new ArrayList<Node>();
		openList = new ArrayList<Node>();
		//add the start node to the open list
		Node startingNode = new Node(from.x,from.y);
		Node targetNode = new Node(from.x,from.y);
		openList.add(startingNode);
		Node currentNode;
		while(openList.size() > 0){
			currentNode = openList.get(0);
			currentNode = getLowestFCostNode(currentNode);
			openList.remove(currentNode);
			closedList.add(currentNode);
			//end node reached
			if(currentNode.x == to.x && currentNode.y == to.y)
				return traceBackRoute(currentNode,startingNode);
			for(Node neighbour : getNeighbourNodes(currentNode)){
				if(closedList.contains(neighbour))
					continue;
				int newMovementCostToNeighbour = currentNode.gCost + getDistance(currentNode,neighbour);
				if(newMovementCostToNeighbour < neighbour.gCost || !openList.contains(neighbour)){
					neighbour.gCost = newMovementCostToNeighbour;
					neighbour.hCost = getDistance(neighbour, targetNode);
					neighbour.previousNode = currentNode;
					if(!openList.contains(neighbour))
						openList.add(neighbour);
				}
			}
		}
		//there is no way to the end point
		return null;
	}
	
	private int getDistance(Node a, Node b){
		int distX = Math.abs(a.x - b.x);
		int distY = Math.abs(a.y - b.y);
		if(distX>distY)
			return 14*distY + 10*(distX - distY);
		return 14*distX + 10 * (distY-distX);
	}
	
	private Node getLowestFCostNode(Node currentNode){
		Node lowestNode = openList.get(0);
		for(Node n : openList){
			if(n.getFCost() < lowestNode.getFCost() || n.getFCost() == lowestNode.getFCost() && n.hCost < currentNode.hCost){
				lowestNode = n;
			}
		}
		return lowestNode;
	}
	
	private List<Node> getNeighbourNodes(Node current){
		List<Node> neighbours = new ArrayList<Node>();
		for(int y = -1; y < 2;y++){
			for(int x = -1; x < 2; x++){
				//the current node's position
				if(x == 0 && y ==0)
					continue;
				int checkX = current.x + x;
				int checkY = current.y + y;
				if(validateNode(checkX,checkY)){
					neighbours.add(new Node(checkX,checkY));
				}
			}
		}
		return neighbours;
	}
	
	private boolean validateNode(int x, int y){
		return !simulator.occupiedCells[y][x] && (x >= 0 && x < simulator.NUMCELLSX && y >= 0 && y < simulator.NUMCELLSY);
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
		while(temp.x != startingNode.x && temp.y != startingNode.y){
			//add the new item before, because it's the previous node
			route.add(0,new Point(temp.x,temp.y));
			//go to the previous node
			temp = temp.previousNode;
		}
		return route;
	}
}
