package a.star;

import model.GridSpace;
import model.IMaze;

public class NodeMap implements INodeMap {

		private GridSpace[][] data;
		private GraphNode[][] nodes;
		private GraphNode root;

		public NodeMap(IMaze maze)
		{
		//Create a connected graph between each node in the data.
		int[] robLoc = maze.getRobotPos();
		
		data = maze.getData();
		
		//For reference
		nodes = new GraphNode[data.length][data[0].length];
		
		//Recursively build a connected graph
		
		root = buildGraph(robLoc);
		
		
	}

	private GraphNode buildGraph(int[] robLoc) {
		
		if (outOfBounds(robLoc))
		{
			return null;
		}
		
		GraphNode node = init(robLoc);
		
		//add up
		
		return node;
	}

	private GraphNode init(int[] robLoc) {
		if (nodes[robLoc[0]][robLoc[1]]==null)
		{
			GraphNode node = new GraphNode();
			node.setUp(buildGraph(new int[]{robLoc[0],robLoc[1]+1}));
			
			
			//add down
			node.setDown(buildGraph(new int[]{robLoc[0],robLoc[1]-1}));
			
			
			//add left
			node.setLeft(buildGraph(new int[]{robLoc[0]-1,robLoc[1]}));
			
			//add right
			node.setRigth(buildGraph(new int[]{robLoc[0]+1,robLoc[1]}));
			
			//add upLeft
			node.setUpLeft(buildGraph(new int[]{robLoc[0]-1,robLoc[1]+1}));
			
			//add downLeft
			node.setDownleft(buildGraph(new int[]{robLoc[0]-1,robLoc[1]-1}));
			
			//add upRight
			node.setUpRight(buildGraph(new int[]{robLoc[0]+1,robLoc[1]+1}));
			
			//add downRight
			node.setDownRight(buildGraph(new int[]{robLoc[0]-1,robLoc[1]+1}));
			
			nodes[robLoc[0]][robLoc[1]]= node;
		}
		
		return nodes[robLoc[0]][robLoc[1]];
	}

	private boolean outOfBounds(int i[]) {
		
		
		
		boolean res = i[0]>-1 && i[0]<data.length && i[1]>-1 && i[1]>data[0].length;
		return !res;
	}

	@Override
	public GraphNode getRoot() {
		return root;
	}

}
