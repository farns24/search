package telnet;

import java.io.IOException;

public class TaskException extends Exception {

	public TaskException(IOException ioe) {
		super(ioe);
	}

}
