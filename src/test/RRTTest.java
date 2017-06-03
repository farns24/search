package test;

import a.star.RRT;
import model.LocationScape;
import model.Maze;
import model.RobotLocation;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

/**
 * Created by michael on 6/3/17.
 */
public class RRTTest {

    int mult = 118;
    RRT rrt = new RRT();
    Maze maze = new Maze();

    @Before
    public void setUp() throws Exception {
        LocationScape ls = new LocationScape();
        RobotLocation robot = new RobotLocation();
        robot.setCenter(new double[] {0,0});
        RobotLocation goal = new RobotLocation();
        goal.setCenter(new double[] {13* mult,7* mult});
        ls.put("robot", robot);
        ls.put("3", goal);
        for (int i = 0; i < 5; i++) {
            RobotLocation rL1 = new RobotLocation();
            rL1.setCenter(new double[] {2* mult,i* mult});
            RobotLocation rL2 = new RobotLocation();
            rL2.setCenter(new double[] {8* mult,(i+3)* mult});
            Random rand = new Random();
            ls.put(Integer.toString(rand.nextInt()), rL1);
            ls.put(Integer.toString(rand.nextInt()), rL2);
        }
        maze.initMaze(ls, 3);
        maze.draw();
    }

    @Test
    public void findPath() throws Exception {
        rrt.findPath(maze);
        rrt.printGraph();
        rrt.printPath();
    }

}