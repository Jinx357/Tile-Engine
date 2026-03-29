package com.kira.game;

import module com.kira.game;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.NULL;


//ADDING
class Game {
	
	private final Window window;
	private final Renderer renderer;
	
	public Game() {
		
		this.window = new window();
		this.renderer = new Renderer();
	}
	
	public void run() {
		
		gameLoop();
	}
	public void close() {
		
		
	}
	
	private void gameLoop() {
		
		while(!glfwWindowShouldClose(window.getContext())) {
			
			glfwPollEvents();
			
			renderer.clear();
			renderer.draw();
		}
	}
	
	
	private void cleanup() {
		
		window.dispose();
	}
}