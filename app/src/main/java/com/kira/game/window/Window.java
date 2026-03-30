package com.kira.game.window;


import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
//import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.NULL;


//REFACTORED
public class Window {
	
	private long pWindow;
	
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
	}
	
	public long getContext() {
		
		return pWindow;
	}
	
	//TODO: refactor 
	public float getCurrentTime() {
		
		return (float)glfwGetTime();
	}
	
	public void makeWindow() {
		
		if(!glfwInit()) throw new RuntimeException("glfw cannot inititalise");
		
		
		
		// window hints
		glfwWindowHint(GLFW_VISIBLE , GLFW_FALSE);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR , 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR , 3);
		glfwWindowHint(GLFW_OPENGL_PROFILE , GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_SAMPLES , this.SAMPLES);
		
		
		
		this.pWindow = glfwCreateWindow(WIDTH , HEIGHT , TITLE , MONITOR , SHARING_MODE);
		if(pWindow == 0) throw new RuntimeException("null window");
		
		
		glfwMakeContextCurrent(this.pWindow);
		glfwSwapInterval(SWAP_INTERVAL);
		
		//GL 
		GL.createCapabilities();
		
		
		glfwSetFramebufferSizeCallback(pWindow , (win , width , height) -> {
			
			float targetAspectRatio = 1f;
			float windowAspectRatio = (float) (width / height);
			
			int viewportWidth = 0 , viewportHeight = 0;
			
			if(windowAspectRatio > targetAspectRatio) {
				
				viewportHeight = height;
				viewportWidth = (int) (height * targetAspectRatio);
			}
			else {
				
				viewportHeight = width;
				viewportWidth = (int) (width / targetAspectRatio);
			}
			
			int viewportX = (width - viewportWidth) / 2;
			int viewportY = (height - viewportHeight) / 2;
			
		glViewport(viewportX , viewportY , viewportWidth , viewportHeight);
		});
		
		
		glEnable(GL_MULTISAMPLE);
		glDisable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		glFrontFace(GL_CCW);
		glClearColor(0f,0f,0f,1f);
		//glPolygonMode(GL_FRONT_AND_BACK , GL_FILL);
		
		
		//show the window
		glfwShowWindow(pWindow);
		
		
		int err = glGetError();
		if( err != GL_NO_ERROR) System.out.println("err gl: " + Integer.toHexString(err));
		
		//TODO: refactor this 
		//-->kblSetWindow(pWindow);
		//-->kblPollEvents();
	}
	
	
	public void dispose() {
		
		glfwDestroyWindow(pWindow);
		glfwTerminate();
	}
	
	
}