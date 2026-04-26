package com.kira.game.graphics.rendering;

import java.util.List;
import java.util.ArrayList;

import com.kira.game.graphics.resources.Material;
import com.kira.game.graphics.rendering.RenderCommand;

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
	
	
}