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
import org.joml.Vector2f;
import com.kira.game.ecs.EntityRegistry;
import com.kira.game.components.VelocityComponent;
import com.kira.game.components.PositionComponent;
import com.kira.game.components.TransformComponent;
import com.kira.game.components.RenderableComponent;
import com.kira.game.systems.InputSystem;
import com.kira.game.systems.MovementSystem;
import com.kira.game.systems.TransformSystem;


import com.kira.game.graphics.Mesh;
import com.kira.game.input.Input.*;
import static com.kira.game.input.Input.isWireframeOn;

//ADDING
public class Game {
	
	private  Window window;
	private  Renderer renderer;
	private  EntityRegistry registry;
	
	private float time;
	private float deltaTime;
	
	private MovementSystem movement;
	private InputSystem input;
	private TransformSystem transform;
	
	private Mesh mesh;

	public Game() {
		
		this.window = new Window(800 , 800 , "Gaem" , 0 , 0 , 8 , 0);
		this.window.makeWindow();
		this.renderer = new Renderer();
		this.registry = new EntityRegistry();
		this.input = new InputSystem(this.registry);
		this.movement = new MovementSystem(this.registry);
		this.mesh = new Mesh();
		this.transform = new TransformSystem(this.registry);
		
		this.time = window.getTime();
		
		
		
		int e1 = registry.createEntity();
		
		
		registry.addComponent(e1 , new VelocityComponent(0.1f , 0.1f , 0.1f , 0.1f));
		registry.addComponent(e1 , new TransformComponent(new Vector2f(0.0f , 0.0f) , new Vector2f(1.0f , 1.0f)));
		registry.addComponent(e1 , new PositionComponent(0.0f , 0.0f));
		registry.addComponent(e1 , new RenderableComponent(mesh.createMesh(mesh.getVerts() , mesh.getIndex())));
		
		
		//int e2 = registry.createEntity();
		
		
		//registry.addComponent(e2 , new VelocityComponent(0.001f , 0.001f , 0.1f , 0.001f));
		//registry.addComponent(e2 , new TransformComponent(new Vector2f(0.0f , 0.0f) , new Vector2f(1.0f , 1.0f)));
		//registry.addComponent(e2 , new PositionComponent(0.0f , 0.0f));
		//registry.addComponent(e2 , new RenderableComponent(mesh.createMesh(mesh.getVerts() , mesh.getIndex())));
		
		
		input.load(window.getContext());
		transform.load();
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
			renderer.setDebugMode(isWireframeOn());
			//get deltaTime
			deltaTime =  window.getTime() - time;
			time = window.getTime();
			
			glfwPollEvents();
			
			input.update();
			movement.update(deltaTime);
			transform.update();
			
			renderer.render(registry);
			glfwSwapBuffers(window.getContext());
			
		}
	}
	
	
	private void cleanup() {
		
		window.dispose();
	}
}