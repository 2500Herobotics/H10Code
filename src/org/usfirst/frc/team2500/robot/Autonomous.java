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
	    
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	double error = target - begin.eCodeLeft.getDistance();
    	System.out.println("Encoder: " + begin.eCodeLeft.getDistance());
    	speed = error * k;
    	if(error > 0.25){
    		begin.driveTrain1.drive(speed, 0);
    		begin.driveTrain2.drive(speed, 0);
    	}
    	else{
    		begin.driveTrain1.drive(speed, 0);
    		begin.driveTrain2.drive(speed, 0);
    	}
    	
    }
}
