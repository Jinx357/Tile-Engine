package com.kira.game;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import com.kira.game.window.Window;

import com.kira.game.graphics.Renderer;

//import com.kira.game.ecs.entities.Entity;
//import com.kira.game.ecs.entities.EntityFactory;

import com.kira.game.ecs.EntityRegistry;
import com.kira.game.components.VelocityComponent;

import static com.kira.game.input.Input.isWireframeOn;
import static com.kira.game.input.Input.keyCalls;

//ADDING
public class Game {
	
	private  Window window;
	private  Renderer renderer;
	private  EntityRegistry registry;
	//private Entity entity , other;
	private int entity;

	public Game() {
		
		this.window = new Window(800 , 800 , "Gaem" , 0 , 0 , 8 , 0);
		this.window.makeWindow();
		this.renderer = new Renderer();
		this.registry = new EntityRegistry();
		
		
		int entity = registry.createEntity();
		
		registry.addComponent(entity , new VelocityComponent(0.01f));
		System.out.println(registry.hasComponent(entity , VelocityComponent.class));
		System.out.println(registry.getComponent(entity , VelocityComponent.class));
		registry.removeEntity(entity);
		System.out.println(registry.getComponent(entity , VelocityComponent.class));
		//this.entity = EntityFactory.createEntity();
		
		//this.other = EntityFactory.createEntity();
	}
	
	public void run() {
		
		gameLoop();
		cleanup();
	}
	public void close() {
		
		
	}
	
	private void gameLoop() {
		
		while(!glfwWindowShouldClose(window.getContext())) {
			
			renderer.clear();
			//renderer.render(this.other);
			//renderer.render(this.entity);
			
			glfwPollEvents();
			//keyCalls(window.getContext() , this.entity);
			
			renderer.setDebugMode(isWireframeOn());
			
			glfwSwapBuffers(window.getContext());
		}
	}
	
	
	private void cleanup() {
		
		window.dispose();
	}
}