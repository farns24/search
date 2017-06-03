package test;

import static org.junit.Assert.*;

import java.awt.Robot;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import model.ForwardCommand;
import model.MoveNorthCommand;
import model.RobotNavigator;
import model.RotateClockwiseCommand;

import org.junit.Test;

import telnet.TaskException;
import telnet.TelnetProxy;

public class TestRoboInstructions {

	@Test
	public void test() {
		TelnetProxy proxy = new TelnetProxy();
		try {
			proxy.connectToBot();
		} catch (TaskException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//new ForwardCommand().moveRobot(proxy);
		//new ForwardCommand().moveRobot(proxy);
//		new RotateClockwiseCommand().moveRobot(proxy);
//		new RotateCounterClockwiseCommand().moveRobot(proxy);
//		new ForwardCommand().moveRobot(proxy);
	}
	
	@Test
	public void  testWrapper(){
		TelnetProxy proxy = new TelnetProxy();
		try {
			proxy.connectToBot();
		} catch (TaskException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RobotNavigator navigator = new RobotNavigator(proxy);
		navigator.move(new MoveNorthCommand());
		
	}

}
