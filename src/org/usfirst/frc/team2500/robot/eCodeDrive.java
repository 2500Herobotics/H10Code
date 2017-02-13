package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

public class eCodeDrive {

	int max_speed = 1400;
	int target_speed = 0;
	double left_current_speed = 0;
	double right_current_speed = 0;
	
	double k = 0.5;
	
	//avoiding excessive allocations on each loop
	double eCodeVal;
	double error;
	double derivative;
	long time;
	double speed;
	
	double previous_error = 0;
	long previous_time = 0;
	
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
		previous_error = 0;
		previous_time = 0;
	}
	
	public boolean driveDistance(double target, double p, double d){
		time = System.currentTimeMillis();
		eCodeVal = (left_enc.getDistance() + right_enc.getDistance())/2;
		error = target - eCodeVal;
		derivative = (error - previous_error) / (time - previous_time);
		speed = ((error * p) + (derivative * d)) / target;
		left.set(speed);
		right.set(speed);
//		tankDrive(error, error);
		previous_error = error;
		previous_time = time;
		return error < 0.1 * target;
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
		System.out.println("Left Speed: " + left_current_speed);
		System.out.println("Right Speed: " + right_current_speed);
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
