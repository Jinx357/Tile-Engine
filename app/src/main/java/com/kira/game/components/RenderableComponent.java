package com.kira.game.components;

import com.kira.game.graphics.resources.Mesh;
import com.kira.game.graphics.resources.ShaderC;
import com.kira.game.graphics.resources.TextureC;
import com.kira.game.graphics.resources.Material;

public class RenderableComponent {
	
	public int vao;
	public int shader;
	public int renderPriority;
	
	public Material material;
	
	public RenderableComponent(int vao , Material material , int renderPriority) {
		
		this.vao = vao;
		this.material = material;
		this.renderPriority = renderPriority;
	}
	
}