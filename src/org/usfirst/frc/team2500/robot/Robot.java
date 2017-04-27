package org.usfirst.frc.team2500.robot;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    
//    public PrintWriter writer;
    
//    public UsbCamera gearCam;
    public UsbCamera Cam0;
    
    
	/**
     * This function is called periodically during test mode
     */

    Command autonomousCommand;
    SendableChooser<Command> autoChooser;
    
	public void robotInit()
	{
		//setting camera ports
		Cam0 = CameraServer.getInstance().startAutomaticCapture("cam0", 0);

		//setting Smartdashboard auto buttons
		autoChooser = new SendableChooser<Command>();
		autoChooser.addObject("Base Line", new TimerLine());
		autoChooser.addObject("Left", new TimerLeft());
		autoChooser.addObject("Mid", new TimerMid());
		autoChooser.addObject("Right", new TimerRight());
		autoChooser.addObject("Temp", new autoTemp());
		SmartDashboard.putData("Auto mode chooser", autoChooser);
		
//	    try{
//	    	writer = new PrintWriter("test.txt", "UTF-8");
//	    	writer.println("Stick X" +"\t" + "Stick Y" + "\t" + "Move Value" + "\t" + "Turn Value" + "\t" + "Left Rate" + "\t" + "Right Rate");
//	    	writer.println(begin.stick.getRawAxis(0) +"\t" + begin.stick.getRawAxis(1) + "\t" + teleop.mov_value + "\t" + teleop.turning_value + "\t" + begin.eCodeLeft.getRate() + "\t" + begin.eCodeRight.getRate());
//	    }
//	    catch(IOException e){
//	    	System.out.print("The file isnt printing");
//	    }
	}
	
	public void autonomousInit()
	{
		//Printing out message when auto starts
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(luck.Message());
		autonomousCommand = (Command) autoChooser.getSelected();
		autonomousCommand.start();
    	begin.eCodeLeft.reset();
    	begin.eCodeRight.reset();
    	begin.gyro.reset();
	}
	public void autonomousPeriodic(){
		//run selected auto 
		Scheduler.getInstance().run();
	}
	
	public void teleopInit()
	{
		teleop.teleopInit();
	}
	
	public void teleopPeriodic()
	{
		teleop.teleopPeriodic();
//    	writer.println(begin.stick.getRawAxis(0) +"\t" + begin.stick.getRawAxis(1) + "\t" + teleop.mov_value + "\t" + teleop.turning_value + "\t" + begin.eCodeLeft.getRate() + "\t" + begin.eCodeRight.getRate());
	}
	
    public void testPeriodic()
    {
    	LiveWindow.run();
    }
    
    public void disabledInit() 
    {
//    	writer.close();
    	//Printing out message when game ends
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Good job. Now meet me back here by the egg.");
    }
}