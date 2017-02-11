package org.usfirst.frc.team2500.robot;

import java.util.concurrent.TimeUnit;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.hal.HAL;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tInstances;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tResourceType;
import edu.wpi.first.wpilibj.RobotDrive;

public class AntiDrunk{

	public AntiDrunk() {
	}

	int eCodeCur = 0;
	int eCodeNex = 0;
	double velocity;
	double error;
	
	public double eCodeToSpeed(Encoder eCode, double rotateValue,long dt,float p){
		eCodeCur = eCodeNex;
		
//		try {
//		    Thread.sleep(dt);
//		} catch(InterruptedException ex) {
//		    Thread.currentThread().interrupt();
//		}
		eCodeNex = eCode.getRaw();
//		System.out.println(eCodeNex);
//		System.out.println(eCodeCur);
		
		velocity = (eCodeNex - eCodeCur)/(dt);
		error = rotateValue - velocity * p;
		return(error);
	}
	
	  public void eArcadeDrive(double moveValue, double rotateValue, boolean squaredInputs, Encoder eCodeLeft, Encoder eCodeRight, RobotDrive drive){
		    // local variables to hold the computed PWM values for the motors

		    double leftMotorSpeed;
		    double rightMotorSpeed;

		    if (squaredInputs) {
		      // square the inputs (while preserving the sign) to increase fine control
		      // while permitting full power
		      if (moveValue >= 0.0) {
		        moveValue = moveValue * moveValue;
		      } else {
		        moveValue = -(moveValue * moveValue);
		      }
		      if (rotateValue >= 0.0) {
		        rotateValue = rotateValue * rotateValue;
		      } else {
		        rotateValue = -(rotateValue * rotateValue);
		      }
		    }

		    if (moveValue > 0.0) {
		      if (rotateValue > 0.0) {
		        leftMotorSpeed = moveValue - rotateValue;
		        rightMotorSpeed = Math.max(moveValue, eCodeToSpeed(eCodeLeft, rotateValue,5,0));
		      } else {
		        leftMotorSpeed = Math.max(moveValue, eCodeToSpeed(eCodeRight, rotateValue,5,0));
		        rightMotorSpeed = moveValue + rotateValue;
		      }
		    } else {
		      if (rotateValue > 0.0) {
		        leftMotorSpeed = -Math.max(-moveValue, eCodeToSpeed(eCodeLeft, rotateValue,5,0));
		        rightMotorSpeed = moveValue + rotateValue;
		      } else {
		        leftMotorSpeed = moveValue - rotateValue;
		        rightMotorSpeed = -Math.max(-moveValue, -eCodeToSpeed(eCodeRight, rotateValue,5,0));
		      }
		    }
		    drive.tankDrive(leftMotorSpeed,rightMotorSpeed);
	  }
}