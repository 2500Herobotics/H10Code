package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Talon;


public class AutoMid extends IterativeRobot {
	double speed = 0;
	double target1 = 180;

	boolean reach1;
	
	double k = -0.1;
	/**
     * This function is run once each time the robot enters autonomous mode
     */
	Begin begin;
	
    public void autonomousInit() {
		begin = Robot.begin;
    	begin.eCodeLeft.reset();
//    	drive = new eCodeDrive();
    	reach1 = false;
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	
    	if(!reach1){
    		if(begin.drive.driveDistance(target1, 1, 0.5)){
        		reach1 = true;
    		}
    	}
    	else{
    		begin.drive.arcadeDrive(0, 0);
    	}
    	
    	System.out.println("Left Distance: " + begin.eCodeLeft.getDistance());
    	System.out.println("Right Distance: " + begin.eCodeRight.getDistance());
    }
}
