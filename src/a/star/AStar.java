package a.star;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
	
	private Set<GraphNode> closedSet = new HashSet<GraphNode>();
	private Set<GraphNode> openSet = new TreeSet<GraphNode>();
	private Map<GraphNode,GraphNode> cameFrom = new HashMap<GraphNode,GraphNode>();
	private Map<GraphNode,Integer> gScore = new HashMap<GraphNode, Integer>();
	private Map<GraphNode,Integer> fScore = new HashMap<GraphNode, Integer>();
	public List<GraphNode> doAstar(GraphNode start, GraphNode goal)
	{
    // The set of nodes already evaluated.
    closedSet.clear();
    // The set of currently discovered nodes that are not evaluated yet.
    // Initially, only the start node is known.
    openSet.clear();
    openSet.add(start);
    // For each node, which node it can most efficiently be reached from.
    // If a node can be reached from many nodes, cameFrom will eventually contain the
    // most efficient previous step.
    cameFrom.clear();

    // For each node, the cost of getting from the start node to that node.
    //gScore := map with default value of Infinity
    // The cost of going from start to start is zero.
    gScore.clear();
    gScore.put(start, 0); 
    // For each node, the total cost of getting from the start node to the goal
    // by passing by that node. That value is partly known, partly heuristic.
    //fScore := map with default value of Infinity
    // For the first node, that value is completely heuristic.
    Integer distToGoal = 0;
	fScore.put(start, distToGoal );//start] := heuristic_cost_estimate(start, goal)

    while (!openSet.isEmpty())// is not empty
    {
        GraphNode current= Collections.min(openSet);//:= the node in openSet having the lowest fScore[] value
        if (current == goal)
        {
            return reconstruct_path(current);
        }
        openSet.remove(current);
        closedSet.add(current);
        
        for (GraphNode neighbor: current.getNeighbors())//each neighbor of current
            {
        	if ( closedSet.contains(neighbor))
        	{
                continue;		// Ignore the neighbor which is already evaluated.
        	}
            // The distance from start to a neighbor
            int tentative_gScore = gScore.get(current) + 1;//dist_between(current, neighbor)
            if (!openSet.contains(neighbor))	// Discover a new node
            {
                openSet.add(neighbor);
            }
            else if (tentative_gScore >= gScore.get(neighbor))
                continue;		// This is not a better path.

            // This path is the best until now. Record it!
            cameFrom.put(neighbor, current);
            gScore.put(neighbor, tentative_gScore);
            fScore.put(neighbor, gScore.get(neighbor) + heuristic_cost_estimate(neighbor, goal));
            }
    }
	return null;
    
	}
	
private Integer heuristic_cost_estimate(GraphNode neighbor, GraphNode goal) {
		// TODO Auto-generated method stub
		return null;
	}

    private List<GraphNode> reconstruct_path(GraphNode current){
    List<GraphNode> total_path = new ArrayList<GraphNode>();
    		total_path.add(current);
    while (cameFrom.keySet().contains(current))
    {
        current = cameFrom.get(current);
        total_path.add(current);
    }
    return total_path;
    }
}
