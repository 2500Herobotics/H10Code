package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;


public class eCodeMid extends Command {
	double speed = 0;
	double target1 = 45;
//	double target1 = 180;
	double target2 = -10;
	
	int count;

	boolean reach1;
	boolean reach2;
	boolean reach3;
	boolean reach4;
	
	double k = -0.1;
	/**
     * This function is run once each time the robot enters autonomous mode
     */
	Begin begin;
	
    public void initialize() {
		begin = Robot.begin;
    	begin.eCodeLeft.reset();
    	begin.eCodeRight.reset();
//    	drive = new eCodeDrive();
    	reach1 = false;
    	reach2 = false;
    	reach3 = false;
    	reach4 = false;
    	count = 0;
    }

    /**
     * This function is called periodically during autonomous
     */
    public void execute() {
    	
//    	begin.light1.set(begin.sol1.get());
//		begin.light2.set(begin.sol1.get());
//    	begin.sol1.set(true);
//    	if(begin.eCodeLeft.getDistance() < 60){
//    		if(!reach1){
//        		if(begin.drive.driveDistance(target1, 0.5, 0.5)){
//            		reach1 = true;
//            		begin.drive.arcadeDrive(0, 0);
//            		begin.sol3.set(true);
//        		}
//        	}
//        	else if(!reach2) {
//        		if (count >= 20){
//            		if(begin.drive.driveDistance(target2, 1, 0.5)){
//            			reach2 = true;
//            			begin.drive.arcadeDrive(0, 0);
//            		}
//        		}
//        		else count++;
//        	}
//    		
//        	else{
//        		begin.drive.arcadeDrive(0, 0);
//        		reach3 = true;
//        	}
//    	}
//    	
//    	System.out.println("Left Distance: " + begin.eCodeLeft.getDistance());
//    	System.out.println("Right Distance: " + begin.eCodeRight.getDistance());
    }
    
	@Override
	protected boolean isFinished() {
		return reach3;
	}
}
