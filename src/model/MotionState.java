package model;

import telnet.ITelnetProxy;

public abstract class MotionState {

	public abstract void run(ITelnetProxy proxy);
}
