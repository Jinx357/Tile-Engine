package com.kira.game.assets;


//ADDING
public class ShaderAssetsManager {
	
	
	public final static int DEFAULT_VERTEX_SHADER = 0;
	public final static int DEFAULT_PIXEL_SHADER = 1;
	
	private final static String DEFAULT_VERTEX_SHADER_PATH = "/Shaders/vertex.shader";
	private final static String DEFAULT_PIXEL_SHADER_PATH =  "/Shaders/pixel.shader" ;
	
	public static String getDefaultShader(int type) {
		
		if(type == DEFAULT_VERTEX_SHADER) return DEFAULT_VERTEX_SHADER_PATH;
		if(type == DEFAULT_PIXEL_SHADER)  return DEFAULT_PIXEL_SHADER_PATH;
		
		return "NULL";
	}
}