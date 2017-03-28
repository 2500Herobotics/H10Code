package org.usfirst.frc.team2500.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleOp extends IterativeRobot{
	Robot robot;
	Begin begin;
	eCodeDrive drive;
	
	//setting all the button
	boolean[] previous_button_states;
	boolean[] button_toggles;
	
	double turning_value = 0;
	double mov_value = 0;


	int LowGearMax = 35;
	int LowGearMin = 5;
	boolean shifted = false;
	boolean gear_bool = true;

	int timer = 0;

    public UsbCamera curCam;

    /**
     * This function is called once each time the robot enters tele-operated mode
     */
	
    public TeleOp(){
    	
    	begin = Robot.begin;
    	
    	//starting with no null values
    	previous_button_states = new boolean[7];
    	button_toggles = new boolean[7];
    	
    	for(int i = 0; i < 6; i++){
    		button_toggles[i] = false;
    	}
    }

    /**
     * This function is called periodically during operator control
     */
	
	public void teleopInit(){

	}
	
    public void teleopPeriodic() 
    {    	
    	/*
    	*Button Get Raw Number Codes
    	*	1 = A
    	*	2 = B
    	*	3 = X
    	*	4 = Y
    	*	5 = LB
    	*	6 = RB
    	*/
    	
    	/*
    	 *Axis Get Raw Number Codes
    	 *	0 = Stick 1 X
    	 *	1 = Stick 1 Y
    	 *	2 = Left Trigger
    	 *	3 = Right Trigger
    	 *	4 = Stick 2 X
    	 *	5 = Stick 2 Y
    	 */
    	
    	//testing if buttons were getting pressed down and toggles acordingly
    	for(int i = 1; i < 6; i++){
    		if(begin.stick.getRawButton(i) && begin.stick.getRawButton(i) != previous_button_states[i])
			{
    		button_toggles[i] = !button_toggles[i];
			}
    	}
    	
    	//gear jaw open/close on A toggle
		begin.jaw.set(button_toggles[1]);
		
    	String jaw;
    	if(button_toggles[1]){
    		jaw = "Open";
    	}
    	else {
    		jaw = "Closed";
    	}
    	SmartDashboard.putString("Jaw", jaw);
    	SmartDashboard.putBoolean("Jaw ", button_toggles[1]);	
    	
    	//setting turn val and move val to avoid dorment movement
		if (Math.abs(begin.stick.getRawAxis(0)) > 0.3) {
			turning_value = begin.stick.getRawAxis(0);
		}
		else {
			turning_value = 0;
		}
		
		if (Math.abs(begin.stick.getRawAxis(1)) > 0.3) {
			mov_value = -1 * begin.stick.getRawAxis(1);
		}
		else {
			mov_value = 0;
		}

		//shift on B if you are atleast going 35 rate on ecode left and shifts back if your speed dropes back down to 5 rate
		String gear;
		
		if(!button_toggles[2]){
			begin.shift.set(false);
			begin.drive.arcadeDrive(turning_value,mov_value);
    		gear = "Low";
		}
		else{
			//testing if meets max low gear eCode rate
			if(Math.abs(begin.eCodeLeft.getRate()) >= LowGearMax || shifted){
				shifted = true;
				begin.shift.set(true);
				begin.drive.arcadeDrive(turning_value * 0.6, mov_value * 0.6);
	    		gear = "High";
	    		gear_bool = true;
			}
			else{
				begin.shift.set(false);
				begin.drive.arcadeDrive(turning_value,mov_value);
	    		gear = "Low";
	    		gear_bool = false;
			}
			//testing if meets min speed to reset the shifters to closed
			if(begin.eCodeLeft.getRate() <= LowGearMin){
				shifted = false;
			}
			
		}
    	SmartDashboard.putString("High Gear Mode", gear);
    	SmartDashboard.putBoolean("Gear", gear_bool);	

		//if Climber moveing then set break to open if not moveing break locked
		if(Math.abs(begin.stick.getRawAxis(2) - begin.stick.getRawAxis(3)) > 0.1){
			begin.Break.set(true);
			begin.climber.set(begin.stick.getRawAxis(2) - begin.stick.getRawAxis(3));
		}
		else {
			begin.Break.set(false);
			begin.climber.set(0);
		}
		
		System.out.println("Left: " + begin.eCodeLeft.getRate());
		System.out.println("Right: " + begin.eCodeRight.getRate());
		
		//seting all previous button states for button togles
		for(int i = 1; i < 7; i++){
			previous_button_states[i] = begin.stick.getRawButton(i);
		}	
    }
}