package com.kira.game.physics;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;

//TODO: refactor
public class Movement {
	
	private static FloatBuffer transformationBuffer = BufferUtils.createFloatBuffer(16);
	
	private static Matrix4f transform = new Matrix4f();
	
	public static FloatBuffer getTransformationBuffer() {
		
		return transformationBuffer;
	}
	
	public static void moveX(float displacementX) {

		
		
		transform.translate(new Vector3f(displacementX , 0f , 0f));
		
		transform.get(transformationBuffer);
		//System.out.println(transform); 
		
	}
	
	public static void moveY(float displacementY) {

		
		
		transform.translate(new Vector3f(0f , displacementY , 0f));
		
		transform.get(transformationBuffer);
		//System.out.println(transform); 
		
	}
	
	

}