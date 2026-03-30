package com.kira.game.input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;

import static com.kira.game.physics.Movement.moveX;
import static com.kira.game.physics.Movement.moveY;
//TODO: refactor , A LOT
public class Input {
	
	private static boolean wireframe = false;
	
	
	public static boolean isKeyDown(long window , int key) {
		
		return glfwGetKey(window , key) == GLFW_PRESS;
	}
	
	// holds - non util
	
	
	
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
	
	public static void keyCalls(long pWindow) {
		
		if(isKeyDown(pWindow , GLFW_KEY_D)) moveX(0.001f);
		if(isKeyDown(pWindow , GLFW_KEY_A)) moveX(-0.001f);
		if(isKeyDown(pWindow , GLFW_KEY_W)) moveY(0.001f);
		if(isKeyDown(pWindow , GLFW_KEY_S)) moveY(-0.001f);
		
	}
	
}