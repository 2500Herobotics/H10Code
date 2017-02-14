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

	Talon left1;
	Talon left2;
	Talon left3;
	Talon right1;
	Talon right2;
	Talon right3;
	Encoder left_enc;
	Encoder right_enc;
	
	public eCodeDrive(Talon left1, Talon left2, Talon left3, Talon right1, Talon right2, Talon right3, Encoder left_enc, Encoder right_enc){
		this.left1 = left1;
		this.left2 = left2;
		this.left3 = left3;
		this.right1 = right1;
		this.right2 = right2;
		this.right3 = right3;
		
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
		eCodeVal = (left_enc.getDistance() + (right_enc.getDistance()) * -1) / 2;
		error = target - eCodeVal;
		derivative = (error - previous_error) / (time - previous_time);
		speed = ((error * p) + (derivative * d)) / target;
		left1.set(speed * -1);
		left2.set(speed * -1);
		left3.set(speed * -1);
		right1.set(speed);
		right2.set(speed);
		right3.set(speed);
//		tankDrive(error, error);
		previous_error = error;
		previous_time = time;
		return error < 0.1 * target;
	}
	

	public boolean driveDistance(double rtarget, double ltarget, double p, double d){
		time = System.currentTimeMillis();
		
		double lError = ltarget - left_enc.getDistance();
		double lDerivative = (lError - previous_error) / (time - previous_time);
		double lSpeed = ((lError * p) + (lDerivative * d)) / ltarget * -1;
		
		double rError = rtarget - right_enc.getDistance();
		double rDerivative = (rError - previous_error) / (time - previous_time);
		double rSpeed = ((rError * p) + (rDerivative * d)) / rtarget * -1;
		
		left1.set(lSpeed);
		left2.set(lSpeed);
		left3.set(lSpeed);
		right1.set(rSpeed);
		right2.set(rSpeed);
		right3.set(rSpeed);
		previous_error = error;
		previous_time = time;
		return lError < 0.1 * ltarget && rError < 0.1 * rtarget;
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
		left1.set(leftTargetSpeed);
		left2.set(leftTargetSpeed);
		left3.set(leftTargetSpeed);
		right1.set(rightTargetSpeed);
		right2.set(rightTargetSpeed);
		right3.set(rightTargetSpeed);
	    
	    
	}
	
	public void tankDrive(double leftValue, double rightValue){
		left_current_speed = calculateMotorSpeed(left_enc, leftValue, left_current_speed);
		right_current_speed = calculateMotorSpeed(right_enc, rightValue, right_current_speed);

		left1.set(left_current_speed);
		left2.set(left_current_speed);
		left3.set(left_current_speed);
		right1.set(right_current_speed);
		right2.set(right_current_speed);
		right3.set(right_current_speed);
	}
}
