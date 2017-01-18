package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.IterativeRobot;


public class Autonomous extends IterativeRobot {
    /**
     * This function is run once each time the robot enters autonomous mode
     */
	Begin begin = Robot.begin;
	
    public void autonomousInit() {
    	begin.autoLoopCounter = 0;
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	if(begin.autoLoopCounter < 100) //Check if we've completed 100 loops (approximately 2 seconds)
		{
			begin.driveTrain.drive(-0.5, 0.0); 	// drive forwards half speed
			begin.autoLoopCounter++;
			} 
    	else 
    	{
			begin.driveTrain.drive(0.0, 0.0); 	// stop robot
			
		}
    }
}
