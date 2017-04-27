package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.command.Command;


public class AutoRight extends Command {
    double speed = 0;
	double gTime = 35;

	boolean reach1 = false;
	boolean reach2 = false;
	boolean reach3 = false;
	boolean reach4 = false;
	boolean reach5 = false;
	boolean reach6 = false;
	
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
    	reach1 = false;
    	reach2 = false;
    	reach3 = false;
    	reach4 = false;
    	reach5 = false;
    	reach6 = false;
    }

    /**
     * This function is called periodically during autonomous
     */
    public void execute() {
    	System.out.println("Timer: " + timer);
    	System.out.println("Gyro: " + begin.gyro.getAngle());
    	if(begin.drive.driveDistance(93.5, 1, 1) || reach1){
    		begin.jaw.set(true);
    	}
    	else if(timer < gTime || reach2){
    		reach1 = true;
        	timer++;
    		begin.drive.arcadeDrive(0, (-45 + begin.gyro.getAngle())/30);
    		begin.eCodeLeft.reset();
    		begin.eCodeRight.reset();
    	}
    	else if(begin.drive.driveDistance(71, 1, 1) || reach3){
    		reach2 = true;
    		timer = 0;
    	}
    	else if(timer < gTime || reach4){
    		reach3 = true;
        	timer++;
    		begin.jaw.set(false);
    	}
    	else if(begin.drive.driveDistance(0, 1, 1)){
    		reach4 = true;
    		timer = 0;
    	}
    	else if(timer < gTime || reach5){
    		reach4 = true;
        	timer++;
    		begin.drive.arcadeDrive(0, begin.gyro.getAngle()/30);
    		begin.eCodeLeft.reset();
    		begin.eCodeRight.reset();
    	}
    	else if(begin.drive.driveDistance(285.5, 1, 1) || reach6){
    		reach5 = true;
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