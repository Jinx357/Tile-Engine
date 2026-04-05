package com.kira.game;

import java.util.List;
import java.util.ArrayList;

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
import com.kira.game.components.PositionComponent;

import static com.kira.game.input.Input.isWireframeOn;
import static com.kira.game.input.Input.keyCalls;

//ADDING
public class Game {
	
	private  Window window;
	private  Renderer renderer;
	private  EntityRegistry registry;
	
	private float time;
	private float deltaTime;

	public Game() {
		
		this.window = new Window(800 , 800 , "Gaem" , 0 , 0 , 8 , 0);
		this.window.makeWindow();
		this.renderer = new Renderer();
		this.registry = new EntityRegistry();
		
		this.time = window.getTime();
		
		{
		int e1 = registry.createEntity();
		int e2 = registry.createEntity();
		int e3 = registry.createEntity();
		int e4 = registry.createEntity();
		
		registry.addComponent(e1 , new VelocityComponent(10.0f));
		registry.addComponent(e2 , new VelocityComponent(20.0f));
		registry.addComponent(e2 , new PositionComponent(1 , 1));
		registry.addComponent(e3 , new PositionComponent(0 , 0));
		registry.addComponent(e4 , new PositionComponent(2 , 0));
		}
		
		List<Integer> bundle = new ArrayList<>();
		
		bundle = registry.view(VelocityComponent.class);
		for(int e : bundle) {
			
			System.out.println("entity #" + e + ": has -> " + VelocityComponent.class + " , " + PositionComponent.class);
		}
	}
	
	public void run() {
		
		gameLoop();
		cleanup();
	}
	public void close() {
		
		
	}
	
	private void gameLoop() {
		
		while(!glfwWindowShouldClose(window.getContext())) {
			
			//get deltaTime
			deltaTime =  window.getTime() - time;
			time = window.getTime();
			
			
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