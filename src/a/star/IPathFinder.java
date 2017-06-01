package a.star;

import java.util.List;

import model.IMaze;
import model.IRoboInstruction;

public interface IPathFinder {

	/**
	 * 
	 * @param maze
	 * @return a list of commands from the robot to the goal. 
	 */
	public List<IRoboInstruction> findPath(IMaze maze);
}
