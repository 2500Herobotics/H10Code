package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.command.Command;


public class AutoLine extends Command {
    double speed = 0;
	double time1 = 110;
	double time2 = 150;
	
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
    	timer = 0;
    	end = false;
    }

    /**
     * This function is called periodically during autonomous
     */
    public void execute() {
    	timer++;
    	System.out.println(timer);
    	if(timer < time1){
    		begin.shift.set(false);
    		begin.drive.arcadeDrive(0, 0.75);
    	}
    	else if(timer < time2){
    		begin.drive.arcadeDrive(-0.5, 0);
    	}
    	else {
    		begin.drive.arcadeDrive(0, 0);
    		end = true;
    	}
    	
    	System.out.println();
    }
    
	@Override
	protected boolean isFinished() {
		return end;
	}
}