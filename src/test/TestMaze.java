package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import telnet.TaskException;
import telnet.TelnetProxy;
import model.IMaze;
import model.LocationScape;
import model.Maze;
import model.RobotLocation;

public class TestMaze {

	@Before
	public void setUp() throws Exception {
	}

//	@Test
//	public void testInitMazeSimple() {
//		
//		IMaze maze = new Maze();
//		
//		LocationScape location = new LocationScape();
//		
//		RobotLocation robotLocation = new RobotLocation();
//		robotLocation.setCenter(new double[]{1.0,1.0});
//		location.put("Robot", robotLocation );
//		
//		RobotLocation goalLocation = new RobotLocation();
//		goalLocation.setCenter(new double[]{50.0,50.0});
//		location.put("3",goalLocation );
//		
//		
//		
//		
//		RobotLocation obst1 = new RobotLocation();
//		obst1.setCenter(new double[]{10,30});
//		location.put("1", obst1 );
//
//		RobotLocation obst5 = new RobotLocation();
//		obst5.setCenter(new double[]{10,40});
//		location.put("5", obst5 );
//		
//		maze.initMaze(location , 3);
//		//maze.draw();
//		
//		
//		
//	}
//	
//	@Test
//	public void buildMapFromCamTest()
//	{
//		TelnetProxy proxy = new TelnetProxy();
//		
//		try {
//			proxy.connectToBot();
//		
//		
//		    Maze m = new Maze();
//		    LocationScape location=proxy.where();;
//		    m.initMaze(location, 3);
//		    m.draw();
//		} catch (TaskException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
