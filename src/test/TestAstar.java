package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import model.LocationScape;
import model.Maze;
import model.RobotLocation;

import org.junit.Test;

import a.star.AStar;
import a.star.GraphNode;
import a.star.NodeMap;
import telnet.TaskException;
import telnet.TelnetProxy;

public class TestAstar {

	@Test
	public void test() {
		//TelnetProxy proxy = new TelnetProxy();
		
		
		
		    Maze m = new Maze();
		    LocationScape location=new LocationScape();
		    RobotLocation robLoc =new RobotLocation();
		    robLoc.setCenter(new double[]{1.0,1.0});
		    location.put("robot",robLoc );

		    RobotLocation goalLoc =new RobotLocation();
		    goalLoc.setCenter(new double[]{1000.0,890.0});
		    location.put("3",goalLoc );
		    
		    m.initMaze(location, 3);
		    m.draw();
		    
		    NodeMap map = new NodeMap(m);
		    map.draw();
		    
		    AStar aStar = new AStar();
		   List<GraphNode> path = aStar.doAstar(map.getRoot(), map.getGoal());
		   map.overLayPath(path);
	}
}

		
