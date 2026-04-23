package com.kira.game.components;

import com.kira.game.graphics.Mesh;
import com.kira.game.graphics.ShaderC;
import com.kira.game.graphics.TextureC;


//this.texture = new TextureC(TextureAssetsManager.getTexture(TextureType.TEST_TEXTURE));

public class RenderableComponent {
	
	public int vao;
	public int shader;
	
	
	public ShaderC shaderC;
	public TextureC textureC;
	
	public float colorE;
	
	public RenderableComponent(int vao , int shader , ShaderC shaderC , TextureC textureC , float colorE) {
		
		this.vao = vao;
		this.shader = shader;
		this.shaderC = shaderC;
		this.textureC = textureC;
		this.colorE = colorE;
	}
	
}