package a.star;

import java.util.Arrays;
import java.util.List;

import model.GridSpace;
import model.IMaze;

public class NodeMap implements INodeMap {

		private static final GridSpace PATH = null;
		private GridSpace[][] data;
		private GraphNode[][] nodes;
		private GraphNode root;
		private GraphNode goal;

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
		if (isBarrior(robLoc))
		{
			return null;
		}
	
		
		GraphNode node = init(robLoc);
			if (isGoal(robLoc))
			{
				goal = node;
			}
		//add up
		
		return node;
	}

	private boolean isGoal(int[] robLoc) {
		// TODO Auto-generated method stub
		return data[robLoc[0]][robLoc[1]]==GridSpace.GOAL;
	}

	private boolean isBarrior(int[] robLoc) {

		return data[robLoc[0]][robLoc[1]]==GridSpace.OBSTICAL;
	}

	private GraphNode init(int[] robLoc) {
		if (nodes[robLoc[0]][robLoc[1]]==null)
		{
			GraphNode node = new GraphNode();
			nodes[robLoc[0]][robLoc[1]]= node;
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


	public void draw() {
		
		for (GraphNode[] row: nodes)
		{
			for(GraphNode node: row)
			{
				if (node ==null)
				{
					System.out.print("#");
				}
				else
				{
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		
	}

	public GraphNode getGoal() {
		return goal;
	}

	public void overLayPath(List<GraphNode> path) {
		 if (path!=null)
		 {
			 
			// GridSpace[][] grid  = Arrays.copyOf(data, data.length);
			   for ( GraphNode step:path)
			   {
				   System.out.println(Arrays.toString(step.getPosition()));
				   try {
					int x = step.getPosition()[0];
					   int y = step.getPosition()[1];
					   data[x][y] = PATH;
					} catch (Exception e) {
						System.out.println("Out of Bounds: "+ Arrays.toString(step.getPosition()));
						//e.printStackTrace();
					}
			   }
			   drawData();
		 }
			    else
			    	System.out.println("No Path found");
	}

	private void drawData() {
		for (GridSpace[] row: data)
		{
			for(GridSpace node: row)
			{
				if (node ==PATH)
				{
					System.out.print("P");
				}
				if (node ==GridSpace.OBSTICAL)
				{
					System.out.print("#");
				}
				if (node == GridSpace.ROBOT)
				{
					System.out.print("R");
				}
				else
				{
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}


}
