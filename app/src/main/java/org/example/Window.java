package org.example;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;
import static imgui.ImGui.*;
import static org.lwjgl.system.MemoryUtil.NULL;

class Window {
	
	private long pWindow;
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
		
		glfwWindowHint(GLFW_VISIBLE , GLFW_TRUE);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR , 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR , 3);
		glfwWindowHint(GLFW_OPENGL_PROFILE , GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_SAMPLES , SAMPLES);
		
		pWindow = glfwCreateWindow(800 , 600 , TITLE , MONITOR , SHARING_MODE);
		
		if(pWindow == NULL) throw new RuntimeException("null windpw");
		
		glfwMakeContextCurrent(pWindow);
		
		GL.createCapabilities();
		glEnable(GL_MULTISAMPLE);
		
		glClearColor(0.0f,0.0f,0.0f,1.0f);
	}
	
	public void showWindow() {
		
		glfwShowWindow(pWindow);
	}
	
	public long getWindowHandle() {
		
		return pWindow;
	}
	
	public void dispose() {
		
		glfwDestroyWindow(pWindow);
		glfwTerminate();
	}
}