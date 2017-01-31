package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.vision.VisionThread;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;

public class Begin {
	
	int startup;
	boolean toggle;
	
	RobotDrive driveTrain;
	Joystick stick1;
	Joystick stick2;
	Solenoid sol1;
	Solenoid sol2;
	Solenoid sol3;
	Solenoid sol4;
	Spark Climbing;
	ADXRS450_Gyro gyro;
	Encoder eCodeLeft;
	Encoder eCodeRight;
	public static int autoLoopCounter;

    public VisionThread visionThread;
    public UsbCamera gearCam;
    public UsbCamera driveCam;
    private final Object imgLock = new Object();
    public DriverStation station;

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
    	sol1 = new Solenoid(0);
    	sol2 = new Solenoid(1);
    	sol3 = new Solenoid(2);
    	sol4 = new Solenoid(3);
    	eCodeLeft = new Encoder(0, 1);
    	eCodeRight = new Encoder(2, 3);
    	//camera setup
	    gearCam = CameraServer.getInstance().startAutomaticCapture("cam0", 0);
	    
	    driveCam = CameraServer.getInstance().startAutomaticCapture("cam1", 1);

	    visionThread = new VisionThread(driveCam, new Vision(), pipeline -> {
	        if (!pipeline.filterContoursOutput().isEmpty()) {
	            Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
	            synchronized (imgLock) {
	                centerX = r.x + (r.width / 2);
	            }
	        }
	    });
	    visionThread.start();
    }
    
    public void robotInit(){
    	
    }
}