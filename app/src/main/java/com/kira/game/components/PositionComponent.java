package com.kira.game.components;

import com.kira.game.components.interfaces.Components;

public class PositionComponent implements Components {
	
	public float x;
	public float y;
	
	public PositionComponent(float x , float y) {
		
		this.x = x;
		this.y = y;
	}

}