package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.command.Command;


public class TimerRight extends Command {
    double speed = 0;
	double time1 = 35;
	double time2 = 40;
	double time3 = 65;
	double time4 = 130;
	double time5 = 160;
	
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
    		begin.drive.arcadeDrive(1, 0);
    		begin.jaw.set(true);
    	}
    	else if(timer < time2){
    		begin.drive.arcadeDrive(0, -1);
    	}
    	else if(timer < time3){
    		begin.drive.arcadeDrive(1, 0);
    	}
    	else if(timer < time4){
    		begin.drive.arcadeDrive(0, 0);
    	}
    	else if(timer < time5){
    		begin.drive.arcadeDrive(-75, 0);
    	}
    	else
    	{
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