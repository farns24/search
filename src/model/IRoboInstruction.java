package model;

import telnet.TelnetProxy;

public interface IRoboInstruction {

	/**
	 * executes the step for the robot to follow. 
	 */
	public void moveRobot(TelnetProxy proxy);
}
