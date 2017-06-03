package model;

import telnet.TelnetProxy;

public class MoveNorthCommand implements IRoboInstruction {

	
	
	
	@Override
	public void moveRobot(TelnetProxy proxy) {
		System.out.println("Use RobotNavigator.move(new MoveNorthCommand) to move north");

	}

}
