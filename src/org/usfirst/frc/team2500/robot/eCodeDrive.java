package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

public class eCodeDrive {

	int max_speed = 1400;
	int target_speed = 0;
	double left_current_speed = 0;
	double right_current_speed = 0;
	
	double k = 0.5;
	
	Talon left;
	Talon right;
	Encoder left_enc;
	Encoder right_enc;
	
	public eCodeDrive(Talon left, Talon right, Encoder left_enc, Encoder right_enc){
		this.left = left;
		this.right = right;
		this.left_enc = left_enc;
		this.right_enc = right_enc;
	}
	
	private double calculateMotorSpeed(Encoder enc, double input, double current){
		return current + ((((input*max_speed) - enc.getRate())/max_speed) * k);
	}
	
	private void reset(){
		left_current_speed = 0;
		right_current_speed = 0;
	}
	
	public void arcadeDrive(double moveValue, double rotateValue){
	    double leftTargetSpeed = 0.0;
	    double rightTargetSpeed = 0.0;
		if (moveValue > 0){
			left_current_speed = calculateMotorSpeed(left_enc, moveValue, left_current_speed);
			right_current_speed = calculateMotorSpeed(right_enc, moveValue, right_current_speed);
			
			leftTargetSpeed = left_current_speed;
			rightTargetSpeed = right_current_speed;
		}
	    else {
	    	//turning code doesn't use PID or encoders
	    	reset();
	        if (rotateValue > 0.0) {
	          leftTargetSpeed = -Math.max(-moveValue, rotateValue);
	          rightTargetSpeed = moveValue + rotateValue;
	        } else {
	          leftTargetSpeed = moveValue - rotateValue;
	          rightTargetSpeed = -Math.max(-moveValue, -rotateValue);
	        }
	      }
		left.set(leftTargetSpeed);
		right.set(rightTargetSpeed);
	    
	    
	}
	
	public void tankDrive(double leftValue, double rightValue){
		left_current_speed = calculateMotorSpeed(left_enc, leftValue, left_current_speed);
		right_current_speed = calculateMotorSpeed(right_enc, rightValue, right_current_speed);
		
		left.set(left_current_speed);
		right.set(right_current_speed);
	}
}
