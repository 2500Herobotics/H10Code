package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends IterativeRobot {
	public static Begin begin = new Begin();
	public static TeleOp teleop = new TeleOp();
    /**
     * This function is called periodically during test mode
     */
	
	public void robotInit()
	{
		CameraServer server = CameraServer.getInstance();
		server.setQuality(50);
		server.startAutomaticCapture("cam0");
	}
	
	public void teleopInit()
	{
		//teleop = new TeleOp();
		//begin = new Begin();
		 begin.startup = 20;
		 begin.toggle = true;
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
    	begin.stick.setRumble(RumbleType.kRightRumble, 0);
    	begin.stick.setRumble(RumbleType.kLeftRumble, 0);
    }
    
    
    
}
