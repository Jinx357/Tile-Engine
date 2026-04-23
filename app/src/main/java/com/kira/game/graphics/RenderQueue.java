package com.kira.game.graphics;


import java.util.List;
import java.util.ArrayList;

import com.kira.game.graphics.RenderCommand;

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
}