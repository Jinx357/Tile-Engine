package org.example;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;
import static imgui.ImGui.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.example.Window;

public class App {
	
	private long pWindow;
	private int WIDTH = 800 , HEIGHT = 600 , SAMPLES = 1 ;
	private long SHARING_MODE = 0  , MONITOR = 0;
	private String TITLE = "TILE-ENGINE";
   
   private Window window;
   private DevWidgets widgets;
   
    public static void main(String[] args) {
       
	   var app = new App();
	   app.run();
    }
	
	public void run() {
		
		getWindow();
		gameLoop();
		cleanup();
	}
	
	private void getWindow(){
		
		window = new Window(800 , 600 , TITLE , MONITOR , SHARING_MODE , SAMPLES);
		pWindow = window.getWindowHandle();
		//window.showWindow(); SET GLFW_VISIBLE TO GLFW_TRUE FOR NOW
		
		widgets = new DevWidgets();
		widgets.init(pWindow);
	}
	
	private void gameLoop() {
		
		while(!glfwWindowShouldClose(pWindow)) {
			glfwPollEvents();
			
			widgets.newFrame();
			widgets.update();
			
			glClear(GL_COLOR_BUFFER_BIT);
			
			widgets.render();
			
			glfwSwapBuffers(pWindow);
		}
	}
	
	private void cleanup() {
		
		window.dispose();
		widgets.dispose();
	}
}
