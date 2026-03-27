package org.example;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;

class Input {
	
	private static boolean wireframe = false;
	private static long pWindow;
	
	
	public static void kblSetWindow(long window) {
		
		pWindow = window;
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
		
	}
}