package com.kira.game.components;

import com.kira.game.components.interfaces.Components;

public class VelocityComponent implements Components {
	
		public float velocityX;
		public float velocityY;
		public float velocityClamp;
		public float generalVelocity;
	
	public VelocityComponent(float velocityX , float velocityY , float velocityClamp , float generalVelocity) {
		
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		this.velocityClamp = velocityClamp;
		this.generalVelocity = generalVelocity;
	}

}