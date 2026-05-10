package com.kira.game.graphics.rendering;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import com.kira.game.graphics.rendering.Renderer;
import com.kira.game.graphics.resources.Mesh;
import com.kira.game.graphics.resources.ShaderC;
import com.kira.game.graphics.resources.TextureC;

import com.kira.game.components.TransformComponent;
import com.kira.game.components.RenderableComponent;

import org.joml.Matrix4f;

public class RenderCommand {
	
	public TransformComponent t;
	public RenderableComponent r;
	
	public Mesh m;
	public TextureC tC;
	public ShaderC sC;
	
	public int commandQueueFamily;
	
	//0-invalid
	//1-map , background
	//2-ecs
	//3-ui
	
	public RenderCommand(RenderableComponent r , TransformComponent t) {
		
		this.commandQueueFamily = 2;
		
		this.r = r;
		this.t = t;
		
		this.m = null;
		this.tC = null;
		this.sC = null;
	}
	
	

	public RenderCommand(Mesh m , TextureC tC , ShaderC sC) {
		
		this.commandQueueFamily = 1;
		
		this.m = m;
		this.tC = tC;
		this.sC = sC;
		
		this.t = null;
		this.r = null;
	}
}