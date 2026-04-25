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
	private Matrix4f projection;
	
	public CameraSystem(EntityRegistry registry) {
		
		this.registry = registry;
		
		this.view = new Matrix4f();
		this.projection = new Matrix4f();
	}
	
	public void loadSize(int w , int h){
		
		float halfW = w/2;
		float halfH = h/2;
		
		projection.ortho(-halfW , halfW , halfH , -halfH ,-1f , 1f);
	}
	
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
	
	public Matrix4f getViewMatrix() {return view;}
	public Matrix4f getProjectionMatrix() {return projection;}
}