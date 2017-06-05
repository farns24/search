package model;

import telnet.ITelnetProxy;
import telnet.TaskException;

public class GoForwardTask extends MotionState {

	@Override
	public void run(ITelnetProxy proxy) {

		try {
			proxy.speed(4, 4);
			try {
				Thread.sleep(500);
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
