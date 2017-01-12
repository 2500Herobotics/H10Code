package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

public class Begin {
	
	int startup;
	boolean toggle;
	
	RobotDrive myRobot;
	Joystick stick;
	Solenoid sol1;
	public static int autoLoopCounter;
	
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public Begin() {
    	myRobot = new RobotDrive(8,9,7,6);
    	stick = new Joystick(0);
    	sol1 = new Solenoid(1);
    }
}