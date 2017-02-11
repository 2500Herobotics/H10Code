package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.Solenoid;
//import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
//import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Talon;

public class Begin {
	
	int startup;
	boolean toggle;

	DigitalOutput light1;
	DigitalOutput light2;
	DigitalOutput light3;
	DigitalOutput light4;
	DigitalOutput light5;
	DigitalOutput light6;

	Talon talon_left;
	Talon talon_right;
	
	RobotDrive driveTrain1;
	RobotDrive driveTrain2;
	eCodeDrive drive;
	Joystick stick1;
	Joystick stick2;
	Solenoid sol1;
	Solenoid sol2;
	Solenoid sol3;
	Solenoid sol4;
	Encoder eCodeLeft;
	Encoder eCodeRight;
	
//	Spark Climbing;
//	ADXRS450_Gyro gyro;
	
	public static int autoLoopCounter;

    public double centerX = 0.0;
	
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public Begin() {

    	driveTrain1 = new RobotDrive(0,1,4,3);
    	
//    	gyro = new ADXRS450_Gyro();
//    	Climbing = new Spark(7);
    	stick1 = new Joystick(0);
    	sol1 = new Solenoid(0);//2
    	sol3 = new Solenoid(1);//0
    	sol4 = new Solenoid(2);//1
    	
    	eCodeLeft = new Encoder(0, 1, false);
    	eCodeLeft.setDistancePerPulse(0.16);
    	eCodeRight = new Encoder(2, 3, true);
    	eCodeRight.setDistancePerPulse(0.16);
    	
    	talon_left = new Talon(2);
    	talon_right = new Talon(5);
    	drive = new eCodeDrive(talon_left, talon_right, eCodeLeft, eCodeRight);
    	
    	light1 = new DigitalOutput(4);
    	light2 = new DigitalOutput(5);
    	light3 = new DigitalOutput(6);
    	light4 = new DigitalOutput(7);
    	light5 = new DigitalOutput(8);
    	light6 = new DigitalOutput(9);
    }
    
    public void robotInit(){
    	
    }
}