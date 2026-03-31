package com.kira.game.input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;

import com.kira.game.entities.Entity;

import static com.kira.game.physics.Movement.moveX;
import static com.kira.game.physics.Movement.moveY;
//TODO: refactor , A LOT
public class Input {
	
	private static boolean wireframe = false;
	private static boolean debug = false;
	
	public static boolean isKeyDown(long window , int key) {
		
		return glfwGetKey(window , key) == GLFW_PRESS;
	}
	
	public static boolean isWireframeOn() {
		
		return wireframe;
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
	
	public static void keyCalls(long pWindow , Entity entity) {
		
		if(isKeyDown(pWindow , GLFW_KEY_D)) entity.moveEntityX((byte)1);
		if(isKeyDown(pWindow , GLFW_KEY_A)) entity.moveEntityX((byte)-1);
		if(isKeyDown(pWindow , GLFW_KEY_W)) entity.moveEntityY((byte)1);
		if(isKeyDown(pWindow , GLFW_KEY_S)) entity.moveEntityY((byte)-1);
		
	}
	
}