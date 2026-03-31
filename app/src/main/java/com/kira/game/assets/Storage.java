package com.kira.game.assets;

import java.util.EnumMap;
import java.util.Map;

import static com.kira.game.assets.ShaderType.*;

//Refactor
public class Storage{
	
	private static final Map<ShaderType , String> shaderStorage;
	
	//TODO: implement this
	//private static final Map<TextureType , String> textureStorage;
	
	static {
		shaderStorage = new EnumMap<>(ShaderType.class);
		
		shaderStorage.put(DEFAULT_VERTEX_SHADER , "/shaders/default/default_vertex.shader");
		shaderStorage.put(DEFAULT_PIXEL_SHADER , "/Shaders/default/default_pixel.shader" );
		shaderStorage.put(DEBUG_VERTEX_SHADER , "/shaders/debug/vertex.debugShader");
		shaderStorage.put(DEBUG_PIXEL_SHADER , "/Shaders/debug/pixel.debugShader");
	}
	
	public static void store(ShaderType type , String path) {
		
		shaderStorage.put(type , path);
	}
	
	public static String getShader(ShaderType type) {
		
		return shaderStorage.get(type);
	}
}