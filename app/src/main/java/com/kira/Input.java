package com.kira;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;

import static com.kira.Movement.*;

class Input {
	
	private static boolean wireframe = false;
	private static long pWindow;
	
	
	public static void kblSetWindow(long window) {
		
		pWindow = window;
	}
	
	public static boolean isKeyDown(long window , int key) {
		
		return glfwGetKey(window , key) == GLFW_PRESS;
	}
	
	// holds - non util
	public static void kblPollInputs() {
		
		Input.keyCalls();
	}
	
	//callbacks - non util
	public static void kblPollEvents() {
		
		glfwSetKeyCallback(pWindow , (win , key , scancode , action , mods) -> {
			
			if(action == GLFW_PRESS) {
				
			//-----------------------------------UTILS--------------------------------------------------------
			{
			if((key == GLFW_KEY_SPACE || key == GLFW_KEY_ESCAPE)) glfwSetWindowShouldClose(pWindow , true); //exit - end 
			
		    
			if(key == GLFW_KEY_BACKSPACE ) { //debug - wireframe mode
				wireframe = !wireframe;
				
				if(wireframe){
					
				glPolygonMode(GL_FRONT_AND_BACK , GL_LINE);
				glUseProgram(0);
				glColor3f(0f , 1f , 0f);
				
				System.out.println("Wireframe : ON"); }
				
				else{
				glPolygonMode(GL_FRONT_AND_BACK , GL_FILL);
				System.out.println("Wireframe : OFF"); }
			}
			}
			//------------------------------------------------------------------------------------------------	
			
			}
		});
		
	}
	
	private static void keyCalls() {
		
		if(isKeyDown(pWindow , GLFW_KEY_D)) moveX(0.001f);
		if(isKeyDown(pWindow , GLFW_KEY_A)) moveX(-0.001f);
		if(isKeyDown(pWindow , GLFW_KEY_W)) moveY(0.001f);
		if(isKeyDown(pWindow , GLFW_KEY_S)) moveY(-0.001f);
		
	}
}