package com.kira.game;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import com.kira.game.window.Window;
import com.kira.game.graphics.Renderer;
import static com.kira.game.input.Input.isWireframeOn;
import static com.kira.game.input.Input.keyCalls;

//ADDING
public class Game {
	
	private  Window window;
	private  Renderer renderer;

	public Game() {
		
		this.window = new Window(800 , 800 , "Gaem" , 0 , 0 , 8 , 0);
		this.window.makeWindow();
		this.renderer = new Renderer();
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
			renderer.render();
			
			glfwPollEvents();
			keyCalls(window.getContext());
			
			renderer.setDebugMode(isWireframeOn());
			
			glfwSwapBuffers(window.getContext());
		}
	}
	
	
	private void cleanup() {
		
		window.dispose();
	}
}