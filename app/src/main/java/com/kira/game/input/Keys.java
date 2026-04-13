package com.kira.game.input;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFW;

public enum Keys {
	
	
	A(GLFW.GLFW_KEY_A) , 
	B(GLFW.GLFW_KEY_B) ,
	C(GLFW.GLFW_KEY_C) , 
	D(GLFW.GLFW_KEY_D) , 
	S(GLFW.GLFW_KEY_S) , 
	W(GLFW.GLFW_KEY_W) ; 
	
	public final int glfwKey;
	
	Keys(int glfwKey) {
		this.glfwKey = glfwKey;
	}
}