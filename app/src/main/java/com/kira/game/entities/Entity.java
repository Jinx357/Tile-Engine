package com.kira.game.entities;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;


import static com.kira.game.physics.Movement.moveX;
import static com.kira.game.physics.Movement.moveY;

import com.kira.game.graphics.Mesh;

//TODO: implement

public class Entity {
	
	private int entityID;
	private int vao;
	
	private Mesh mesh;
	
	private FloatBuffer transformBuffer;
	
	private Matrix4f transform = new Matrix4f();
	
	public Entity(int id) {
		
		entityID = id;
		mesh = new Mesh();
		vao = mesh.getVertexArrayObject();
		transformBuffer = BufferUtils.createFloatBuffer(16);
		
		show();
	}
	
	private void show() {
		
		this.getTransform().get(this.getTransformBuffer());
	}
	
	public void moveEntityX(byte direction) {
		
		moveX(this , direction * 0.001f);
	}
	
	public void moveEntityY(byte direction) {
		
		moveY(this , direction * 0.001f);
	}
	
	public FloatBuffer getTransformBuffer() {
		
		return transformBuffer;
	}
	
	public Matrix4f getTransform() {
		
		return transform;
	}
	
	public int getVao() {
		
		return vao;
	}
	
	/*
	
	*/
}