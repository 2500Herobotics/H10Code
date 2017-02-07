package org.usfirst.frc.team2500.robot;

public class GoodLuck {
	public String Message(){
		
		String[] sentence = new String[6];
		
		sentence[0] = "Good luck guys. -John";
		sentence[1] = sentence[0];
		sentence[2] = "Don't do the bad thing";
		sentence[3] = "Blame others for your own incompetence";
		sentence[4] = "Wake me up inside";
		sentence[5] = "Motivation";

		int ranNum = (int) Math.ceil(((Math.random() * sentence.length) - 1));
		return(sentence[ranNum]);
	}
}
