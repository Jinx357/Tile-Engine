package com.kira;

import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;

import static org.lwjgl.system.MemoryUtil.NULL;
import org.lwjgl.BufferUtils;


class Mesh {
	
	
	
	private static float[] verts = {
	//    x       y       r     g    b   
		-0.5f , -0.5f ,  0f   , 0f , 0f   ,
		 0.5f , -0.5f ,  0.9f , 0f , 0f   ,
		 0.5f ,  0.5f ,  0f   , 0f , 0f   ,
		-0.5f ,  0.5f ,  0.9f , 0f , 0f 
		
	};
	
	
	
	private static int[] indices = {
		0 , 1 , 2 , //013 123
		0 , 2 , 3
	};
	
	public static int pVao;
	
	public static  void start() {
		
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
		
		int stride = 5 * Float.BYTES;
		
		int ebo = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER , ebo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER , indBuffer , GL_DYNAMIC_DRAW);
		
		//pos
		glVertexAttribPointer(0 , 2 , GL_FLOAT , false , stride , 0);
		
		//col
		glVertexAttribPointer(1 , 3 , GL_FLOAT , false , stride , 2 * Float.BYTES);
		
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		
		glBindVertexArray(0);
		
	}
	
	
}
