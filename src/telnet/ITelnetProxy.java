package telnet;

import model.LocationScape;
import model.RobotLocation;

/**
 * Telnet Proxy commands
 * @author wolfa
 *
 */
public interface ITelnetProxy {
	public void connectToBot() throws TaskException;
	
	public void speed(int left,int right) throws TaskException ;
	
	public LocationScape where() throws TaskException;
	
	public RobotLocation whereRobot() throws TaskException;
	
	public LocationScape whereOthers() throws TaskException;
	
	public void shutdown() throws TaskException;
}
