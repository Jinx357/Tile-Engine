package com.kira.game.graphics.rendering;


import java.util.List;
import java.util.ArrayList;

import com.kira.game.graphics.rendering.RenderCommand;

public class RenderQueue {
	
	private List<RenderCommand> renderChain;
	private int capacity = 200;
	
	public RenderQueue(int capacity) {
		
		if(capacity == 0);
		else this.capacity = capacity;
		renderChain = new ArrayList<>(this.capacity);
	}
	
	public void submit(RenderCommand cmd) {
		
		renderChain.add(cmd);
	}
	
	public List<RenderCommand> getRenderCommands() {
		
		return renderChain;
	}
	
	public void flush(int index) {
		
		renderChain.remove(index);
	}
	
	public int getCurrentIndex() {
		
		return renderChain.size() - 1;
	}
	
	public void clear() {
		
		renderChain.clear();
	}
	
	public void sortRenderCommandChain() {
		
		renderChain.sort((a , b) -> {
			
			int shaderA = a.r.material.shader.getShaderProgram();
			int shaderB = b.r.material.shader.getShaderProgram();
			
			if(shaderA != shaderB) return Integer.compare(shaderA , shaderB);
			
			int textureA = a.r.material.texture.getTextureId(); 
			int textureB = b.r.material.texture.getTextureId();
			
			if(textureA != textureB) return Integer.compare(textureA , textureB);
			
			return Integer.compare(a.r.renderPriority , b.r.renderPriority);
		});
	}
}