package com.kira.game.ecs.entities;

import com.kira.game.ecs.entities.Entity;

public class EntityFactory {
	
	private static int idCounter = -1;
	
	public static Entity createEntity() {
		
		++idCounter;
		
		return new Entity(idCounter);
	}
}