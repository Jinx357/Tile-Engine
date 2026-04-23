package com.kira.game.components;

import com.kira.game.graphics.Mesh;
import com.kira.game.graphics.ShaderC;


public class RenderableComponent {
	
	//public float[] mesh;
	//public int[] meshBuilder;
	
	public int vao;
	public int shader;
	public ShaderC shaderC;
	
	public RenderableComponent(int vao , int shader , ShaderC shaderC) {
		
		this.vao = vao;
		this.shader = shader;
		this.shaderC = shaderC;
	}
	
}