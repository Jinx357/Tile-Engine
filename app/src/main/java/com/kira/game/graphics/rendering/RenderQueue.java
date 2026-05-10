package com.kira.game.graphics.rendering;


import java.util.List;
import java.util.ArrayList;

import com.kira.game.graphics.rendering.RenderCommand;

public class RenderQueue {
	
	private List<RenderCommand> entityRenderChain;
	private List<RenderCommand> backgroundRenderChain;
	
	private int capacity = 200;
	
	public RenderQueue(int capacity) {
		
		if(capacity == 0);
		else this.capacity = capacity;
		
		entityRenderChain = new ArrayList<>(this.capacity);
		backgroundRenderChain = new ArrayList(this.capacity * 2);
	}
	
	public void submit(RenderCommand cmd) {
		
		entityRenderChain.add(cmd);
	}
	
	public List<RenderCommand> getRenderCommands(int queueFamily) {
		
		if(queueFamily == 1)
		return backgroundRenderChain;
	
		if(queueFamily== 2)
		return entityRenderChain;
		
		else throw new RuntimeException("invalid render queue family : cannot resolve type -> \' " + queueFamily + " \'");
	}
	
	public void flush(int index) {
		
		entityRenderChain.remove(index);
	}
	
	public int getCurrentIndex() {
		
		return entityRenderChain.size() - 1;
	}
	
	public void clear() {
		
		entityRenderChain.clear();
	}
	
	//ecs
	public void sortRenderCommandChain(int queueFamily) {
		
		if(queueFamily == 1) {
			
			backgroundRenderChain.sort((a , b) -> {
				
			int shaderA = a.sC.getShaderProgram();
			int shaderB = b.sC.getShaderProgram();
			
			if(shaderA != shaderB) return Integer.compare(shaderA , shaderB);
			
			int textureA = a.tC.getTextureId(); 
			int textureB = b.tC.getTextureId();
			
			if(textureA != textureB) return Integer.compare(textureA , textureB);
			
			//return Integer.compare(a.r.renderPriority , b.r.renderPriority);
		});
		}
		
		if(queueFamily == 2) {
			
		entityRenderChain.sort((a , b) -> {
			
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
	
	
}