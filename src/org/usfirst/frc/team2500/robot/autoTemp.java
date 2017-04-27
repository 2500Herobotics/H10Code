package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.command.Command;


public class autoTemp extends Command {
	int timer = 0;
	
	boolean end = false;
	
	/**
     * This function is run once each time the robot enters autonomous mode
     */
	Begin begin;
	
    public void initialize() {
		begin = Robot.begin;
    	begin.eCodeLeft.reset();
    	begin.eCodeRight.reset();
    	end = false;
    }

    /**
     * This function is called periodically during autonomous
     */
    public void execute() {
    	if(!begin.drive.driveDistance(100, 1)){
    	}
    	else{
    		begin.drive.arcadeDrive(0, 0);
    		end = true;
    	}
    }
    
	@Override
	protected boolean isFinished() {
		return end;
	}
}