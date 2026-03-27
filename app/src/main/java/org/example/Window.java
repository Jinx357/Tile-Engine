package org.example;


import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import static org.example.Input.*;


class Window {
	
	public double time;
	
	public long pWindow;
	private int WIDTH , HEIGHT , SAMPLES ;
	private long SHARING_MODE , MONITOR ;
	private String TITLE;
	
	
	
	public Window(int w , int h , String t ,  long m , long sm , int sam) {
		
		WIDTH = w;
		HEIGHT = h;
		TITLE = t;
		MONITOR = m;
		SHARING_MODE = sm;
		SAMPLES = sam;
		
		makeWindow();
	}
	
	private void makeWindow() {
		
		if(!glfwInit()) throw new RuntimeException("glfw cannot inititalise");
		
		glfwWindowHint(GLFW_VISIBLE , GLFW_FALSE);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR , 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR , 3);
		glfwWindowHint(GLFW_OPENGL_PROFILE , GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_SAMPLES , SAMPLES);
		
		pWindow = glfwCreateWindow(WIDTH , HEIGHT , TITLE , MONITOR , SHARING_MODE);
		
		if(pWindow == NULL) throw new RuntimeException("null windpw");
		
		glfwMakeContextCurrent(pWindow);
		//glfwSwapInterval(1);
		
		GL.createCapabilities();
		glEnable(GL_MULTISAMPLE);
		glDisable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		glFrontFace(GL_CCW);
		
		glClearColor(0.0f,0.0f,0.0f,1.0f);
		glfwShowWindow(pWindow);
		
		time = glfwGetTime();
		
		glPolygonMode(GL_FRONT_AND_BACK , GL_FILL);
		
		kblSetWindow(pWindow);
		kblPollEvents();
	}
	
	public void dispose() {
		
		glfwDestroyWindow(pWindow);
		glfwTerminate();
	}
}