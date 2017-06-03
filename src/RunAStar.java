import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.hamcrest.Condition.Step;

import a.star.AStar;
import a.star.GraphNode;
import a.star.NodeMap;
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
		ITelnetProxy proxy = new TelnetProxy();
		try {
			proxy.connectToBot();
			Maze m = new Maze();
			LocationScape location = proxy.where();
			m.initMaze(location, 3);
			m.draw();

			NodeMap map = new NodeMap(m);
			map.draw();

			AStar aStar = new AStar();
			List<GraphNode> path = aStar.doAstar(map.getRoot(), map.getGoal());
			Collections.reverse(path);
			map.overLayPath(path);

			while (true) {

				// initialize potential fields
				// RobotLocation goal = map.get(goalID); //Goal location
				if (path.size() > 0) {
					GraphNode step = path.get(0);
					// get robot position
					try {
						RobotLocation rob = proxy.whereRobot();
						if (rob == null || rob.getCenter() == null) {
							continue;
						}
						System.out.println("Robot Position "+ Arrays.toString(rob.getCenter()));
						System.out.println("Sep Position "+ Arrays.toString(step.getPosition()));
						// get potential field vector at robot's position
						int[] vect = new int[]{rob.getCenter()[0]-step.getPosition()[0],rob.getCenter()[1]-step.getPosition()[1]};
						System.out.println(Arrays.toString(vect));
						// Stop if at goal
						if (MyUtils.madeIt(vect,path.size()==1)) {
							//proxy.speed(0, 0);
							path.remove(0);
							continue;
						}

						double robTheta = rob.getOrientation();
						double goalTheta = rob.getGoalTheta(vect);

						MotionState command = MyUtils.getOrders(robTheta,
								goalTheta);

						command.run(proxy);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else
				{
					proxy.speed(0, 0);
				}
			}
		} catch (TaskException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// end

	}

}
