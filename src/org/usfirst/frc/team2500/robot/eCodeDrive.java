package org.usfirst.frc.team2500.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

public class eCodeDrive {
	
	int target_speed = 0;
//	double left_current_speed = 0;
//	double right_current_speed = 0;
	
//	WheelSpeedPID left_drivetrain;
//	WheelSpeedPID right_drivetrain;
	
	int max_speed = 60;
	
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
		
//		left_drivetrain = new WheelSpeedPID();
//		left_drivetrain.setEnc(left_enc);
//		left_drivetrain.setMotors(left1, left2, left3);
//		
//		right_drivetrain = new WheelSpeedPID();
//		right_drivetrain.setEnc(right_enc);
//		right_drivetrain.setMotors(right1, right2, right3);
//		
//		left_drivetrain.enable();
//		right_drivetrain.enable();
	}
	
	private double calculateTargetSpeed(Encoder enc, double targetSpeed, double currentSpeed){
		//return currentSpeed + ((((targetSpeed * max_speed) - enc.getRate())/max_speed) * k);
		return (((targetSpeed * max_speed) - enc.getRate())/max_speed);
	}
	
	public void setMax(int max){
		max_speed = max;
	}
	
	public boolean driveDistance(double target, double p){
		eCodeVal = left_enc.getDistance();
		double error = target - eCodeVal;
		double speed = error * p;
		System.out.println("Error: " + error);
		if(Math.abs(speed) > 1){
			speed = speed / Math.abs(speed);
		}
		left1.set(speed * -1);
		left2.set(speed * -1);
		left3.set(speed * -1);
		right1.set(speed);
		right2.set(speed);
		right3.set(speed);
		return Math.abs(error) < 4;
	}
	
	public boolean driveDistance(double target, double p, double d){
		time = System.currentTimeMillis();
		eCodeVal = (left_enc.getDistance() + (right_enc.getDistance())) / 2;
//		eCodeVal = left_enc.getDistance();
		error = target - eCodeVal;
		derivative = (error - previous_error) / (time - previous_time);
		speed = ((error * p) + (derivative * d)) / target;
		if (speed > 0.5) speed = 0.5;
		left1.set(speed * -1);
		left2.set(speed * -1);
		left3.set(speed * -1);
		right1.set(speed);
		right2.set(speed);
		right3.set(speed);
//		tankDrive(error, error);
		previous_error = error;
		previous_time = time;
		System.out.println("Error: " + error + " Target: " + target*0.15);
		return error < 0.15 * target;
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
	
	
	public void setTeleopPID(boolean status){
//		left_drivetrain.setEnable(status);
//		right_drivetrain.setEnable(status);

	}
	
	public void arcadeDrive(double moveValue, double rotateValue){
	    double leftTargetSpeed = 0.0;
	    double rightTargetSpeed = 0.0;
	    
	    if (Math.abs(moveValue) < 0.1) moveValue = 0;
	    
	    if (moveValue > 0.0) {
	        if (rotateValue > 0.0) {
	          leftTargetSpeed = moveValue - rotateValue;
	          rightTargetSpeed = Math.max(moveValue, rotateValue);
	        } else {
	          leftTargetSpeed = Math.max(moveValue, -rotateValue);
	          rightTargetSpeed = moveValue + rotateValue;
	        }
	      } else {
	        if (rotateValue > 0.0) {
	          leftTargetSpeed = -Math.max(-moveValue, rotateValue);
	          rightTargetSpeed = moveValue + rotateValue;
	        } else {
	          leftTargetSpeed = moveValue - rotateValue;
	          rightTargetSpeed = -Math.max(-moveValue, -rotateValue);
	        }
	      }
	    
//	    double left_next;
//	    double right_next;
//		if (Math.abs(moveValue) > 0.1){
//			left_drivetrain.setEnable(true);
//			right_drivetrain.setEnable(true);
//			
//			
//			
//			left_drivetrain.setSetpoint(moveValue * max_speed);
//			right_drivetrain.setSetpoint(-1 * moveValue * max_speed);
//			left_next = calculateTargetSpeed(left_enc, moveValue, left_current_speed);
//			right_next = calculateTargetSpeed(right_enc, moveValue, right_current_speed);
//			
////			System.out.println("Left Speed: " + left_current_speed);
////			System.out.println("Right Speed: " + right_current_speed);
//			
//			leftTargetSpeed = (left_next + left_current_speed)*k + left_enc.getRate()*max_speed;
//			rightTargetSpeed = (right_next + right_current_speed)*k + right_enc.getRate()*max_speed;
//			
//			left_current_speed = left_next;
//			right_current_speed = right_next;
//		}
//	    else {
//	    	//turning code doesn't use PID or encoders
//	    	reset();
//	    	left_drivetrain.setEnable(false);
//	    	right_drivetrain.setEnable(false);
//	        if (rotateValue > 0.0) {
//	          leftTargetSpeed = -Math.max(-moveValue, rotateValue);
//	          rightTargetSpeed = moveValue + rotateValue;
//	        } else {
//	          leftTargetSpeed = moveValue - rotateValue;
//	          rightTargetSpeed = -Math.max(-moveValue, -rotateValue);
//	        }
//			left1.set(leftTargetSpeed);
//			left2.set(leftTargetSpeed);
//			left3.set(leftTargetSpeed);
//			right1.set(rightTargetSpeed);
//			right2.set(rightTargetSpeed);
//			right3.set(rightTargetSpeed);
//	      }
//		System.out.println("Left Speed: " + left_enc.getRate());
//		System.out.println("Right Speed: " + right_enc.getRate());
//		double left_rate = left_enc.getRate();
//		double right_rate = right_enc.getRate();
//		if (right_rate != 0) leftTargetSpeed *= left_rate/right_rate;
		if (rightTargetSpeed > 0.9) rightTargetSpeed = 0.9;
		left1.set(leftTargetSpeed);
		left2.set(leftTargetSpeed);
		left3.set(leftTargetSpeed);
		right1.set(rightTargetSpeed);
		right2.set(rightTargetSpeed);
		right3.set(rightTargetSpeed);
	}
	
	public void tankDrive(double leftValue, double rightValue){
//		left_current_speed = calculateTargetSpeed(left_enc, leftValue, left_current_speed);
//		right_current_speed = calculateTargetSpeed(right_enc, rightValue, right_current_speed);
//
		left1.set(leftValue);
		left2.set(leftValue);
		left3.set(leftValue);
		right1.set(rightValue);
		right2.set(rightValue);
		right3.set(rightValue);
//
//		System.out.println("Tank Left: " + left_current_speed + "       Left Target: " + leftValue);
//		System.out.println("Tank Right: " + right_current_speed + "       Right Target: " + rightValue);
	}
}
