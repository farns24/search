package model;

import telnet.TelnetProxy;

public class MoveSouthEastCommand implements IRoboInstruction {
	
	@Override
	public void moveRobot(TelnetProxy proxy) {
		RobotNavigator rn = new RobotNavigator(proxy);
		Direction currentDir = rn.findDirection();
		RotateClockwiseCommand cw = new RotateClockwiseCommand();
		RotateCounterClockwiseCommand ccw = new RotateCounterClockwiseCommand();
		ForwardCommand fc = new ForwardCommand();

		switch (currentDir) {
			case EAST:
				cw.moveRobot(proxy, 1);
				fc.moveRobot(proxy);
				break;
			case NORTHEAST:
				cw.moveRobot(proxy, 2);
				fc.moveRobot(proxy);
				break;
			case NORTH:
				cw.moveRobot(proxy, 3);
				fc.moveRobot(proxy);
				break;
			case NORTHWEST:
				cw.moveRobot(proxy, 4);
				fc.moveRobot(proxy);
				break;
			case WEST:
				ccw.moveRobot(proxy, 3);
				fc.moveRobot(proxy);
				break;
			case SOUTHWEST:
				ccw.moveRobot(proxy, 2);
				fc.moveRobot(proxy);
				break;
			case SOUTH:
				ccw.moveRobot(proxy, 1);
				fc.moveRobot(proxy);
				break;
			case SOUTHEAST:
				fc.moveRobot(proxy);
				break;
			case UNKNOWN:
				System.out.println("Could not determine location");
				break;
		}
	}
}
