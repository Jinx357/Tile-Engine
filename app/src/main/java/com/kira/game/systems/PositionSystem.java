package com.kira.game.systems;

import com.kira.game.systems.interfaces.Systems;
import com.kira.game.ecs.EntityRegistry;

// required
import com.kira.game.components.PositionComponent;
import com.kira.game.components.VelocityComponent;
import com.kira.game.components.TransformComponent;


import java.util.List;
import java.util.ArrayList;

public class PositionSystem implements Systems {
	
	@Override
	public void update(float deltaTime , EntityRegistry registry) {
		
		List<Integer> bundle = new ArrayList<>( registry.view(PositionComponent.class));
		//bundle = registry.view(PositionComponent.class);
		
		
		
		for(int entity : bundle) {
			
			PositionComponent pos  = registry.getComponent(entity , PositionComponent.class);
			VelocityComponent vel  = registry.getComponent(entity , VelocityComponent.class);
			TransformComponent tra = registry.getComponent(entity , TransformComponent.class);
			
			if(vel.velocity != 0) {
				
				pos.x += vel.velocity * deltaTime;
				pos.y += vel.velocity * deltaTime;
			}
			
			tra.position.set(pos.x , pos.y);
		}
	}
}