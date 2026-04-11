package com.kira.game.input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;

import com.kira.game.input.Keys;
//TODO: refactor , A LOT
public class Input {
	
	private static boolean wireframe = false;
	private static boolean debug = false;
	private static long pWindow;
	
	
	public static boolean isWireframeOn() {
		
		return wireframe;
	}
	
	public static void setCurrentWindow(long pW) {
		
		pWindow = pW;
	}
	
	
	
	
	//callbacks - non util
	public static void kblPollEvents(long pWindow) {
		
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
	
	
	public static boolean isKeyPressed(Keys key) {
		
		GLFW.glfwInit();
		return glfwGetKey(pWindow , key.glfwKey) == GLFW_PRESS;
	}
	
}