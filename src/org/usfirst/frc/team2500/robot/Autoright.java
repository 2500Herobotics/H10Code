package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Talon;


public class Autoright extends IterativeRobot {
	
    double speed = 0;
	double target1 = 90;
	double target2L = 180;
	double target2R = 0;
	double target3L = 360;
	double target3R = 180;
	
	boolean reach1 = false;
	boolean reach2 = false;
	boolean reach3 = false;
	
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
    	
    	if(!reach1){
    		if(begin.drive.driveDistance(target1, 1, 0.5)){
        		reach1 = true;
    		}
    	}
    	else if(!reach2) {
    		if(begin.drive.driveDistance(target2L, target2R, 1, 0.5) || reach2){
        		reach2 = true;
    		}
    	}
    	else if(!reach3) {
    		if(begin.drive.driveDistance(target3L, target3R, 1, 0.5) || reach3){
        		reach3 = true;
    		}
    	}
    	else{
    		begin.drive.arcadeDrive(0, 0);
    	}
    	
    	System.out.println("Left Distance: " + begin.eCodeLeft.getDistance());
    	System.out.println("Right Distance: " + begin.eCodeRight.getDistance());
    }
}
