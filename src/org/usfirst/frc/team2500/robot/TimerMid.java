package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.command.Command;


public class TimerMid extends Command {
    double speed = 0;
	double time1 = 260;
	double time2 = 320;
	double time3 = 400;
	
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
    		begin.drive.arcadeDrive(0, 0.5);
    		begin.jaw.set(false);
    	}
    	else if(timer < time2){
    		begin.drive.arcadeDrive(0, 0);
    		begin.jaw.set(true);
    	}
    	else if(timer < time3){
    		begin.drive.arcadeDrive(0, -0.5);
    	}
    	else{
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