package com.kira.game.components;

import com.kira.game.components.interfaces.Components;

// TODO : REFACTOR
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;

public class TransformComponent implements Components{
	
	public Vector2f position;
	public Vector2f scale;
	
	public Matrix4f transformMatrix;
	public FloatBuffer transform;
	
	public Vector3f forceP;

	public TransformComponent(Vector2f position , Vector2f scale ) {
		
		this.position = position;
		this.scale = scale;
		
		this.forceP = new Vector3f(0f , 0f , -0.5f);
		
		transformMatrix = new Matrix4f();
		transform = BufferUtils.createFloatBuffer(16);
	}
}