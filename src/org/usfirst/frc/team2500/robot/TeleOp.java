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
	boolean light_toggle_1 = false;
	boolean light_toggle_2 = false;
	
	int LowGearMax = 0;

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
    	
    	begin = Robot.begin;
    	drive = begin.drive;
//    	vision = Robot.vision;
    	
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
	
	public void teleopInit(){
		drive.setTeleopPID(true);
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
    	
    	//testing if buttons were getting pressed down
    	for(int i = 1; i < 6; i++){
    		if(begin.stick1.getRawButton(i) && begin.stick1.getRawButton(i) != previous_button_states1[i])
			{
    		button_toggles1[i] = !button_toggles1[i];
    		System.out.println("Button "+ i + ": " + button_toggles1[i]);
			}
    	}
    	
		//Solenoids
    	
    	//gear jaw open/close on A
		begin.sol1.set(button_toggles1[3]);

		//shift on B
		if(button_toggles1[2]){
			begin.sol2.set(true);
		}
		else{
			if(begin.eCodeLeft.getRate() >= LowGearMax || begin.eCodeRight.getRate() >= LowGearMax){
				begin.sol2.set(false);
			}
			else{
				begin.sol2.set(true);
			}
		}

		if(button_toggles1[1]){
			drive.setMax(60);
		}
		else{
			drive.setMax(60);
		}

		//lock/unlock on X
		begin.sol3.set(button_toggles1[1]);
		
		begin.climber.set(begin.stick1.getRawAxis(5));
		
		begin.light1.set(button_toggles1[3]);
		begin.light2.set(button_toggles1[2]);
    	
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
//		if(button_toggles1[5]) {
//			drive.tankDrive(-1 * begin.stick1.getRawAxis(1),-1 * begin.stick1.getRawAxis(5));
//		}
//		else {
		
			double mov_value;
			if (Math.abs(begin.stick1.getRawAxis(0)) > 0.3) {
				turning_value = begin.stick1.getRawAxis(0);
			}
			else {
				turning_value = 0;
			}
			
			if (Math.abs(begin.stick1.getRawAxis(1)) > 0.3) {
				mov_value = -1 * begin.stick1.getRawAxis(1);
			}
			else {
				mov_value = 0;
			}
			
			drive.arcadeDrive(turning_value, mov_value);
			
			System.out.println("Left: " + begin.eCodeLeft.getDistance());
			System.out.println("Right: " + begin.eCodeRight.getDistance());
			
			//lights on or off
//			if(begin.stick1.getRawAxis(1) > 0.1) {
//				//One light on for driving forward
//				begin.light1.set(true);
//				begin.light2.set(false);
//			}
//			else if(begin.stick1.getRawAxis(1) < -0.1) {
//				//Other light on off driving backwards
//				begin.light1.set(false);
//				begin.light2.set(true);
//			}
//			else {
//				//Both off if not driving
//				begin.light1.set(false);
//				begin.light2.set(false);
//			}
			
			
//		}
		
		if(begin.stick1.getRawButton(6)){
			begin.eCodeLeft.reset();
			begin.eCodeRight.reset();
		};
		
		for(int i = 1; i < 7; i++){
			previous_button_states1[i] = begin.stick1.getRawButton(i);
		}
		
		
    }
}