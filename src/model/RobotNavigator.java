package model;

import telnet.TaskException;
import telnet.TelnetProxy;

public class RobotNavigator {

	private TelnetProxy proxy;
	
	
	public RobotNavigator(TelnetProxy proxy) {
		super();
		this.proxy = proxy;
	}

    private void findDirection()
    {
    	try {
			RobotLocation rob = this.proxy.whereRobot();
			double orientation = rob.getOrientation();
			
			if (isNorth(orientation))
			{
				System.out.println("Facing north");
			}
			
		} catch (TaskException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	
	private boolean isNorth(double orientation) {
		
		return Math.abs(orientation +1.0 * Math.PI/2.0)< Math.PI/8.0;
	}

	public void move(IRoboInstruction instruction)
	{
		findDirection();
	}
}
