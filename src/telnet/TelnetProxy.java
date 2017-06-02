package telnet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.apache.commons.net.telnet.TelnetClient;

import com.google.gson.Gson;

import model.LocationScape;
import model.RobotLocation;

public class TelnetProxy implements ITelnetProxy {

	private TelnetClient telnet;
	private InputStream in;
	private PrintStream out;

	private Gson gson = new Gson();
	private static final String prompt = "Enter command: ";
	 
	@Override
	public void connectToBot() throws TaskException {
	    // try to get server parameters
	    String telnetServer = "0.0.0.0";

	    int telnetPort;
	    
	        telnetPort = 55555;
	    

	    String telnetUsername = "";
	    String telnetPassword = "";

	    // Connect to the specified server
	    try {
	        telnet = new TelnetClient();

	        // connect
	        //LOG.debug("Connecting to server: " + telnetServer + " on port: " +
	               // telnetPort);
	        telnet.connect(telnetServer, telnetPort);

	        // Get input and output stream references
	        in = telnet.getInputStream();
	        out = new PrintStream(telnet.getOutputStream());

	        // Login the user
	       // LOG.debug("Logging in");
	//        String res = readUntil("MSE430 Server (CS 470 BYU)");
	       // write(telnetUsername);
	        //readUntil("Password: ");
	       // write(telnetPassword);
	  //      System.out.println(res);
	        // Advance to a prompt
	        //LOG.debug("Reading up to prompt.");
	//        readUntil(prompt);
	        //LOG.debug("Prompt found. Ready.");
	    } catch (IOException ioe) {
	        throw new TaskException(ioe);
	    }
	
	}

	  /**
     * Sends the command string to the CAI system. 
     */ 
    public String send(String command) throws TaskException { 
        try { 
            write(command); 
            String result ="";
            while(result.equals(""))
            	result =readUntil("\n");// read past echo  
           // String result = readUntil(prompt); 
            // drop trailing '\n' 
            return result;
        } catch (IOException e) { 
            throw new TaskException(e); 
        } 
    } 
 
    /**
     * Closes the connection. 
     */ 
    public void close() throws TaskException { 
        try { 
            telnet.disconnect(); 
        } catch (IOException ioe) { 
            throw new TaskException(ioe); 
        } 
    } 
 
    /**
     * Reads input stream until the given pattern is reached. The  
     * pattern is discarded and what was read up until the pattern is 
     * returned. 
     */ 
    private String readUntil(String pattern) throws IOException { 
        char lastChar = pattern.charAt(pattern.length() - 1); 
        StringBuilder sb = new StringBuilder(); 
        int c; 
 
        while((c = in.read()) != -1) { 
            char ch = (char) c; 
            //System.out.print(ch); 
            sb.append(ch); 
            if(ch == lastChar) { 
                String str = sb.toString(); 
                if(str.endsWith(pattern)) { 
                    return str.substring(0, str.length() -  
                            pattern.length()); 
                } 
            } 
        } 
 
        return null; 
    } 
 
    /**
     * Writes the value to the output stream. 
     */ 
    private void write(String value) { 
        out.println(value); 
        out.flush(); 
        //System.out.println(value); 
    } 
 
	
	@Override
	public void speed(int left, int right) throws TaskException {
		String cmd = "speed ";
		send(cmd.concat(String.valueOf(left).concat(" ").concat(String.valueOf(right))));

	}

	@Override
	public LocationScape where() throws TaskException {
		String cmd = "where";
		String json = send(cmd);
		json= json.replaceFirst("\"time\": \\d+(\\.\\d+)?,", "");
		json= json.replaceFirst(", \"time\": \\d+(\\.\\d+)?", "");
		return gson.fromJson(json, LocationScape.class);
	}

	@Override
	public RobotLocation whereRobot() throws TaskException {
		String cmd = "where robot";
		String json = send(cmd);
		
		return gson.fromJson(json, RobotLocation.class);
	}

	@Override
	public LocationScape whereOthers() throws TaskException {
		String cmd = "where others";
		String json = send(cmd);
		json= json.replaceFirst("\"time\": \\d+(\\.\\d+)?,", "");
		json= json.replaceFirst(", \"time\": \\d+(\\.\\d+)?", "");
		return gson.fromJson(json, LocationScape.class);
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

}
