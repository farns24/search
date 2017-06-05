package model;

import telnet.TaskException;
import telnet.TelnetProxy;

public class RotateClockwiseCommand{

	public void moveRobot(TelnetProxy proxy, int multiplier) {
		
		try {
			proxy.speed(-5, 5);
			System.out.println("Move");
			 try {
				Thread.sleep(500*multiplier);
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
