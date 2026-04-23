package com.kira.game.systems;

import com.kira.game.systems.interfaces.Systems;
import com.kira.game.ecs.EntityRegistry;

// required
import com.kira.game.components.PositionComponent;
import com.kira.game.components.VelocityComponent;
import com.kira.game.components.TransformComponent;


import java.util.List;
import java.util.ArrayList;

public class MovementSystem implements Systems {
	
	private EntityRegistry registry;
	private List<Integer> bundle ;
	
	public MovementSystem(EntityRegistry registry) {
		
		this.registry = registry;
	}
	
	
	
	public void update(float deltaTime) {
		
		bundle = new ArrayList<>( registry.view(TransformComponent.class));
		
		for(int entity : bundle) {
			
			
			
			VelocityComponent vel  = registry.getComponent(entity , VelocityComponent.class);
     		TransformComponent t = registry.getComponent(entity , TransformComponent.class);
			
			float xp = t.position.x;
			float yp = t.position.y;
			
			if(vel.generalVelocity != 0f) {
				
				xp += vel.velocityX * deltaTime;
				yp += vel.velocityY * deltaTime;
			}
			
			t.position.set(xp , yp);
			
		}
		
	}
}