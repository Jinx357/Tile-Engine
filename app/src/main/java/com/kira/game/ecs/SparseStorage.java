package com.kira.game.ecs;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class SparseStorage <C>{
	
	//make resizable later
	private int [] sparseArray;
	
	private List <Integer> denseEntities;
	
	private List <C> denseComponents;

	
	public SparseStorage() {
		
		this.sparseArray = new int[2048];
		Arrays.fill(this.sparseArray , -1);
		// make sure that sparse array is not filled with random stuf  when initialised or smth am dum D:
		
		this.denseEntities = new ArrayList <> ();
		
		this.denseComponents = new ArrayList <> ();
	}
	
	public void addEntity(int entity , C component) {
		
		if (entity >= sparseArray.length ) return;
		
		if (sparseArray[entity] != -1){
			denseComponents.set(sparseArray[entity] , component);
			return;
		}
		
		denseEntities.add(entity);
		denseComponents.add(component);
		sparseArray[entity] = denseEntities.size() - 1;
	}
	
	
	public void removeEntity(int entity) {
		
		
		if(entity >= sparseArray.length || sparseArray[entity] == -1) return;
			
		int idx = sparseArray[entity];
		int last = denseEntities.size() - 1;
		
		if(idx == -1) return;
		
		if(idx != last) {
			
			int movedEntity = denseEntities.get(last);
			
			denseEntities.set(idx , movedEntity);
			denseComponents.set(idx , denseComponents.get(last));
			
			sparseArray [movedEntity] = idx;
		}
		
		denseEntities.remove(last);
		denseComponents.remove(last);
		sparseArray[entity] = denseEntities.size() - 1;
	}
	
	public int getCount() {
		
		return denseEntities.size();
	}
	
	public C getEntity(int entity) {
		
		if(entity >= sparseArray.length || sparseArray[entity] == -1) return null;
		
		if(entity < 0 || entity >= denseComponents.size()) return null;
		
		return denseComponents.get(sparseArray[entity]);
	}
	
	public boolean hasEntity(int entity) {
		
		if(entity >= sparseArray.length) return false;
		
		return sparseArray[entity] != -1;
	}
	
	
}