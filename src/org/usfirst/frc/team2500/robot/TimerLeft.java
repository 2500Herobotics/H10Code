package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;


public class TimerLeft extends Command {
    double speed = 0;
	double time1 = 90;
	double time2 = 180;
	double time3 = 360;
	double time4 = 450;
	
	boolean end = false;
	
	int timer = 0;
	/**
     * This function is run once each time the robot enters autonomous mode
     */
	Begin begin;
	
    public void initialize() {
		begin = Robot.begin;
    	begin.eCodeLeft.reset();
    	begin.eCodeRight.reset();
//    	drive = new eCodeDrive();
    	timer = 0;
    }

    /**
     * This function is called periodically during autonomous
     */
    public void execute() {
    	timer++;
    	
//    	if(timer < time1){
//    		begin.drive.arcadeDrive(1, 1);
//    	}
//    	else if(timer < time2){
//    		begin.drive.arcadeDrive(-1, 1);
//    	}
//    	else if(timer < time3){
//    		begin.drive.arcadeDrive(1, 1);	
//    	}
//    	else if(timer < time4){
//    		begin.drive.arcadeDrive(0, 0);
//    		end = true;
//    	}
    	
    	System.out.println();
    }
    
	@Override
	protected boolean isFinished() {
		return end;
	}
}