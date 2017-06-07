package model;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import telnet.TaskException;
import telnet.TelnetProxy;

public class ForwardCommand implements IRoboInstruction {

	@Override
	public void moveRobot(TelnetProxy proxy) {
		
		try {
			proxy.speed(5, 5);
			System.out.println("Move");
			 try {
				Thread.sleep(1800);
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
