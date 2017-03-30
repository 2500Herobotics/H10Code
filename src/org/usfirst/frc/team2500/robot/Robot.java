package org.usfirst.frc.team2500.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;

public class Robot extends IterativeRobot {
	public static GoodLuck luck = new GoodLuck();
	public static Begin begin = new Begin();
	public static TeleOp teleop = new TeleOp();
    public VisionThread visionThread;
//    public UsbCamera gearCam;
    public UsbCamera Cam0;
    public UsbCamera Cam1;
    
	/**
     * This function is called periodically during test mode
     */

    Command autonomousCommand;
    SendableChooser<Command> autoChooser;
    
	public void robotInit()
	{
		Cam0 = CameraServer.getInstance().startAutomaticCapture("cam0", 0);
		Cam1 = CameraServer.getInstance().startAutomaticCapture("cam1", 1);

		autoChooser = new SendableChooser<Command>();
		autoChooser.addDefault("No Auto", new Autonone());
		autoChooser.addObject("Time Left", new TimerLeft());
		autoChooser.addObject("Time Center", new TimerMid());
		autoChooser.addObject("Time Right", new TimerRight());
		autoChooser.addObject("Base Line", new TimerLine());
		SmartDashboard.putData("Auto mode chooser", autoChooser);
	}
	
	public void autonomousInit()
	{
		 System.out.println();
		 System.out.println();
		 System.out.println();
		 System.out.println(luck.Message());
		autonomousCommand = (Command) autoChooser.getSelected();
		autonomousCommand.start();
	}
	public void autonomousPeriodic(){
		Scheduler.getInstance().run();
	}
	
	public void teleopInit()
	{
		teleop.teleopInit();
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
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Good game. 6/9");
    }
}