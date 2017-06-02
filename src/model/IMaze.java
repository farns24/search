package model;

/**
 * IMaze is the public interface of a Maze object
 * 
 * A maze takes the information stored in a LocationScape object. 
 * @author wolfa
 *
 */
public interface IMaze {
	/**
	 * Initializes the maze from a LocationScape object. Goal id is initialized in it. 
	 * @param location
	 * @param goalId
	 */
    public void initMaze(LocationScape location, int goalId);
    
    
    public GridSpace[][] getData();
    
    public void draw();
    
    public int[] getRobotPos();

    public int[] getGoalPos();
    
}

