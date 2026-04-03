package com.kira.game.ecs;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;


import com.kira.game.ecs.SparseStorage;
import com.kira.game.components.*;

public class EntityRegistry {
	
	
	private int nextEntity = 0;
	
	private final Set <Integer> currentEntities = new HashSet<>();
	
	private final Map < Class<?> , SparseStorage<?> > componentPools = new HashMap<>();
	
	
	public EntityRegistry() {
		
	}
	
	public int createEntity() {
		
		int entity = ++nextEntity;
		currentEntities.add(entity);
		return entity;
	}
	
	public void removeEntity(int entity) {
		
		currentEntities.remove(entity);
		//throw new RuntimeErrorException("fix this first : 32 -> EntityRegistry.java");
		
		if(!currentEntities.remove(entity)) return;
		
		for(SparseStorage<?> pool : componentPools.values()) pool.removeEntity(entity);
	}
	
	@SuppressWarnings("unchecked")
	public <C> void addComponent(int entity , C component) {
		
		Class<C> type = (Class<C>) component.getClass();
		
		SparseStorage<C> pool = (SparseStorage<C>) componentPools.computeIfAbsent( type , k -> new SparseStorage<C>());
		
		pool.addEntity(entity , component);
	}
	
	@SuppressWarnings("unchecked")
	public <C> C getComponent(int entity , Class<C> type) {
		
		SparseStorage<C> pool = (SparseStorage<C>) componentPools.get(type);
		
		return pool == null ? null : (C) pool.getEntity(entity);
	}
	
	@SuppressWarnings("unchecked")
	public <C> boolean hasComponent(int entity , Class<C> type) {
		
		SparseStorage<C> pool = (SparseStorage<C>) componentPools.get(type);
		
		return pool != null && pool.hasEntity(entity);
	}
	
	@SuppressWarnings("unchecked")
	public <C> void removeComponent(int entity , Class<C> type) {
		
		SparseStorage<C> pool = (SparseStorage<C>) componentPools.get(type);
		
		if(pool != null) pool.removeEntity(entity);
	}
	
}