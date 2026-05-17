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
import com.kira.game.ui.*;

//ADDING
/*

 this class is only supposed to exist as a test class 
 to be able to use the various features and will not be
 part of the final build
 
 */
public class Game {
	
	private Window window;
	private DevWidgets devS;
	private DebugUIBridge devSB;
	
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

	private TextureC textureAtlas;
	public Game() {
		
		this.window = new Window(800 , 800 , "Gaem" , 0 , 0 , 8 , 1);
		this.window.makeWindow();
		//
		devSB = new DebugUIBridge(this);
		devS = new DevWidgets(devSB);
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
		this.textureAtlas = new TextureC(TextureAssetsManager.getTexture(TextureType.TEXTURE_ATLAS));
		
		TileSheet tileSheet = new TileSheet(16 , 1 , 11 , 18);
		SpriteRegion guy = tileSheet.getSprite(118);
		SpriteRegion grassRegion = tileSheet.getSprite(2);
		
		float w = grassRegion.width;
		float h = grassRegion.height;
		
		float[] m = {
		
			0f , 0f , grassRegion.u0 , grassRegion.v0 , 
			 w , 0f , grassRegion.u1 , grassRegion.v0 ,
			 w , h  , grassRegion.u1 , grassRegion.v1 , 
			0f , h  , grassRegion.u0 , grassRegion.v1
		};
		float[] verts = new float[(10000*16)];
		int i = 0;
		
		for(int x = 0; x < 100; x++) {
			for(int y = 0; y < 100; y++) {
				
				float wx = x * 16;
				float wy = y * 16;
				
			verts[i++] = wx ; 		verts[i++] = wy ;	   verts[i++] = grassRegion.u0 ; verts[i++] = grassRegion.v0 ; 
			verts[i++] = wx + 16 ;  verts[i++] = wy ; 	   verts[i++] = grassRegion.u1 ; verts[i++] = grassRegion.v0 ;
			verts[i++] = wx + 16 ;  verts[i++] = wy + 16 ; verts[i++] = grassRegion.u1 ; verts[i++] = grassRegion.v1 ; 
			verts[i++] = wx ;		verts[i++] = wy + 16 ; verts[i++] = grassRegion.u0 ; verts[i++] = grassRegion.v1;
			}
		}System.out.println("i_" + i);
		
		int[][] tl = new int[100][100];
		
		for(int k = 0; k < 100; k++) {
			for(int r = 0; r < 100; r++) {
				
				tl[k][r] = 3;
			}
		}
		map.setTiles(tl);
		Mesh mapM = new Mesh(verts , 10000);
		mVao = mapM.getPVao();
		
		
		
		sh = new ShaderC(ShaderAssetsManager.getShader(ShaderType.DEFAULT_VERTEX_SHADER) 
		,ShaderAssetsManager.getShader(ShaderType.DEFAULT_PIXEL_SHADER));
		 
		 
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
		
		
		input.load(window.getContext());
		camera.loadSize(window.getWidth() , window.getHeight());
		renderSys.load(this.registry);
		
	}
	
	public void run() {
		
		devS.render();
		gameLoop();
		cleanup();
	}
	public void close() {
		
		//TODO : IMPLEMENT
	}
	public void spawnE(float x , float y) {
		
		TileSheet tileSheet = new TileSheet(16 , 1 , 11 , 18);
		 SpriteRegion guy = tileSheet.getSprite(118);
		 
		 Mesh meshT = new Mesh(guy);
		 
		 Material mat1 = new Material(sh , textureAtlas);
		
		
		int e67 = registry.createEntity();
		
		registry.addComponent(e67 , new VelocityComponent(0f , 0f , 0f , 0f));
		registry.addComponent(e67 , new TransformComponent(new Vector2f(x , y)));
		registry.addComponent(e67 , new RenderableComponent(meshT.createMesh() , mat1 , 2));
		
		renderSys.load(this.registry);
	}
	
	private Runnable r;
	
	private void gameLoop() {
		
		while(!glfwWindowShouldClose(window.getContext())) {
			
			//get deltaTime
			deltaTime =  window.getTime() - time;
			time = window.getTime();
			
			smoothDeltaTime = smoothDeltaTime * (1f - smoothing) + deltaTime * smoothing;
			
			while((this.r = devSB.commandQueue.poll()) != null) {
				
				this.r.run();
			}
			
			
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
			
			renderer.render(sh , textureAtlas , mVao , 100 , 100 , 10000);
			renderer.render(queue);
			
			
			glfwSwapBuffers(window.getContext());
			
		}
	}
	
	
	private void cleanup() {
		
		
		window.dispose();
	}
}