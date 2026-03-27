package org.example;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;

import static org.lwjgl.system.MemoryUtil.NULL;

import org.example.Window;

public class App {
	
	private long pWindow;
	private int WIDTH = 800 , HEIGHT = 600 , SAMPLES = 1 ;
	private long SHARING_MODE = 0  , MONITOR = 0;
	private String TITLE = "TILE-ENGINE";
	
	private String VERTEX_SHADER_PATH = "/Shaders/vertex.shader";
	private String PIXEL_SHADER_PATH =  "/Shaders/pixel.shader" ;
	private ShaderC shaderc;
	private int sProg;
	
	private int vao;
   
   private Window window;
   
   
    public static void main(String[] args) {
       
	   var app = new App();
	   app.run();
    }
	
	public void run() {
		
		getWindow();
		getShaders();
		getMesh();
		gameLoop();
		cleanup();
	}
	
	private void getWindow(){
		
		window = new Window(WIDTH , HEIGHT , TITLE , MONITOR , SHARING_MODE , SAMPLES);
		pWindow = window.getWindowHandle();
		
	}
	
	private void getShaders() {
		
		shaderc = new ShaderC();
		shaderc.setShaders(VERTEX_SHADER_PATH , PIXEL_SHADER_PATH);
		sProg = shaderc.sProgram;
	}
	
	private void getMesh() {
		
		Mesh.start();
		vao = Mesh.pVao;
	}
	
	private void gameLoop() {
		
		while(!glfwWindowShouldClose(pWindow)) {
			glfwPollEvents();
			
			
			//glClear(GL_COLOR_BUFFER_BIT);
			
			glUseProgram(sProg);
			
			glBindVertexArray(vao);
			glDrawElements(GL_TRIANGLES , 3 , GL_UNSIGNED_INT , 0);
			glBindVertexArray(0);
			
			glfwSwapBuffers(pWindow);
		}
	}
	
	private void cleanup() {
		
		window.dispose();
	}
}
