package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.command.Command;


public class TimerMid extends Command {
    double distance_1 = 86;
    double distance_2 = 50;
    double time = 40;
    double speed = 0;
	double time1 = 230;
	double time2 = 260;
	double time3 = 380;
	
	boolean end = false;
	boolean forward = true;
	
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
    	forward = true;
    }

    /**
     * This function is called periodically during autonomous
     */
    public void execute() {
    	System.out.println("Gyro: " + begin.gyro.getAngle());
    	System.out.println("Left eCode: " + begin.eCodeLeft.getDistance());
    	System.out.println("Right eCode: " + begin.eCodeRight.getDistance());
    	if((begin.eCodeLeft.getDistance() < distance_1) && forward){
    		begin.drive.arcadeDrive(-1*begin.gyro.getAngle()/10, 0.5);
    		begin.jaw.set(false);
    	}
    	else if(timer < time){
    		forward = false;
        	timer++;
    		begin.drive.arcadeDrive(0, 0);
    		begin.jaw.set(true);
    	}
    	else if (begin.eCodeLeft.getDistance() > distance_2){
    		begin.drive.arcadeDrive(0, -0.5);
    	}
    	else {
    		begin.drive.arcadeDrive(0, 0);
    	}
//    	else if(timer < time3){
//    		begin.drive.arcadeDrive(0, -0.5);
//    	}
//    	else{
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