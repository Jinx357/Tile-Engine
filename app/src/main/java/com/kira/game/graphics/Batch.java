package com.kira.game.graphics;

import java.util.List;
import java.util.ArrayList;

import com.kira.game.graphics.Material;
import com.kira.game.graphics.RenderCommand;

public class Batch {
	
	public Material material;
	public List<RenderCommand> commands;
	
	public Batch(Material material) {
		
		this.material = material;
		this.commands = new ArrayList<>();
	}
	
	public void add(RenderCommand cmd) {
		
		commands.add(cmd);
	}
	
	public void clear() {
		
		commands.clear();
	}
	
	public void sortCommands() {
		
		commands.sort((a , b) -> {
			
			int shaderA = a.r.material.shader.getShaderProgram();
			int shaderB = b.r.material.shader.getShaderProgram();
			
			if(shaderA != shaderB) return Integer.compare(shaderA , shaderB);
			
			return Integer.compare(
			a.r.material.texture.getTextureId() , 
			b.r.material.texture.getTextureId()
			);
		});
	}
}