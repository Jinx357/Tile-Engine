package com.kira;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import static com.kira.Input.*;


public class App {
	
	private Window window;
	private long pWindow;
	private int WIDTH = 800 , HEIGHT = 600 , SAMPLES = 8 ;
	private long SHARING_MODE = 0  , MONITOR = 0;
	private String TITLE = "TILE-ENGINE";
	
	private String VERTEX_SHADER_PATH = "/Shaders/vertex.shader";
	private String PIXEL_SHADER_PATH =  "/Shaders/pixel.shader" ;
	private ShaderC shaderc;
	private int sProg;
	
	private int vao;
   
   private float deltaTime;
   private float currentTime = 0f;
   private float previousTime;
   
   private int uTimeLocation;
   private int uTransformLocation;
   
   
    public static void main(String[] args) {
       
	   var app = new App();
	   app.run();
    }
	
	public void run() {
		
		getWindow();
		getShaders();
		getMesh();
		//move(currentTime);
		gameLoop();
		cleanup();
	}
	
	private void getWindow(){
		
		window = new Window(WIDTH , HEIGHT , TITLE , MONITOR , SHARING_MODE , SAMPLES);
		pWindow = window.pWindow;
		
	   previousTime = window.time;
	}
	
	private void getShaders() {
		
		shaderc = new ShaderC();
		shaderc.setShaders(VERTEX_SHADER_PATH , PIXEL_SHADER_PATH);
		sProg = shaderc.sProgram;
		uTimeLocation = shaderc.uTimeLocation;
		uTransformLocation = shaderc.uTransformLocation;
	}
	
	private void getMesh() {
		
		Mesh.start();
		vao = Mesh.pVao;
	}
	
	private void move(float currentTime) {
		
		//int time = (int) currentTime;
		//Movement.rot(time);
	}
	
	private void gameLoop() {
		
		while(!glfwWindowShouldClose(pWindow)) {
			glfwPollEvents();
			kblPollInputs();
			
			glClear(GL_COLOR_BUFFER_BIT);
			
			glUseProgram(sProg);
			
			  currentTime = window.getCurrentTime();
		      deltaTime = currentTime - previousTime;
			  previousTime = currentTime;
			  
			 
			 
			
			glUniform1f(uTimeLocation , (float)glfwGetTime());
			glUniformMatrix4fv(uTransformLocation , false , Movement.trBuffer);
			
			glBindVertexArray(vao);
			
			glDrawElements(GL_TRIANGLES , 6 , GL_UNSIGNED_INT , 0L);
			
			glBindVertexArray(0);
			
			glfwSwapBuffers(pWindow);
		}
	}
	
	private void cleanup() {
		
		window.dispose();
	}
}
