package com.kira.game.systems;

import java.util.List;
import java.util.ArrayList;

import com.kira.game.ecs.EntityRegistry;
import com.kira.game.components.TransformComponent;
import com.kira.game.systems.interfaces.Systems;

import org.joml.Vector2f;

public class TransformSystem implements Systems {
	
	private EntityRegistry registry;
	private TransformComponent t;
	private List<Integer> bundle;
	
	public TransformSystem(EntityRegistry registry) {
		
		this.registry = registry;
		this.t = new TransformComponent(new Vector2f(0f , 0f) , new Vector2f(1f , 1f));
	}
	
	public void load() {
		
		this.bundle = new ArrayList<>(this.registry.view(TransformComponent.class));
	}
	
	public void update() {
		
		
		
		for(int entity : bundle) {
            this.t.transform.clear();
		    this.t.transformMatrix.translate(this.t.position.x , this.t.position.y , 0.0f);
		    this.t.transformMatrix.get(this.t.transform);
		    this.t.transform.flip();
		}
	}
	//transform.translate(new Vector3f(displacementX , 0f , 0f));
		
		//transform.get(trBuffer);
}