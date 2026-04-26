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
import com.kira.game.graphics.rendering.*;
import com.kira.game.graphics.resources.*;
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
		this.transform = new TransformSystem(this.registry);
		this.camera = new CameraSystem(this.registry);
		//
		this.time = window.getTime();
		
		
		
		
		
		
		TileSheet tileSheet = new TileSheet();
		SpriteRegion grassRegion = tileSheet.getSprite(10 , 0);
		SpriteRegion guy = tileSheet.getSprite(4 , 8);
		
		ShaderC sh = new ShaderC(ShaderAssetsManager.getShader(ShaderType.DEFAULT_VERTEX_SHADER) 
		,ShaderAssetsManager.getShader(ShaderType.DEFAULT_PIXEL_SHADER));
		
		 TextureC texture1 = new TextureC(TextureAssetsManager.getTexture(TextureType.GEEN_TEXTURE));
		 TextureC texture2 = new TextureC(TextureAssetsManager.getTexture(TextureType.MARBLE_TEXTURE));
		 TextureC textureAtlas = new TextureC(TextureAssetsManager.getTexture(TextureType.TEXTURE_ATLAS));
		 
		 
		 Mesh mesh1 = new Mesh(texture1);
		 Mesh mesh2 = new Mesh(texture2);
		 Mesh meshG = new Mesh(grassRegion);
		 Mesh meshT = new Mesh(guy);
		 
		 Material mat1 = new Material(sh , textureAtlas);
		 Material mat2 = new Material(sh , texture2);
		 Material grass = new Material(sh , textureAtlas);
		
		int e1 = registry.createEntity();
		
		registry.addComponent(e1 , new VelocityComponent(10f , 10f , 1f , 1f));
		registry.addComponent(e1 , new TransformComponent(new Vector2f(100f , 100f)));
		registry.addComponent(e1 , new RenderableComponent(meshT.createMesh() , mat1 , 2));
	
	
		int e2 = registry.createEntity();
		
		//registry.addComponent(e2 , new VelocityComponent(0f , 0f , 0f , 0f));
		registry.addComponent(e2 , new TransformComponent(new Vector2f(1f , 0f)));
		registry.addComponent(e2 , new RenderableComponent(meshG.createMesh() , grass , 1));
/*		
		int e3 = registry.createEntity();
		
		registry.addComponent(e3 , new VelocityComponent(0f , 0f , 0f , 0f));
		registry.addComponent(e3 , new TransformComponent(new Vector2f(2f , 0f)));
		registry.addComponent(e3 , new RenderableComponent(mesh2.createMesh() , mat2));
		
*/		
		int cam = registry.createEntity();
		
		registry.addComponent(cam , new VelocityComponent(10f , 10f , 1f , 1f));
		registry.addComponent(cam , new TransformComponent(new Vector2f(0f , 0f)));
		registry.addComponent(cam , new CameraComponent(e1));
		
		
		
		
		input.load(window.getContext());
		camera.loadSize(window.getWidth() , window.getHeight());
		renderSys.load(this.registry);
	}
	
	public void run() {
		
		gameLoop();
		cleanup();
	}
	public void close() {
		
		//TODO : IMPLEMENT
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
			renderer.loadProjectionMatrix(camera.getProjectionMatrix());
			renderer.render(queue);
			
			glfwSwapBuffers(window.getContext());
			
		}
	}
	
	
	private void cleanup() {
		
		window.dispose();
	}
}