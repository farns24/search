package model;

import telnet.TaskException;
import telnet.TelnetProxy;

public class RobotNavigator {

	private TelnetProxy proxy;
	
	
	public RobotNavigator(TelnetProxy proxy) {
		super();
		this.proxy = proxy;
	}

    public Direction findDirection()
    {
    	RobotLocation rob = null;
    	try {
			rob = this.proxy.whereRobot();
		} catch (TaskException e) {
			e.printStackTrace();
		}

		double orientation = rob.getOrientation();
		if (orientation < 0) orientation = orientation + (2 * Math.PI);

		if (isEast(orientation))
		{
			return Direction.EAST;
		} else if (isNorthEast(orientation)) {
			return Direction.NORTHEAST;
		} else if (isNorth(orientation)) {
			return Direction.NORTH;
		} else if (isNorthWest(orientation)) {
			return Direction.NORTHWEST;
		} else if (isWest(orientation)) {
			return Direction.WEST;
		} else if (isSouthWest(orientation)) {
			return Direction.SOUTHWEST;
		} else if (isSouth(orientation)) {
			return Direction.SOUTH;
		} else if (isSouthEast(orientation)) {
			return Direction.SOUTHEAST;
		}
		return Direction.UNKNOWN;
    }

	private boolean isNorth(double orientation) {
		return Math.abs(orientation - Math.PI/2) < Math.PI/8.0;
	}

	private boolean isNorthEast(double orientation) {
		return Math.abs(orientation - Math.PI/4) < Math.PI/8.0;
	}

	private boolean isEast(double orientation) {
		return Math.abs(orientation - 0) < Math.PI/8.0 || 
				Math.abs(orientation - (2*Math.PI)) < Math.PI/8.0;
	}

	private boolean isSouthEast(double orientation) {
		return Math.abs(orientation - (7*Math.PI/4)) < Math.PI/8.0;
	}

	private boolean isSouth(double orientation) {
		return Math.abs(orientation - (3*Math.PI/2)) < Math.PI/8.0;
	}

	private boolean isSouthWest(double orientation) {
		return Math.abs(orientation - (5*Math.PI/4)) < Math.PI/8.0;
	}

	private boolean isWest(double orientation) {
		return Math.abs(orientation - Math.PI) < Math.PI/8.0;
	}

	private boolean isNorthWest(double orientation) {
		return Math.abs(orientation - (3*Math.PI/4)) < Math.PI/8.0;
	}
}
