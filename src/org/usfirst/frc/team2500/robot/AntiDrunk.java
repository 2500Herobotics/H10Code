package org.usfirst.frc.team2500.robot;

import java.util.concurrent.TimeUnit;

import edu.wpi.first.wpilibj.Encoder;

public class AntiDrunk {

	public AntiDrunk(){
		
	}

	int eCodeCur = 0;
	int eCodeNex = 0;
	float velocity;
	float error;
	
	public float eCodeToSpeed(Encoder eCode, int target,long dt,float p){
		eCodeCur = eCodeNex;
		
//		try {
//		    Thread.sleep(dt);
//		} catch(InterruptedException ex) {
//		    Thread.currentThread().interrupt();
//		}
		
		eCodeNex = eCode.getRaw();
		System.out.println(eCodeNex);
		System.out.println(eCodeCur);
		
		velocity = (eCodeNex - eCodeCur)/(dt);
		error = target - velocity * p;
		return(error);
	}
	
}
