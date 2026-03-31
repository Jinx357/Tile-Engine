package com.kira.game.physics;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;

import com.kira.game.entities.Entity;

//TODO: refactor
public class Movement {
	
	public static void moveX(Entity entity , float displacementX) {

		
		
		entity.getTransform().translate(new Vector3f(displacementX , 0f , 0f));
		
		entity.getTransform().get(entity.getTransformBuffer()); 
	}
	
	public static void moveY(Entity entity , float displacementY) {

		
		
		entity.getTransform().translate(new Vector3f(0f , displacementY , 0f));
		
		entity.getTransform().get(entity.getTransformBuffer());
	}
	
	

}