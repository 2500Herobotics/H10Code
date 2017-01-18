package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.buttons.Button;


public class TeleOp extends IterativeRobot{
	Begin begin;
	
	//setting all the button
	boolean[] previous_button_states;
	boolean[] button_toggles;
	
    /**
     * This function is called once each time the robot enters tele-operated mode
     */
	
	//advanced pls ignore till line 26
    public TeleOp(){
    	begin = Robot.begin;
    	previous_button_states = new boolean[6];
    	button_toggles = new boolean[6];
    	for(int i = 0; i < 6; i++){
    		button_toggles[i] = false;
    	}
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() 
    {
    	/*
    	Button Get Raw Number Codes
    	
    	1 = A
    	2 = B
    	3 = X
    	4 = Y
    	5 = LB
    	6 = RB
    	*/
    	//testing if buttons were getting pressed down
    	for(int i = 1; i < 6; i++){
    		if(begin.stick.getRawButton(i) && begin.stick.getRawButton(i) != previous_button_states[i])
			{
				button_toggles[i] = !button_toggles[i];
			}
    	}

    	//switching between tank and arcade drive with press of a
		if(button_toggles[1])
		{
	        begin.driveTrain.arcadeDrive(-1 * begin.stick.getRawAxis(1), 0.75 * begin.stick.getRawAxis(0));
		}
		else
		{
	        begin.driveTrain.tankDrive(-1 * begin.stick.getRawAxis(1),-1 * begin.stick.getRawAxis(5));
		}
		
			begin.sol1.set(button_toggles[2]);
		
		for(int i = 1; i < 7; i++){
			previous_button_states[i] = begin.stick.getRawButton(i);
		}
		
		if(button_toggles[3]){
			begin.Climbing.set(1);
		}
		else
		{
			begin.Climbing.set(0);	
		}
    }
    
}
