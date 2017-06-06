package test;

import model.*;

import org.junit.Before;
import org.junit.Test;

import telnet.TelnetProxy;

public class IRoboInstructionTest {
	
	TelnetProxy proxy;

	@Before
	public void setUp() throws Exception {
		proxy = new TelnetProxy();
		proxy.connectToBot();
	}

	@Test
	public void test() {
		IRoboInstruction inst = new MoveNorthEastCommand();
		inst.moveRobot(proxy);
	}

}
