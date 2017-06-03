package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import model.LocationScape;
import model.Maze;

import org.junit.Test;

import a.star.AStar;
import a.star.GraphNode;
import a.star.NodeMap;
import telnet.TaskException;
import telnet.TelnetProxy;

public class TestAstar {

	@Test
	public void test() {
		TelnetProxy proxy = new TelnetProxy();
		
		try {
			proxy.connectToBot();
		
		
		    Maze m = new Maze();
		    LocationScape location=proxy.where();
		    m.initMaze(location, 3);
		    m.draw();
		    
		    NodeMap map = new NodeMap(m);
		    map.draw();
		    
		    AStar aStar = new AStar();
		   List<GraphNode> path = aStar.doAstar(map.getRoot(), map.getGoal());
		   map.overLayPath(path);
//		    if (path!=null)
//		   for ( GraphNode step:path)
//		   {
//			   System.out.println(Arrays.toString(step.getPosition()));
//		   }
//		    else
//		    	System.out.println("No Path found");
		    
		} catch (TaskException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}

}
