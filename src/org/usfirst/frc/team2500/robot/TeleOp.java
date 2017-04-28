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


	double LowGearMax = 60;
	double LowGearMin = 5*(0.163265);
	double lowGearPersential = 1;
	
	boolean shifted = false;
	
	boolean gear_bool = true;

	int rampTime = 1;

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
		begin.shiftTimer.start();;
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

		//seting all previous button states for button togles
		for(int i = 1; i < 7; i++){
			previous_button_states[i] = begin.stick.getRawButton(i);
		}
		
    	//gear jaw open/close on A toggle
		begin.jaw.set(button_toggles[1]);
    	SmartDashboard.putBoolean("Jaw ", button_toggles[1]);	
    	
    	//gearRamp open/close on LB
    	begin.gearRamp.set(button_toggles[5]);
		
		//invert contoles on Y
		Boolean dMode;
		if(button_toggles[4]){
			mov_value = -1 * mov_value;
		}
    	SmartDashboard.putBoolean("Invert", button_toggles[4]);
		
	    //hold start to drive foroward
	    if(begin.stick.getRawButton(8)){
	    	turning_value = 0;
	    }
	    
		//shift on B if you are atleast going 35 rate on ecode left and shifts back if your speed dropes back down to 5 rate
		if(!button_toggles[2]){
			begin.shift.set(false);
			begin.drive.arcadeDrive(turning_value,mov_value);
		}
		else{
			//testing if meets max low gear eCode rate
			if(Math.abs(begin.eCodeLeft.getRate()) >= LowGearMax || shifted){
				//To do ramp after requested speed is over 65%
				if(mov_value > 0.65){
					begin.rampTimer.start();
					//ramping based on time
					if(begin.rampTimer.get() < rampTime){
						mov_value = 0.65 + ((0.35 * begin.rampTimer.get())/rampTime);
					}
					//if timer is greater then rampTime auto set move value to 1
					else {
						mov_value = 1;
					}
				}
				else{
					//if requested speed is less then 65% reset the timer to 0
					begin.rampTimer.reset();
					mov_value = mov_value * 0.65;
				}
				begin.shift.set(true);
				begin.drive.arcadeDrive(turning_value * 0.65, mov_value);
	    		gear_bool = true;
				shifted = true;
				begin.shiftTimer.reset();
			}
			else if(begin.shiftTimer.get() > 0.25){
				begin.rampTimer.reset();
				begin.shift.set(false);
				begin.drive.arcadeDrive(turning_value,mov_value * lowGearPersential);
	    		gear_bool = false;
			}
			//testing if meets min speed to reset the shifters to closed
			if(begin.eCodeLeft.getRate() <= LowGearMin){
				shifted = false;
			}
		}

    	SmartDashboard.putBoolean("Gear", gear_bool);

		//if Climber moveing then set break to open if not moveing break locked
		if(Math.abs(begin.stick.getRawAxis(2) - begin.stick.getRawAxis(3)) > 0.1){
			begin.Break.set(true);
			begin.climber.set(begin.stick.getRawAxis(2) - begin.stick.getRawAxis(3));
		}
		else if(button_toggles[3]){
			begin.Break.set(false);
		}
		else {
			begin.Break.set(false);
			begin.climber.set(0);
		}
    }
}