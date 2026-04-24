package com.kira.game.graphics;

import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL30.*;

import com.kira.game.graphics.ShaderC;
import com.kira.game.graphics.TextureC;

public class Material {
	
	public ShaderC shader;
	public TextureC texture;
	
	private float tint;
	
	
	public Material(ShaderC shader , TextureC texture) {
		
		this.shader = shader;
		this.texture = texture;
	}
	
	public void apply() {
		
		glUseProgram(shader.getShaderProgram());
		glActiveTexture(GL_TEXTURE0);
		
		texture.bind();
		{
			glUniform1i(shader.getUniformTextureLocation() , 0);
		}
	}
	
	public ShaderC getShader() {return shader;}
	public TextureC getTexture() {return texture;}
}