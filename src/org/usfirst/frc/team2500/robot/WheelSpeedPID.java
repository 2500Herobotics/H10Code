package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class WheelSpeedPID extends PIDSubsystem{

	boolean enabled = false;
	double target = 0;
	
	Talon motor_1;
	Talon motor_2;
	Talon motor_3;
	
	Encoder enc;
	
	public WheelSpeedPID() {
		super(0, 1, 0);
	}
	
	public void setEnc(Encoder enc){
		this.enc = enc;
	}
	
	public void setMotors(Talon motor_1, Talon motor_2, Talon motor_3){
		this.motor_1 = motor_1;
		this.motor_2 = motor_2;
		this.motor_3 = motor_3;
	}

	public void setEnable(boolean status){
		enabled = status;
	}
	
	@Override
	protected double returnPIDInput() {
		return enc.getRate();
	}

	@Override
	protected void usePIDOutput(double output) {
		System.out.println("Output: " + output);
		if (enabled){
			motor_1.pidWrite(output);
			motor_2.pidWrite(output);
			motor_3.pidWrite(output);
//			motor_1.set(output);
//			motor_2.set(output);
//			motor_3.set(output);
		}
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
