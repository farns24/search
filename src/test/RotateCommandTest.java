package test;

import model.LocationScape;
import model.Maze;
import model.RotateClockwiseCommand;
import org.junit.Test;
import telnet.TaskException;
import telnet.TelnetProxy;

import static org.junit.Assert.*;

/**
 * Created by michael on 6/5/17.
 */
public class RotateCommandTest {

    @Test
    public void moveRobot() throws Exception {
        TelnetProxy proxy = new TelnetProxy();
        RotateClockwiseCommand c = new RotateClockwiseCommand();

        try {
            proxy.connectToBot();

            LocationScape locBefore = proxy.where();
            c.moveRobot(proxy,3);
//            LocationScape locAfter = proxy.where();
        } catch (TaskException e) {
            e.printStackTrace();
        }
    }

}