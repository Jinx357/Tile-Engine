package com.kira;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;


class Movement {
	
	public static FloatBuffer trBuffer = BufferUtils.createFloatBuffer(16);
	
	private static Matrix4f transform = new Matrix4f();
	
	
	
	public static void moveX(float displacementX) {

		
		
		transform.translate(new Vector3f(displacementX , 0f , 0f));
		
		transform.get(trBuffer);
		//System.out.println(transform); 
		
	}
	
	public static void moveY(float displacementY) {

		
		
		transform.translate(new Vector3f(0f , displacementY , 0f));
		
		transform.get(trBuffer);
		//System.out.println(transform); 
		
	}
	

	public static void rot(int time) {
		
		float speed = 0.1f;
		float max = 0.1f , min = 0.01f;
		
		float angle = (float)Math.max(min , Math.min( max , (Math.toRadians(time) * speed)));
		System.out.println(angle);
		//transform.translate(new Vector3f(0f , 0f , 0f));
		
		transform.rotateZ(angle);
		
		transform.get(trBuffer);
		//System.out.println(transform);
	}
}