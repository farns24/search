package a.star;

import java.util.ArrayList;
import java.util.List;

public class GraphNode implements Comparable<GraphNode>{
	
	
	
	GraphNode up;
	GraphNode down;
	GraphNode left;
	GraphNode right;
	GraphNode upLeft;
	GraphNode downleft;
	GraphNode upRight;
	GraphNode downRight;
	
	GraphNode from;
	
	double distToGoal;
	
	public List<GraphNode> getNeighbors()
	{
		List<GraphNode> neighbors = new ArrayList<GraphNode>();
		
		if (up!=null)
		{
			neighbors.add(up);
		}
		if (down!=null)
		{
			neighbors.add(down);
		}
		if (left!=null)
		{
			neighbors.add(left);
		}
		if (right!=null)
		{
			neighbors.add(right);
		}
		if (upRight!=null)
		{
			neighbors.add(upRight);
		}
		if (upLeft!=null)
		{
			neighbors.add(upLeft);
		}
		if (downRight!=null)
		{
			neighbors.add(downRight);
		}
		if (downleft!=null)
		{
			neighbors.add(downleft);
		}
		
		
		return neighbors;
	}
	
	public double getDistToGoal() {
		return distToGoal;
	}
	public void setDistToGoal(double distToGoal) {
		this.distToGoal = distToGoal;
	}
	boolean isGoal = false;
	
	public GraphNode getFrom() {
		return from;
	}
	public void setFrom(GraphNode from) {
		this.from = from;
	}
	public boolean isGoal() {
		return isGoal;
	}
	public void setGoal(boolean isGoal) {
		this.isGoal = isGoal;
	}
	public GraphNode getUp() {
		return up;
	}
	public void setUp(GraphNode up) {
		this.up = up;
	}
	public GraphNode getDown() {
		return down;
	}
	public void setDown(GraphNode down) {
		this.down = down;
	}
	public GraphNode getLeft() {
		return left;
	}
	public void setLeft(GraphNode left) {
		this.left = left;
	}
	public GraphNode getRigth() {
		return right;
	}
	public void setRigth(GraphNode rigth) {
		this.right = rigth;
	}
	public GraphNode getUpLeft() {
		return upLeft;
	}
	public void setUpLeft(GraphNode upLeft) {
		this.upLeft = upLeft;
	}
	public GraphNode getDownleft() {
		return downleft;
	}
	public void setDownleft(GraphNode downleft) {
		this.downleft = downleft;
	}
	public GraphNode getUpRight() {
		return upRight;
	}
	public void setUpRight(GraphNode upRight) {
		this.upRight = upRight;
	}
	public GraphNode getDownRight() {
		return downRight;
	}
	public void setDownRight(GraphNode downRight) {
		this.downRight = downRight;
	}
	@Override
	public int compareTo(GraphNode arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
