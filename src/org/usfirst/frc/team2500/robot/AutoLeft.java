package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.command.Command;


public class AutoLeft extends Command {
    double speed = 0;
	double gTime = 35;

	boolean reach1 = false;
	boolean reach2 = false;
	boolean reach3 = false;
	boolean reach4 = false;
	boolean reach5 = false;
	boolean reach6 = false;
	boolean reach7 = false;
	boolean reach8 = false;
	boolean reach9 = false;
	
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
		begin.gyro.reset();

		reach1 = false;
		reach2 = false;
		reach3 = false;
		reach4 = false;		
		reach5 = false;
		reach6 = false;
		reach7 = false;
		reach8 = false;
		reach9 = false;
    }

    /**
     * This function is called periodically during autonomous
     */
    public void execute() {
    	System.out.println("Timer: " + timer);
    	System.out.println("Gyro: " + begin.gyro.getAngle());
    	if(reach1 || !begin.drive.driveDistance(93.5, 1)){
    		begin.jaw.set(true);
    	}
    	else if(reach2 || timer < gTime){
    		reach1 = true;
        	timer++;
    		begin.drive.arcadeDrive((45 + begin.gyro.getAngle())/30, 0);
    		begin.eCodeLeft.reset();
    		begin.eCodeRight.reset();
    	}
    	else if(reach3 || !begin.drive.driveDistance(71, 1)){
    		reach2 = true;
    		timer = 0;
    	}
    	else if(reach4 || timer < gTime){
    		reach3 = true;
        	timer++;
    		begin.jaw.set(false);
    	}
    	else if(reach5 || !begin.drive.driveDistance(0, 1)){
    		reach4 = true;
    		timer = 0;
    	}
    	else if(reach6 || timer < gTime){
    		reach5 = true;
        	timer++;
    		begin.drive.arcadeDrive(begin.gyro.getAngle()/30, 0);
    		begin.eCodeLeft.reset();
    		begin.eCodeRight.reset();
    	}
    	else if(reach7 || !begin.drive.driveDistance(93.5, 1)){
    		reach6 = true;
    		timer = 0;
    	}
    	else if(reach8 || timer < gTime){
    		reach7 = true;
    		begin.drive.arcadeDrive((45 + begin.gyro.getAngle())/30, 0);
    		begin.eCodeLeft.reset();
    		begin.eCodeRight.reset();
    	}
    	else if(reach9 || !begin.drive.driveDistance(240, 1, 1)){
    		reach8 = true;
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