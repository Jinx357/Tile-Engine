package com.kira.game;

import java.util.List;
import java.util.ArrayList;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.joml.Vector2f;

import com.kira.game.window.*;
import com.kira.game.graphics.*;
import com.kira.game.ecs.*;
import com.kira.game.components.*;
import com.kira.game.systems.*;
import com.kira.game.assets.*;
import com.kira.game.input.Input.*;

//ADDING
/*

 this class is only supposed to exist as a test class 
 to be able to use the various features and will not be
 part of the final build
 
 */
public class Game {
	
	private  Window window;
	
	private RenderSystem renderSys;
	private RenderQueue queue;
	private Renderer renderer;
	
	
	private  EntityRegistry registry;
	
	private float time;
	private float deltaTime;
    private float smoothDeltaTime = 1f/60f;
	private float smoothing = 0.05f;
	
	private MovementSystem movement;
	private InputSystem input;
	private TransformSystem transform;
	private CameraSystem camera;
	
	private Mesh mesh;

	public Game() {
		
		this.window = new Window(800 , 800 , "Gaem" , 0 , 0 , 8 , 1);
		this.window.makeWindow();
		//
		this.registry = new EntityRegistry();
		//
		this.renderSys = new RenderSystem();
		this.queue = new RenderQueue(200);
		this.renderer = new Renderer(this.registry);
		//
		this.input = new InputSystem(this.registry);
		this.movement = new MovementSystem(this.registry);
		this.mesh = new Mesh();
		this.transform = new TransformSystem(this.registry);
		this.camera = new CameraSystem(this.registry);
		//
		this.time = window.getTime();
		
		ShaderC sh = new ShaderC(ShaderAssetsManager.getShader(ShaderType.DEFAULT_VERTEX_SHADER) 
		,ShaderAssetsManager.getShader(ShaderType.DEFAULT_PIXEL_SHADER));
		
		int e1 = registry.createEntity();
		
		registry.addComponent(e1 , new VelocityComponent(1f , 1f , 1f , 1f));
		registry.addComponent(e1 , new TransformComponent(new Vector2f(0.0f , 0.0f)));
		registry.addComponent(e1 , new RenderableComponent(mesh.createMesh(mesh.getVerts() , mesh.getIndex()) 
		,sh.getShaderProgram() , sh , 1f));
		
		int e2 = registry.createEntity();
		
		registry.addComponent(e2 , new VelocityComponent(0f , 0f , 0f , 0f));
		registry.addComponent(e2 , new TransformComponent(new Vector2f(0.5f , 1.0f)));
		registry.addComponent(e2 , new RenderableComponent(mesh.createMesh(mesh.getVerts() , mesh.getIndex()) , sh.getShaderProgram() , sh , 0f));
		
		int e3 = registry.createEntity();
		
		registry.addComponent(e3 , new VelocityComponent(0f , 0f , 0f , 0f));
		registry.addComponent(e3 , new TransformComponent(new Vector2f(1f , 1f)));
		registry.addComponent(e3 , new RenderableComponent(mesh.createMesh(mesh.getVerts() , mesh.getIndex()) , sh.getShaderProgram() , sh , 0.5f));
		
		
		int cam = registry.createEntity();
		
		registry.addComponent(cam , new VelocityComponent(1f , 1f , 1f , 1f));
		registry.addComponent(cam , new TransformComponent(new Vector2f(0f , 0f)));
		registry.addComponent(cam , new CameraComponent(e1));
		
		
		
		
		input.load(window.getContext());
		renderSys.load(this.registry);
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
			
			smoothDeltaTime = smoothDeltaTime * (1f - smoothing) + deltaTime * smoothing;
			
			//System.out.println(smoothDeltaTime);
			
			queue.clear();
			renderer.clear();
			
			glfwPollEvents();
			
			input.update();
			movement.update(smoothDeltaTime);
			transform.update();
			camera.update();
			
			renderSys.update(queue);
			
			renderer.loadViewMatrix(camera.getViewMatrix());
			renderer.render(queue);
			
			glfwSwapBuffers(window.getContext());
			
		}
	}
	
	
	private void cleanup() {
		
		window.dispose();
	}
}