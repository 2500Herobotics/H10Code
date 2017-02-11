package org.usfirst.frc.team2500.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.buttons.Button;
//import edu.wpi.cscore.UsbCamera;
//import edu.wpi.first.wpilibj.CameraServer;
//import edu.wpi.first.wpilibj.DriverStation;


public class TeleOp extends IterativeRobot{
	Robot robot;
	Begin begin;
	Vision vision;
	eCodeDrive drive;

	//setting all the button
	boolean[] previous_button_states1;
	boolean[] button_toggles1;
	boolean[] previous_button_states2;
	boolean[] button_toggles2;
	boolean driving_state = false;
	double turning_value = 0;

    public UsbCamera curCam;

    /**
     * This function is called once each time the robot enters tele-operated mode
     */
	
    public TeleOp(){
    	
    	robot = new Robot();
    	begin = Robot.begin;
    	vision = Robot.vision;
    	drive = begin.drive;
    	
    	previous_button_states1 = new boolean[7];
    	button_toggles1 = new boolean[7];
    	
    	for(int i = 0; i < 6; i++){
    		button_toggles1[i] = false;
    	}
    }

    /**
     * This function is called periodically during operator control
     */
	int timer = 0;
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
    	
    	//testing if buttons were getting pressed down
    	for(int i = 1; i < 6; i++){
    		if(begin.stick1.getRawButton(i) && begin.stick1.getRawButton(i) != previous_button_states1[i])
			{
    		button_toggles1[i] = !button_toggles1[i];
    		System.out.println("Button "+ i + ": " + button_toggles1[i]);
			}
    	}
    	
    	/*
    	 *Axis Get Raw Number Codes
    	 *	0 = Stick 1 X
    	 *	1 = Stick 1 Y
    	 *	2 = Left Trigger
    	 *	3 = Right Trigger
    	 *	4 = Stick 2 X
    	 *	5 = Stick 2 Y
    	 */
    	
    	//Switching Drive modes Tank/Arcade
		if(button_toggles1[5]) {
			drive.tankDrive(-1 * begin.stick1.getRawAxis(1),-1 * begin.stick1.getRawAxis(5));
		}
		else {
			if (Math.abs(begin.stick1.getRawAxis(0)) > 0.2) {
				turning_value = -1* begin.stick1.getRawAxis(0);
			}
			else {
				turning_value = 0;
			}
			if(begin.stick1.getRawAxis(1) > 0.1) {
				//One light on for driveing forword
				begin.light1.set(true);
				begin.light2.set(false);
			}
			else if(begin.stick1.getRawAxis(1) < -0.1) {
				//Other light on of driving backwords
				begin.light1.set(false);
				begin.light2.set(true);
			}
			else {
				//Both of if not driving
				begin.light1.set(false);
				begin.light2.set(false);
			}
			
			drive.arcadeDrive(begin.stick1.getRawAxis(1), turning_value);
		}
		
		//Solenoids
		begin.sol4.set(button_toggles1[2]);//1
		begin.sol1.set(button_toggles1[3]);//3
		begin.sol3.set(button_toggles1[3]);//4
		
		//Camera Toggle
		if(button_toggles1[1]){
			curCam = robot.driveCam;
		}
		else {
			curCam = robot.gearCam;
		}
		
		for(int i = 1; i < 7; i++){
			previous_button_states1[i] = begin.stick1.getRawButton(i);
		}
		
		
    }
}