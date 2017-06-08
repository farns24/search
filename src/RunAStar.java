import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.hamcrest.Condition.Step;

import a.star.AStar;
import a.star.GraphNode;
import a.star.IPathFinder;
import a.star.NodeMap;
import a.star.RRT;
import model.IRoboInstruction;
import model.LocationScape;
import model.Maze;
import model.MotionState;
import model.MyUtils;
import model.RobotLocation;
import model.potentialFields.AttractionField;
import model.potentialFields.CompositeField;
import model.potentialFields.PotentialField;
import model.potentialFields.RepulsionField;
import telnet.ITelnetProxy;
import telnet.TaskException;
import telnet.TelnetProxy;

public class RunAStar {

	public static void main(String[] args) {
		// Main

		String goalID = "3";
		TelnetProxy proxy = new TelnetProxy();
		try {
			proxy.connectToBot();
			Maze m = new Maze();
			LocationScape location = proxy.where();
			m.initMaze(location, 3);
			m.draw();

//			NodeMap map = new NodeMap(m);
//			map.draw();

			IPathFinder pathFinder = new AStar();
		//	RRT pathFinder = new RRT();
			
			
			List<IRoboInstruction> commands = pathFinder.findPath(m);
			//pathFinder.printPath();
			
			for (IRoboInstruction cmd: commands)
			{
				cmd.moveRobot(proxy);
			}
			
			/*
			List<GraphNode> path = aStar.doAstar(map.getRoot(), map.getGoal());
			Collections.reverse(path);
			map.overLayPath(path);

			//Loop forever
			while (true) {

				// initialize potential fields
				// RobotLocation goal = map.get(goalID); //Goal location
				//If Path is larger than 0
				if (path.size() > 0) {
					
					//Get next step
					GraphNode step = path.get(0);
					
					// get robot position
					try {
						RobotLocation rob = proxy.whereRobot();
						//if something broke, stop the robot and try again
						if (rob == null || rob.getCenter() == null) {
							proxy.speed(0, 0);
							continue;
						}
						
						
						System.out.println("Robot Position "+ Arrays.toString(rob.getCenter()));
						System.out.println("Step Position "+ Arrays.toString(step.getPosition()));
						
						
						// get potential field vector pointing from the robot to the step
						int[] vect = new int[]{step.getPosition()[0]-rob.getCenter()[0],step.getPosition()[1]-rob.getCenter()[1]};
						//System.out.println(Arrays.toString(vect));
						// Stop if at goal
						
						
						
						double robTheta = rob.getOrientation();
						double goalTheta = rob.getGoalTheta(vect);
						
						MotionState command = MyUtils.getOrders(robTheta,
								goalTheta);
							
						command.run(proxy);
						path.remove(0);
						
						if (MyUtils.madeIt(vect,path.size()==0)&& path.size()==0) {
								proxy.speed(0, 0);
								break;
							
						}
						
						
						
						

					

						

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else
				{
					proxy.speed(0, 0);
				}
			}*/
		} catch (TaskException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// end

	}

}
