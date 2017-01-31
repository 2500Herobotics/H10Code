package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Talon;


public class Autonomous extends IterativeRobot {
    /**
     * This function is run once each time the robot enters autonomous mode
     */
	Begin begin = Robot.begin;
	Talon gearbox1;
	Talon gearbox2;
	Talon gearbox3;
	double speed = 0;
	double mult = 1; 
	
    public void autonomousInit() {
    	Begin.autoLoopCounter = 0;
	    gearbox1 = new Talon(3);
	    gearbox2 = new Talon(4);
	    gearbox3 = new Talon(5);
	    
	    
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	if (Math.abs(speed) >= 1){
    		speed = 0;
    		mult *= -1;
    	}
    	gearbox1.set(speed);
    	gearbox2.set(speed);
    	gearbox3.set(speed);
    	speed += 0.01 * mult;
    	
    	
    }
}
