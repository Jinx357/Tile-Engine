package com.kira.game.systems;

import java.util.List;
import java.util.ArrayList;

import com.kira.game.ecs.EntityRegistry;
import com.kira.game.components.TransformComponent;
import com.kira.game.systems.interfaces.Systems;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class TransformSystem implements Systems {
	
	private EntityRegistry registry;
	private TransformComponent t;
	private List<Integer> bundle;
	
	public TransformSystem(EntityRegistry registry) {
		
		this.registry = registry;
	}
	
	public void load() {
		
		this.bundle = new ArrayList<>(this.registry.view(TransformComponent.class));
	}
	
	public void update() {
		
		this.bundle = new ArrayList<>(this.registry.view(TransformComponent.class));
		
		for(int entity : bundle) {
			
			this.t = this.registry.getComponent(entity , TransformComponent.class);
			//System.out.println(t);
           // this.t.transform.clear();
		    this.t.transformMatrix.identity();
			this.t.transformMatrix.translate(this.t.position.x , this.t.position.y , 0f);
			
			//System.out.println("ts->" + System.identityHashCode(this.t));
		    //System.out.println(System.identityHashCode(registry)+"--");
			
			//System.out.println("ts "+entity + " _>" + t);
		}
	}
}