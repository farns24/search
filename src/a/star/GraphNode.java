package a.star;

public class GraphNode {
	
	GraphNode up;
	GraphNode down;
	GraphNode left;
	GraphNode rigth;
	GraphNode upLeft;
	GraphNode downleft;
	GraphNode upRight;
	GraphNode downRight;
	
	GraphNode from;
	
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
		return rigth;
	}
	public void setRigth(GraphNode rigth) {
		this.rigth = rigth;
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

}
