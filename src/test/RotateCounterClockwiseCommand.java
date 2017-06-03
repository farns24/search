package test;

import telnet.TaskException;
import telnet.TelnetProxy;
import model.IRoboInstruction;

public class RotateCounterClockwiseCommand implements IRoboInstruction {

	@Override
	public void moveRobot(TelnetProxy proxy) {
		
		try {
			proxy.speed(5,0);
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
