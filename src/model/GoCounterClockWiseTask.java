package model;

import telnet.ITelnetProxy;
import telnet.TaskException;

public class GoCounterClockWiseTask extends MotionState {

	private int angularVelocity;
	private int diff;
	
	public GoCounterClockWiseTask(double diff) {
		this.diff = (int) diff *100;
	}


	@Override
	public void run(ITelnetProxy proxy) {
		try {
			Thread.sleep(diff * angularVelocity);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			proxy.speed(5, 0);
		} catch (TaskException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
