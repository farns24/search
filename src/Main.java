import telnet.TaskException;
import telnet.TelnetProxy;
import model.LocationScape;
import model.Maze;


public class Main {
	public static void main(String[] args) {
		TelnetProxy proxy = new TelnetProxy();
		
		
		try {
			proxy.connectToBot();
		
		
		    Maze m = new Maze();
		    LocationScape location=proxy.where();;
		    m.initMaze(location, 3);
		    m.draw();
		} catch (TaskException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
