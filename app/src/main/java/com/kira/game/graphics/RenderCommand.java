package com.kira.game.graphics;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import com.kira.game.graphics.Renderer;

import com.kira.game.components.TransformComponent;
import com.kira.game.components.RenderableComponent;

import org.joml.Matrix4f;

public class RenderCommand {
	
	public TransformComponent t;
	public RenderableComponent r;
	
	public RenderCommand(RenderableComponent r , TransformComponent t) {
		
		this.r = r;
		this.t = t;
	}

}