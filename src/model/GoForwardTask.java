package model;

import telnet.ITelnetProxy;
import telnet.TaskException;

public class GoForwardTask extends MotionState {

	@Override
	public void run(ITelnetProxy proxy) {

		try {
			proxy.speed(4, 4);
		} catch (TaskException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
