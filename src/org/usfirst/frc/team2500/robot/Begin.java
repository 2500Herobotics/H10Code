package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.Solenoid;
//import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
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

//	Talon talon_left1;
//	Talon talon_right1;
//	Talon talon_left2;
//	Talon talon_right2;
//	Talon talon_left3;
//	Talon talon_right3;
	
	Talon climber;

	RobotDrive drive;
	
//	eCodeDrive drive;
	
	Joystick stick1;
	Joystick stick2;
	Solenoid sol1;
	Solenoid sol2;
	Solenoid sol3;
	Encoder eCodeLeft;
	Encoder eCodeRight;
	
//	int max_speed;
	
//	Spark Climbing;
//	ADXRS450_Gyro gyro;
	
	public static int autoLoopCounter;

    public double centerX = 0.0;
	
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public Begin() {    	
//    	gyro = new ADXRS450_Gyro();
//    	Climbing = new Spark(7);
    	stick1 = new Joystick(0);
    	sol1 = new Solenoid(0);
    	sol2 = new Solenoid(1);
    	sol3 = new Solenoid(2);
    	
    	eCodeLeft = new Encoder(0, 1, true);
    	eCodeLeft.setDistancePerPulse(0.15514);
//    	eCodeLeft.setDistancePerPulse(1);
    	eCodeRight = new Encoder(2, 3, false);
//    	eCodeRight.setDistancePerPulse(1);
    	eCodeRight.setDistancePerPulse(0.37889);

//    	talon_left1 = new Talon(0);
//    	talon_left2 = new Talon(1);
//    	talon_left3 = new Talon(6);
//    	
//    	talon_right1 = new Talon(2);
//    	talon_right2 = new Talon(3);
//    	talon_right3 = new Talon(5);
    	
    	climber = new Talon(4);
    	drive = new RobotDrive(0,1,2,3);
//    	drive = new eCodeDrive(talon_left1, talon_left2, talon_left3, talon_right1, talon_right2, talon_right3, eCodeLeft, eCodeRight);
    	
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