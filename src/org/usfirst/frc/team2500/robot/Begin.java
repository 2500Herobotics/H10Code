package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CameraServer;

public class Begin {
	
	int startup;
	boolean toggle;
	
	RobotDrive driveTrain;
	Joystick stick1;
	Joystick stick2;
	Solenoid sol1;
	Solenoid sol2;
	Spark Climbing;
	ADXRS450_Gyro gyro;
	public static int autoLoopCounter;

    public double centerX = 0.0;
	
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public Begin() {
    	driveTrain = new RobotDrive(0,1); //0,1
    	gyro = new ADXRS450_Gyro();
    	Climbing = new Spark(7);
    	stick1 = new Joystick(0);
//    	stick2 = new Joystick(1);
    	sol1 = new Solenoid(0);
    	sol2 = new Solenoid(1);
    }
    
    public void robotInit(){
    	
    }
}