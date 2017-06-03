package model;

import java.util.TimerTask;

import telnet.ITelnetProxy;
import telnet.TaskException;

public class GoClockWiseTask extends MotionState {

	private int angularVelocity;
	private int diff;

	public GoClockWiseTask(double diff) {
		this.diff = (int) diff* 100;
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
			proxy.speed(0, 5);
		} catch (TaskException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
