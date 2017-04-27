package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.Solenoid;
//import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class Begin {
	
	Talon climber;

	Talon talon_left1;
	Talon talon_right1;
	Talon talon_left2;
	Talon talon_right2;
	Talon talon_left3;
	Talon talon_right3;

	Timer rampTimer;
	Timer shiftTimer;
	
	eCodeDrive drive;
//	RobotDrive drive;
	
	Joystick stick;
	Joystick stick2;
	
	Solenoid jaw;
	Solenoid shift;
	Solenoid Break;
	Solenoid hopper;
	
	Encoder eCodeLeft;
	Encoder eCodeRight;
	
	ADXRS450_Gyro gyro;
	
	public static int autoLoopCounter;

    public double centerX = 0.0;
	
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    
    public Begin() {    	
    	gyro = new ADXRS450_Gyro();
    	
    	//Joysticks
    	stick = new Joystick(0);
    	stick2 = new Joystick(1);
    	
    	//Solenoids
    	jaw = new Solenoid(0);
    	shift = new Solenoid(2);
    	Break = new Solenoid(1);
    	hopper = new Solenoid(3);
    	
    	//Encoders
    	eCodeLeft = new Encoder(0, 1, true);
    	eCodeLeft.setDistancePerPulse(0.163265);
//    	eCodeLeft.setDistancePerPulse(1/(6*Math.PI));
    	eCodeRight = new Encoder(2, 3, false);
    	eCodeRight.setDistancePerPulse(0.163265);
//    	eCodeRight.setDistancePerPulse(0.15514);

    	//Drive train Motors
    	talon_left1 = new Talon(0);
    	talon_left2 = new Talon(1);
    	talon_left3 = new Talon(2);
    	
    	talon_right1 = new Talon(3);
    	talon_right2 = new Talon(4);
    	talon_right3 = new Talon(5);
    	
    	//Ecode drive class
    	drive = new eCodeDrive(talon_left1, talon_left2, talon_left3, talon_right1, talon_right2, talon_right3, eCodeLeft, eCodeRight);
//    	drive = new RobotDrive(0, 1);
    	
    	//Climbing motor
    	climber = new Talon(6);
    	
    	rampTimer = new Timer();
    	shiftTimer = new Timer();
    }
    
    public void robotInit(){
    	
    }
}