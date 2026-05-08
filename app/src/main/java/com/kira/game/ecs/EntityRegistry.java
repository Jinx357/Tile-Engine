package com.kira.game.ecs;

import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

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
		//System.out.println("entity _init_ -> " + currentEntities.contains(entity) + " : created entity-> #" + entity);
		return entity;
	}
	
	public void removeEntity(int entity) {
		
		//currentEntities.remove(entity);
		//throw new RuntimeErrorException("fix this first : 32 -> EntityRegistry.java");
		
		if(!currentEntities.remove(entity)){ System.out.println("rm e nx"); return;}
		
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
		
		return pool == null ? null : (C) pool.getComponent(entity);
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
	
	public ArrayList<Integer> view(Class<?>... components) {
		
		ArrayList<Integer> bundle = new ArrayList<>(0);
		
		if(components.length == 0) {
			
			ArrayList<Integer> bun = new ArrayList<>(currentEntities);
			return bun;
		}
		
		
		SparseStorage<?> smallestPool = null;
		int minsize = Integer.MAX_VALUE;
		
		for(Class<?> type : components) {
			
			SparseStorage<?> p = (SparseStorage<?>) componentPools.get(type);
			
			if(p == null) return bundle;
			
			if(p.getCount() < minsize) {
				
				minsize = p.getCount();
				smallestPool = p;
			}
		}
		
		
		for(int i = 0; i < smallestPool.getCount(); i++) {
			
			int e = smallestPool.getEntityAtDenseIndex(i);
			
			boolean hasAll = true;
			
			for(Class<?> type : components) {
				
				if(!hasComponent(e , type)) {
					
					hasAll = false;
					break;
				}
			}
			
			if(hasAll) bundle.add(e);
		}
		
		return bundle;
	}
}