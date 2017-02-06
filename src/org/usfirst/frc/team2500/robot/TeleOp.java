package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.buttons.Button;
//import edu.wpi.cscore.UsbCamera;
//import edu.wpi.first.wpilibj.CameraServer;
//import edu.wpi.first.wpilibj.DriverStation;


public class TeleOp extends IterativeRobot{
	Begin begin;
	Vision vision;
	
	//setting all the button
	boolean[] previous_button_states1;
	boolean[] button_toggles1;
	boolean[] previous_button_states2;
	boolean[] button_toggles2;
	boolean driving_state = false;
	double turning_value = 0;
//	public UsbCamera camOut;
	
    /**
     * This function is called once each time the robot enters tele-operated mode
     */
	
    public TeleOp(){
    	begin = Robot.begin;
    	vision = Robot.vision;
    	previous_button_states1 = new boolean[7];
    	button_toggles1 = new boolean[7];
    	for(int i = 0; i < 6; i++){
    		button_toggles1[i] = false;
    	}
    	turning_value = 0;
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
    		if(begin.stick1.getRawButton(i) && begin.stick1.getRawButton(i) != previous_button_states1[i])
			{
    		button_toggles1[i] = !button_toggles1[i];
    		System.out.println("Button "+i+": "+button_toggles1[i]);
			}
    	}
    	
//    	if(button_toggles1[4]){
//    	    camOut = CameraServer.getInstance().startAutomaticCapture("cam1", 1);
//    	}
//    	else{
//    	    camOut = CameraServer.getInstance().startAutomaticCapture("cam1", 0);
//    	}
    	
		if(button_toggles1[5])
		{
			begin.driveTrain1.tankDrive(-1 * begin.stick1.getRawAxis(1),-1 * begin.stick1.getRawAxis(5));
			begin.driveTrain2.tankDrive(-1 * begin.stick1.getRawAxis(1),-1 * begin.stick1.getRawAxis(5));
		}
		else
		{
			if (Math.abs(begin.stick1.getRawAxis(0)) > 0.2) turning_value = -1* begin.stick1.getRawAxis(0);
			else turning_value = 0;
			begin.driveTrain1.arcadeDrive(begin.stick1.getRawAxis(1), turning_value);
			begin.driveTrain2.arcadeDrive(begin.stick1.getRawAxis(1), turning_value);
		}
		begin.sol1.set(button_toggles1[2]);
//		begin.sol2.set(button_toggles1[1]);
		begin.sol3.set(button_toggles1[3]);
		begin.sol4.set(button_toggles1[3]);
		for(int i = 1; i < 7; i++){
			previous_button_states1[i] = begin.stick1.getRawButton(i);
		}
		
		begin.light1.set(button_toggles1[3]);
    }
}
