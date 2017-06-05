package model;

import telnet.TelnetProxy;

/**
 * Created by michael on 6/5/17.
 */
public interface IRotateCommand {
    int miliseconds = 500;
    public void moveRobot(TelnetProxy proxy, int multiplier);
}
