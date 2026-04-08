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
	
	public MovementSystem(EntityRegistry registry) {
		
		this.registry = registry;
	}
	
	@Override
	public void update(float deltaTime ) {
		
		List<Integer> bundle = new ArrayList<>( registry.view(PositionComponent.class));
		//bundle = registry.view(PositionComponent.class);
		
		
		
		for(int entity : bundle) {
			
			PositionComponent pos  = registry.getComponent(entity , PositionComponent.class);
			VelocityComponent vel  = registry.getComponent(entity , VelocityComponent.class);
			TransformComponent tra = registry.getComponent(entity , TransformComponent.class);
			
			if(vel.velocityX != 0 || vel.velocityY != 0) {
				
				pos.x += vel.velocityX * deltaTime;
				pos.y += vel.velocityY * deltaTime;
			}
			
			tra.position.set(pos.x , pos.y);
		}
	}
}