package com.kira.game.systems;

import java.util.List;
import java.util.ArrayList;

import org.joml.Matrix4f;

import com.kira.game.components.CameraComponent;
import com.kira.game.components.TransformComponent;

import com.kira.game.ecs.EntityRegistry;

public class CameraSystem {
	
	private EntityRegistry registry;
	private List<Integer> bundle;
	private TransformComponent t;
	private CameraComponent c;
	
	private Matrix4f view;
	
	public CameraSystem(EntityRegistry registry) {
		
		this.registry = registry;
		this.view = new Matrix4f();
	}
	
	public void load(){}
	
	public void update() {
		
		bundle = new ArrayList<>(registry.view(CameraComponent.class , TransformComponent.class));
		
		for(int camera : bundle) {
			
			c = registry.getComponent(camera , CameraComponent.class);
			t = registry.getComponent(camera , TransformComponent.class);
			
			t.position = registry.getComponent(c.targetEntity , TransformComponent.class).position;
			
			view.identity();
			view.translate( t.position.x * -1 , t.position.y * -1, 0);
			
			
		}
	}
	
	public Matrix4f getViewMatrix() {
		
		return view;
	}
}