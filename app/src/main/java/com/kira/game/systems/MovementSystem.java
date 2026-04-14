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
	
	private float xp = 0f;
	private float yp = 0f;
	
	public void update(float deltaTime) {
		
		bundle = new ArrayList<>( registry.view(TransformComponent.class));
		//bundle = registry.view(PositionComponent.class);
		
		//PositionComponent pos = null;
		
		
		//System.out.print("a");
		for(int entity : bundle) {
			
			
			//pos  = registry.getComponent(entity , PositionComponent.class);
			VelocityComponent vel  = registry.getComponent(entity , VelocityComponent.class);
     		TransformComponent t = registry.getComponent(entity , TransformComponent.class);
			
			
			System.out.println("ms "+entity + " _>" + t);
			System.out.println("m->" + System.identityHashCode(t));
			if(vel.velocityX != 0 || vel.velocityY != 0) {
				
				xp += vel.velocityX * deltaTime;
				yp += vel.velocityY * deltaTime;
			}
			
			t.position.set(xp , yp);
		}
		//System.out.println(pos.x + " " + pos.y + " " + vel.velocityX + " " + vel.velocityY);
	}
}