package org.example;

import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;

import static org.lwjgl.system.MemoryUtil.NULL;
import org.lwjgl.BufferUtils;


class Mesh {
	
	private static float[] verts = {
		0.0f , 0.0f , 
		0.5f , 0.5f ,
	   -0.5f , 0.5f	
	};
	
	public static int pVao;
	
	public static  void start() {
		
		var vertBuffer = BufferUtils.createFloatBuffer(verts.length);
		vertBuffer.put(verts).flip();
		
		int vao = glGenVertexArrays();
		pVao = vao;
		glBindVertexArray(vao);
		
		int vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER , vbo);
		glBufferData(GL_ARRAY_BUFFER , vertBuffer , GL_DYNAMIC_DRAW);
		
		glVertexAttribPointer(0 , 2 , GL_FLOAT , false , 0 , 0);
		glEnableVertexAttribArray(0);
		
		glBindVertexArray(0);
	}
	
	
}
