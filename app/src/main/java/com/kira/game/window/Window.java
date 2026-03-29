package com.kira.game.window;


import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import static com.kira.Input.*;

//REFACTORED
class Window {
	
	private final long pWindow;
	
	//TODO: refactor this 
	//--->public float time;
	
	
	private int WIDTH;
	private int HEIGHT;
	private int SAMPLES;
	private int SWAP_INTERVAL;
	
	private long SHARING_MODE;
	private long MONITOR;
	
	
	private String TITLE;
	
	
	
	public Window(int WIDTH , int HEIGHT , String TITLE ,  long MONITOR , long SHARING_MODE , int SAMPLES , int SWAP_INTERVAL) {
		
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.TITLE = TITLE;
		this.MONITOR = MONITOR;
		this.SHARING_MODE = SHARING_MODE;
		this.SAMPLES = SAMPLES;
		this.SWAP_INTERVAL = SWAP_INTERVAL;
		
		makeWindow();
	}
	
	public long getContext() {
		
		return pWindow;
	}
	
	//TODO: refactor 
	public float getCurrentTime() {
		
		return (float)glfwGetTime();
	}
	
	public void dispose() {
		
		glfwDestroyWindow(pWindow);
		glfwTerminate();
	}
	
	private void makeWindow() {
		
		if(!glfwInit()) throw new RuntimeException("glfw cannot inititalise");
		
		
		
		// window hints
		glfwWindowHint(GLFW_VISIBLE , GLFW_FALSE);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR , 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR , 3);
		glfwWindowHint(GLFW_OPENGL_PROFILE , GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_SAMPLES , SAMPLES);
		
		
		
		pWindow = glfwCreateWindow(WIDTH , HEIGHT , TITLE , MONITOR , SHARING_MODE);
		if(pWindow == NULL) throw new RuntimeException("null window");
		
		
		glfwMakeContextCurrent(pWindow);
		
		
		if(SWAP_INTERVAL != NULL) glfwSwapInterval(SWAP_INTERVAL);
		
		//GL 
		GL.createCapabilities();
		glEnable(GL_MULTISAMPLE);
		glDisable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		glFrontFace(GL_CCW);
		glClearColor(0.0f,0.0f,0.0f,1.0f);
		glPolygonMode(GL_FRONT_AND_BACK , GL_FILL);
		
		
		//show the window
		glfwShowWindow(pWindow);
		
		
		//TODO: refactor this 
		//-->kblSetWindow(pWindow);
		//-->kblPollEvents();
	}
	
}