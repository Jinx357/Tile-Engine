package com.kira.game.components;

import com.kira.game.graphics.Mesh;
import com.kira.game.graphics.ShaderC;
import com.kira.game.graphics.TextureC;
import com.kira.game.graphics.Material;

public class RenderableComponent {
	
	public int vao;
	public int shader;
	
	public Material material;
	
	public RenderableComponent(int vao , Material material) {
		
		this.vao = vao;
		this.material = material;
	}
	
}