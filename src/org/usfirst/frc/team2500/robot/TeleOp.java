package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick.RumbleType;


public class TeleOp extends IterativeRobot{
	Begin begin;
	
	//setting all the button
	boolean[] previous_button_states;
	boolean[] button_toggles;
	
    /**
     * This function is called once each time the robot enters tele-operated mode
     */
	
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
    	if(begin.startup > 0){
    		begin.startup = begin.startup-1;
    		begin.stick.setRumble(RumbleType.kRightRumble, 1);
    		begin.stick.setRumble(RumbleType.kLeftRumble, 1);
    	}
    	else
    	{
    		if(begin.toggle == true){
        		begin.toggle = false;
    			begin.stick.setRumble(RumbleType.kRightRumble, 0);
    			begin.stick.setRumble(RumbleType.kLeftRumble, 0);
    		}
    	}
		
    	//testing if buttons were getting pressed down
    	for(int i = 0; i < 6; i++){
		if(begin.stick.getRawButton(i) && begin.stick.getRawButton(i) != previous_button_states[i])
		{
			button_toggles[i] = !button_toggles[i];
		}
    	}

    	//switching between tank and arcade drive with press of a
		if(button_toggles[1])
		{
	        begin.myRobot.arcadeDrive(begin.stick.getRawAxis(0),begin.stick.getRawAxis(1));
		}
		else
		{
	        begin.myRobot.tankDrive(begin.stick.getRawAxis(0),begin.stick.getRawAxis(2));
		}
		
		for(int i = 0; i < 6; i++){
			previous_button_states[i] = begin.stick.getRawButton(i);
		}
    }
    
}
