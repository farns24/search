package a.star;

import model.GridSpace;
import model.IMaze;

public class NodeMap {

	public NodeMap(IMaze maze) {
		
		//Create a connected graph between each node in the data.
		int[] robLoc = maze.getRobotPos();
		
		GridSpace[][] data = maze.getData();
		
		//For reference
		GraphNode[][] nodes = new GraphNode[data.length][data[0].length];
		
		//Recursively build a connected graph
		
		GraphNode root = buildGraph(robLoc);
		
		
	}

	private GraphNode buildGraph(int[] robLoc) {
		
		GraphNode node = new GraphNode();
		
		//add up
		
		//add down
		
		//add left
		
		//add east
		
		//add upLeft
		
		//add downLeft
		
		//add upRight
		
		//add downRight
		
		return null;
	}

}
