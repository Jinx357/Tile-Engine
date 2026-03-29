// ADDING
module com.kira.game {
	
	
	
	requires org.lwjgl;
	requires org.lwjgl.glfw;
	requires org.lwjgl.opengl;
	requires org.joml;
	requires org.lwjgl.stb;
	
	
	
	exports com.kira.game.core;
	exports com.kira.game.graphics;
	exports com.kira.game.input;
	exports com.kira.game.window;
	exports com.kira.game.ui;
	exports com.kira.game.physics;
	exports com.kira.game.entities;
	exports com.kira.game.assets;
	exports com.kira.game.utils;
	
	
}