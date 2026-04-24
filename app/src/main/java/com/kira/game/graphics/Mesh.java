package com.kira.game.graphics;

import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;

import static org.lwjgl.system.MemoryUtil.NULL;
import org.lwjgl.BufferUtils;

import com.kira.game.graphics.TextureC;
import com.kira.game.assets.TextureType;
import com.kira.game.assets.TextureAssetsManager;

//REFACTORED?
public class Mesh {
	
	private TextureC texture;
	private float aspectRatio;
	private float halfW;
	private float halfH;

	public Mesh(TextureC texture) {
			
		this.texture = texture;
		
		aspectRatio = (float) texture.getWidth() / texture.getHeight();
		halfW = 0.5f * aspectRatio;
	    halfH = 0.5f;
		
	}

	
	private float[] verts = {
	//    x       y       u     v  
		-halfW , -halfH ,  0f   , 0f  , //0
		 halfW , -halfH ,  1f   , 0f  , //1
		 halfW ,  halfH ,  1f   , 1f  , //2
		-halfW ,  halfH ,  0f   , 1f    //3
		
	};
	
	private int[] indices = {
		0 , 1 , 2 , //013 123
		0 , 2 , 3
	};
	
	private int pVao;
	
	
	
	
	public int createMesh() {
		
		
		//make buffers and feed arrays 
		var vertBuffer = BufferUtils.createFloatBuffer(verts.length);
		vertBuffer.put(verts).flip();
		
		var indBuffer = BufferUtils.createIntBuffer(indices.length);
		indBuffer.put(indices).flip();
		
		//bind vao
		int vao = glGenVertexArrays();
		pVao = vao;
		glBindVertexArray(vao); 
		
		//pos
		int Vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER , Vbo);
		glBufferData(GL_ARRAY_BUFFER , vertBuffer , GL_DYNAMIC_DRAW);
		
		//stride
		int stride = 4 * Float.BYTES;
		
		//ebo
		int ebo = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER , ebo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER , indBuffer , GL_DYNAMIC_DRAW);
		
		//pos
		glVertexAttribPointer(0 , 2 , GL_FLOAT , false , stride , 0);
		
		//col
		glVertexAttribPointer(1 , 2 , GL_FLOAT , false , stride , 2 * Float.BYTES);
		
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		
		glBindVertexArray(0);
		
		
		System.out.println("v"+vao);
		return vao;
	}
	
	
}
