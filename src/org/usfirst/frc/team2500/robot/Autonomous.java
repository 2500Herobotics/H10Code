package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Talon;


public class Autonomous extends IterativeRobot {
    double speed = 0;
	double target = 80;
	double k = -0.1;
	/**
     * This function is run once each time the robot enters autonomous mode
     */
	Begin begin;
	
    public void autonomousInit() {
		begin = Robot.begin;
    	begin.eCodeLeft.reset();
//    	drive = new eCodeDrive();
	    
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	
//    	if()
    	begin.drive.driveDistance(target, 1, 0.5);
    	
    	System.out.println("Left Distance: " + begin.eCodeLeft.getDistance());
//    	System.out.println("Right Distance: " + begin.eCodeLeft.getDistance());
    }
}
