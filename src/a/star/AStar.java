package a.star;

import java.util.List;

import model.GridSpace;
import model.IMaze;
import model.IRoboInstruction;

public class AStar implements IPathFinder {

	@Override
	public List<IRoboInstruction> findPath(IMaze maze) {
		
		//GridSpace[][] data = maze.getData();
		
		INodeMap nm = new NodeMap(maze);
		
		//TODO perform A* Logic here
		
		//new helper().convert(
		return null;
	}

}
