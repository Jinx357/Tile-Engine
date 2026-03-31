package com.kira.game.entities;

import com.kira.game.entities.Entity;

public class EntityFactory {
	
	private static int idCounter = -1;
	
	public static Entity createEntity() {
		
		++idCounter;
		
		return new Entity(idCounter);
	}
}