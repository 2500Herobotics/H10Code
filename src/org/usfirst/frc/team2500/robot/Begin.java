<<<<<<< HEAD
package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.Solenoid;
//import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
//import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;

public class Begin {
	
	int startup;
	boolean toggle;

	RobotDrive driveTrain1;
	RobotDrive driveTrain2;
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
    	driveTrain2 = new RobotDrive(2,5);
//    	gyro = new ADXRS450_Gyro();
//    	Climbing = new Spark(7);
    	stick1 = new Joystick(0);
    	sol1 = new Solenoid(2);
//    	sol2 = new Solenoid();
    	sol3 = new Solenoid(0);
    	sol4 = new Solenoid(1);
    	eCodeLeft = new Encoder(0, 1, false);
    	eCodeLeft.setDistancePerPulse(0.16);
    	eCodeRight = new Encoder(2, 3);
    }
    
    public void robotInit(){
    	
    }
=======
package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.Solenoid;
//import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
//import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DigitalOutput;

public class Begin {
	
	int startup;
	boolean toggle;
	
	DigitalOutput light1;

	RobotDrive driveTrain1;
	RobotDrive driveTrain2;
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
    	driveTrain2 = new RobotDrive(2,5);
//    	gyro = new ADXRS450_Gyro();
//    	Climbing = new Spark(7);
    	stick1 = new Joystick(0);
    	sol1 = new Solenoid(2);
//    	sol2 = new Solenoid();
    	sol3 = new Solenoid(0);
    	sol4 = new Solenoid(1);
    	eCodeLeft = new Encoder(0, 1, false);
    	eCodeLeft.setDistancePerPulse(0.16);
    	eCodeRight = new Encoder(2, 3);
    	light1 = new DigitalOutput(9);
    }
    
    public void robotInit(){
    	
    }
>>>>>>> 7fbc64c10d2342e427ce212644074d5b071f3dc8
}