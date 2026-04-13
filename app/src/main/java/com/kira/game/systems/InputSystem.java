package com.kira.game.systems;

import com.kira.game.systems.interfaces.Systems;
import com.kira.game.ecs.EntityRegistry;
import com.kira.game.input.Keys;
import com.kira.game.input.Input;

//required
import com.kira.game.components.VelocityComponent;

import java.util.List;
import java.util.ArrayList;

public class InputSystem /*implements Systems*/ {

	private EntityRegistry registry;
	private List<Integer> bundle;
	
	public InputSystem(EntityRegistry registry) {
		
		this.registry = registry;
		
	}
	
	public void load(long pWindow){
		
		this.bundle = new ArrayList<>(registry.view(VelocityComponent.class));
		Input.setCurrentWindow(pWindow);
	}
	//@Override
	public void update() {
		
		for(int entity : this.bundle) {
			//System.out.println(entity);
			VelocityComponent vel = registry.getComponent(entity , VelocityComponent.class);
			
			vel.velocityX = 0f;
			vel.velocityY = 0f;
			
			if(Input.isKeyPressed(Keys.W)) vel.velocityY =  vel.generalVelocity;
			if(Input.isKeyPressed(Keys.A)) vel.velocityX = -vel.generalVelocity;
			if(Input.isKeyPressed(Keys.S)) vel.velocityY = -vel.generalVelocity;
			if(Input.isKeyPressed(Keys.D)) vel.velocityX =  vel.generalVelocity;
			
			//System.out.println("x:"+vel.velocityX+"\ny:"+vel.velocityY);
		}
	}	
}