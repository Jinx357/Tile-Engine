package com.kira.game.assets;

import static com.kira.game.assets.ShaderType.*;

//ADDING
public class ShaderAssetsManager {
	
	
	
	public static String getShader(ShaderType type) {
		
		return Storage.getShader(type);
	}
}