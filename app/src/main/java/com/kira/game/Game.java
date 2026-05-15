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
import com.kira.game.world.map.*;

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
	private ShaderC sh;
	
	private TileMap map;
	private int mVao;

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
		//
		this.map = new TileMap(2 , 2 , 16);
		//
		
		
		float[] m = {0f , 16f , 16f , 16f , 16f , 0f};
		int[][] tl = {
			{1 , 2} , {1 , 2}
		};
		map.setTiles(tl);
		Mesh mapM = new Mesh(m , 3);
		mVao = mapM.getPVao();
		
		TileSheet tileSheet = new TileSheet(16 , 1 , 11 , 18);
		SpriteRegion grassRegion = tileSheet.getSprite(2);
		SpriteRegion guy = tileSheet.getSprite(118);
		
		sh = new ShaderC(ShaderAssetsManager.getShader(ShaderType.DEFAULT_VERTEX_SHADER) 
		,ShaderAssetsManager.getShader(ShaderType.DEFAULT_PIXEL_SHADER));
		
	
		 TextureC textureAtlas = new TextureC(TextureAssetsManager.getTexture(TextureType.TEXTURE_ATLAS));
		 
		 
		 Mesh meshG = new Mesh(grassRegion);
		 Mesh meshT = new Mesh(guy);
		 
		 Material mat1 = new Material(sh , textureAtlas);
		 Material grass = new Material(sh , textureAtlas);
		
		int e1 = registry.createEntity();
		
		registry.addComponent(e1 , new VelocityComponent(10f , 10f , 1f , 1f));
		registry.addComponent(e1 , new TransformComponent(new Vector2f(100f , 100f)));
		registry.addComponent(e1 , new RenderableComponent(meshT.createMesh() , mat1 , 2));
	
		
		int cam = registry.createEntity();
		
		registry.addComponent(cam , new VelocityComponent(10f , 10f , 1f , 1f));
		registry.addComponent(cam , new TransformComponent(new Vector2f(0f , 0f)));
		registry.addComponent(cam , new CameraComponent(e1));
		
		int i = 36, j = 36 , x = 0 , y = 0;
		int currentE = 4;
		int currentEPosX = -16 , currentEPosY = -16;
		
		/*for(x = 0; i > x; x++ , currentEPosX+=16) {
			
			for(y = 0; j > y; y++ , currentEPosY+=16)
			{
			//	System.out.print("o");
				
				currentE =  registry.createEntity();
				
				registry.addComponent(currentE , new TransformComponent(new Vector2f(currentEPosX , currentEPosY)));
				registry.addComponent(currentE , new RenderableComponent(meshG.createMesh() , grass , 1));
			}
			currentEPosY = -16;
			//System.out.println();
		}*/
		
		
		
		
		input.load(window.getContext());
		camera.loadSize(window.getWidth() , window.getHeight());
		renderSys.load(this.registry);
		
		//renderer.loadMap(map , tileSheet);
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
			
			queue.clear(2);
			renderer.clear();
			
			glfwPollEvents();
			
			input.update();
			movement.update(smoothDeltaTime);
			transform.update();
			camera.update();
			
			renderSys.update(queue);
			
			renderer.loadViewMatrix(camera.getViewMatrix());
			renderer.loadProjectionMatrix(camera.getProjectionMatrix());
			
			renderer.render(sh , new TextureC(TextureAssetsManager.getTexture(TextureType.TEXTURE_ATLAS)) , mVao , 2 , 2);
			renderer.render(queue);
			
			glfwSwapBuffers(window.getContext());
			
		}
	}
	
	
	private void cleanup() {
		
		window.dispose();
	}
}