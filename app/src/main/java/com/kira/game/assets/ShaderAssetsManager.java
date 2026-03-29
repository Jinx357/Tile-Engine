package com.kira.game.assets;


//ADDING
class ShaderAssetsManager {
	
	
	public final int DEFAULT_VERTEX_SHADER = 0;
	public final int DEFAULT_PIXEL_SHADER = 1;
	
	private final String DEFAULT_VERTEX_SHADER_PATH = "/Shaders/vertex.shader";
	private final String DEFAULT_PIXEL_SHADER_PATH =  "/Shaders/pixel.shader" ;
	
	public static String getDefaultShader(int type) {
		
		if(type == DEFAULT_VERTEX_SHADER) return DEFAULT_VERTEX_SHADER_PATH;
		if(type == DEFAULT_PIXEL_SHADER)  return DEFAULT_PIXEL_SHADER_PATH;
		
		return "NULL";
	}
}