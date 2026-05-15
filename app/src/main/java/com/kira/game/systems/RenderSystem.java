package com.kira.game.systems;

import com.kira.game.ecs.EntityRegistry;

import java.util.List;
import java.util.ArrayList;

import com.kira.game.graphics.rendering.RenderCommand;
import com.kira.game.graphics.rendering.RenderQueue;
import com.kira.game.graphics.rendering.Renderer;

import com.kira.game.components.TransformComponent;
import com.kira.game.components.RenderableComponent;

public class RenderSystem {
	
	private EntityRegistry registry;
	private List<Integer> bundle;
	
	private TransformComponent t;
	private RenderableComponent r;
	
	public void load(EntityRegistry registry) {
		
		this.registry = registry;
		bundle = new ArrayList<>(registry.view(RenderableComponent.class , TransformComponent.class));
	}
	
	public void update(RenderQueue queue) {
		
		for(int entity : bundle) {
			
			t = registry.getComponent(entity , TransformComponent.class);
			r = registry.getComponent(entity , RenderableComponent.class);
			
			queue.submit(new RenderCommand(r , t) , 2);
		}
		
	}
	
	
}