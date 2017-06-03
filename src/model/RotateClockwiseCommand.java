package model;

import telnet.TaskException;
import telnet.TelnetProxy;

public class RotateClockwiseCommand implements IRoboInstruction{

	@Override
	public void moveRobot(TelnetProxy proxy) {
		
		try {
			proxy.speed(0, 5);
			System.out.println("Move");
			 try {
				Thread.sleep(2000);
				proxy.speed(0, 0);
				System.out.println("Time up");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		} catch (TaskException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
