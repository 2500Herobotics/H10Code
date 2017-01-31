package org.usfirst.frc.team2500.robot;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.UsbCamera;

//import org.opencv.core.Rect;
//import org.opencv.imgproc.Imgproc;
//import edu.wpi.cscore.UsbCamera;
//import edu.wpi.first.wpilibj.CameraServer;
//import edu.wpi.first.wpilibj.IterativeRobot;
//import edu.wpi.first.wpilibj.RobotDrive;
//import edu.wpi.first.wpilibj.vision.VisionRunner;
//import edu.wpi.first.wpilibj.vision.VisionThread;
//public class Robot extends IterativeRobot {
//	
//	private static final int IMG_WIDTH = 320;
//	private static final int IMG_HEIGHT = 240;
//	
//	private VisionThread visionThread;
//	private double centerX = 0.0;
//	private RobotDrive drive;
//	
//	private final Object imgLock = new Object();
//	
//	@Override
//	public void robotInit() {
//	    UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
//	    camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
//	    
//	    visionThread = new VisionThread(camera, new Vision(), pipeline -> {
//	        if (!pipeline.filterContoursOutput().isEmpty()) {
//	            Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
//	            synchronized (imgLock) {
//	                centerX = r.x + (r.width / 2);
//	            }
//	        }
//	    });
//	    visionThread.start();
//	        
//	    drive = new RobotDrive(1, 2);
//	}
//}

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.vision.VisionThread;
import edu.wpi.first.wpilibj.DriverStation;

public class Robot extends IterativeRobot {
	public static GoodLuck luck = new GoodLuck();
	public static Begin begin = new Begin();
	public static Autonomous autonomous = new Autonomous();
	public static TeleOp teleop = new TeleOp();
	public static Vision vision = new Vision(); 
    public VisionThread visionThread;
    public UsbCamera gearCam;
    public UsbCamera driveCam;
    public DriverStation station;
    
    private final Object imgLock = new Object();
	/**
     * This function is called periodically during test mode
     */
	
	public void robotInit()
	{
	    gearCam = CameraServer.getInstance().startAutomaticCapture("cam0", 0);
//	    driveCam.setResolution(380,420);
	    

	    driveCam = CameraServer.getInstance().startAutomaticCapture("cam1", 1);
//	    driveCam.setResolution(380,420);
	    

	    visionThread = new VisionThread(driveCam, new Vision(), pipeline -> {
	        if (!pipeline.filterContoursOutput().isEmpty()) {
	            Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
	            synchronized (imgLock) {
	                begin.centerX = r.x + (r.width / 2);
	            }
	        }
	    });
	    visionThread.start();

	}
	
	public void autonomousInit(){
		System.out.println(luck.Message());
	}
	
	public void autonomousPeriodic(){
		autonomous.autonomousPeriodic();
	}
	
	public void teleopInit()
	{
		 begin.startup = 20;
		 begin.toggle = true;
	     System.out.println(begin.centerX);
	}
	
	public void teleopPeriodic() 
	{
		teleop.teleopPeriodic();
	}
	
    public void testPeriodic() 
    {
    	LiveWindow.run();
    }
    
    public void disabledInit() 
    {
		System.out.println("Good game guys tell me if you want any changes when you get back to the pit.");
    }
}

