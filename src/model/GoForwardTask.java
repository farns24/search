package model;

import telnet.ITelnetProxy;
import telnet.TaskException;

public class GoForwardTask extends MotionState {

	@Override
	public void run(ITelnetProxy proxy) {

		try {
			proxy.speed(7, 7);
		} catch (TaskException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
