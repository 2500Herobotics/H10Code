package org.usfirst.frc.team2500.robot;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.vision.VisionThread;
import edu.wpi.first.wpilibj.DriverStation;

public class Robot extends IterativeRobot {
	public static GoodLuck luck = new GoodLuck();
	public static Begin begin = new Begin();
	public static Autonomous autonomous = new Autonomous();
	public static TeleOp teleop = new TeleOp();
//	public static Vision vision = new Vision(); 
//    public VisionThread visionThread;
//    public UsbCamera gearCam;
//    public UsbCamera driveCam;
    
    private final Object imgLock = new Object();
	/**
     * This function is called periodically during test mode
     */
	
	public void robotInit()
	{
//	    gearCam = CameraServer.getInstance().startAutomaticCapture("cam0", 0);   
//
//	    driveCam = CameraServer.getInstance().startAutomaticCapture("cam1", 1);
//	    
//
//	    visionThread = new VisionThread(driveCam, new Vision(), pipeline -> {
//	        if (!pipeline.filterContoursOutput().isEmpty()) {
//	            Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
//	            synchronized (imgLock) {
//	                begin.centerX = r.x + (r.width / 2);
//	            }
//	        }
//	    });
//	    visionThread.start();

	}
	
	public void autonomousInit(){
		autonomous.autonomousInit();
		
	}
	public void autonomousPeriodic(){
		autonomous.autonomousPeriodic();
	}
	
	public void teleopInit()
	{
		System.out.println();
		System.out.println();
		 System.out.println();
		 System.out.println(luck.Message());
		 begin.startup = 20;
		 begin.toggle = true;
	}
	
	public void teleopPeriodic() 
	{
		teleop.teleopPeriodic();
//	    System.out.println(begin.centerX);
	}
	
    public void testPeriodic() 
    {
    	LiveWindow.run();
    }
    
    public void disabledInit() 
    {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Good game guys tell me if you want any changes when you get back to the pit.");
    }
}