package com.kira.game.assets;

import static com.kira.game.assets.TextureType.*;

//ADDING
public class TextureAssetsManager {
	
	
	
	public static String getTexture(TextureType type) {
		
		return Storage.getTexture(type);
	}
}