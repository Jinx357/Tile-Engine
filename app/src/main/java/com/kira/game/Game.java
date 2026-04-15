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
import com.kira.game.components.TextureComponent;
import com.kira.game.components.CameraComponent;

import com.kira.game.systems.InputSystem;
import com.kira.game.systems.MovementSystem;
import com.kira.game.systems.TransformSystem;
import com.kira.game.systems.CameraSystem;

import com.kira.game.assets.TextureAssetsManager;

import com.kira.game.graphics.Mesh;
import com.kira.game.input.Input.*;
import static com.kira.game.input.Input.isWireframeOn;

//ADDING
/*

 this class is only supposed to exist as a test class 
 to be able to use the various features and will be removed and will not be
 part of the final build
 
 */
public class Game {
	
	private  Window window;
	private  Renderer renderer;
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
		this.registry = new EntityRegistry();
		this.renderer = new Renderer(this.registry);
		this.input = new InputSystem(this.registry);
		this.movement = new MovementSystem(this.registry);
		this.mesh = new Mesh();
		this.transform = new TransformSystem(this.registry);
		this.camera = new CameraSystem(this.registry);
		
		this.time = window.getTime();
		
		
		
		//int e1 = registry.createEntity();
		
		//registry.addComponent(e1 , new VelocityComponent(1f , 1f , 1f , 1f));
		//registry.addComponent(e1 , new TransformComponent(new Vector2f(0.0f , 0.0f)));
		//registry.addComponent(e1 , new RenderableComponent(mesh.createMesh(mesh.getVerts() , mesh.getIndex())));
		//registry.addComponent(e1 , new TextureComponent());
		
		int e2 = registry.createEntity();
		
		registry.addComponent(e2 , new VelocityComponent(0f , 0f , 0f , 0f));
		registry.addComponent(e2 , new TransformComponent(new Vector2f(0.0f , 0.0f)));
		registry.addComponent(e2 , new RenderableComponent(mesh.createMesh(mesh.getVerts() , mesh.getIndex())));
		
		
		/*int cam = registry.createEntity();
		
		registry.addComponent(cam , new VelocityComponent(1f , 1f , 1f , 1f));
		registry.addComponent(cam , new TransformComponent(new Vector2f(0f , 0f)));
		registry.addComponent(cam , new CameraComponent(e2));
		*/
		
		
		
		input.load(window.getContext());
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
			
			
			renderer.clear();
			renderer.setDebugMode(isWireframeOn());
			
			
			glfwPollEvents();
			
			input.update();
			movement.update(smoothDeltaTime);
			transform.update();
			//camera.update();
			
			//renderer.loadViewMatrix(camera.getViewMatrix());
			
			renderer.render();
			glfwSwapBuffers(window.getContext());
			
		}
	}
	
	
	private void cleanup() {
		
		window.dispose();
	}
}