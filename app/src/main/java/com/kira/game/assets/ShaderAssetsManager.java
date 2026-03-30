package com.kira.game.assets;


//ADDING
public class ShaderAssetsManager {
	
	
	public final static int DEFAULT_VERTEX_SHADER = 0;
	public final static int DEFAULT_PIXEL_SHADER = 1;
	public final static int DEBUG_VERTEX_SHADER = -2;
	public final static int DEBUG_PIXEL_SHADER = -1;
	
	private final static String DEFAULT_VERTEX_SHADER_PATH = "/shaders/default/default_vertex.shader";
	private final static String DEFAULT_PIXEL_SHADER_PATH =  "/Shaders/default/default_pixel.shader" ;
	
	private final static String DEBUG_VERTEX_SHADER_PATH = "/shaders/debug/vertex.debugShader";
	private final static String DEBUG_PIXEL_SHADER_PATH =  "/Shaders/debug/pixel.debugShader" ;
	
	public static String getShader(int type) {
		
		if(type == DEFAULT_VERTEX_SHADER) return DEFAULT_VERTEX_SHADER_PATH;
		if(type == DEFAULT_PIXEL_SHADER)  return DEFAULT_PIXEL_SHADER_PATH;
		if(type == DEBUG_VERTEX_SHADER) return DEBUG_VERTEX_SHADER_PATH;
		if(type == DEBUG_PIXEL_SHADER)  return DEBUG_PIXEL_SHADER_PATH;
		
		return "NULL";
	}
}