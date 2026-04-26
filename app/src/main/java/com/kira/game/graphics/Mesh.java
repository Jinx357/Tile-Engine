package com.kira.game.graphics;

import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;

import static org.lwjgl.system.MemoryUtil.NULL;
import org.lwjgl.BufferUtils;

import com.kira.game.graphics.TextureC;
import com.kira.game.graphics.SpriteRegion;
import com.kira.game.assets.TextureType;
import com.kira.game.assets.TextureAssetsManager;

//REFACTORED?
public class Mesh {
	
	private int pVao;
	
	
	private TextureC texture;
	private float aspectRatio;
	private float halfW;
	private float halfH;
	
	private float[] vert  = new float[16];
	private int[] ind  = new int[6];

	public Mesh(TextureC texture) {
			
		this.texture = texture;
		
		
		float w = texture.getWidth();
		float h = texture.getHeight();
		 
		 float[] verts = {
		//  x     y    u     v  
			0f , 0f , 0f   , 0f , //0
			 w , 0f , 1f   , 0f , //1
			 w ,  h , 1f   , 1f , //2
			0f ,  h , 0f   , 1f   //3
		
			};
	
		 int[] indices = {
		0 , 1 , 2 , //013 123
		0 , 2 , 3
		};
		
		vert = verts;
		ind = indices;
	
	}
	
	public Mesh(SpriteRegion region) {
		
		float w = region.width;
		float h = region.height;
		
		float[] verts  = {
		//  x    y     u          v 
		   0f , 0f , region.u0 , region.v1 , 
		    w , 0f , region.u1 , region.v1 , 
			w ,  h , region.u1 , region.v0 , 
		   0f ,  h , region.u0 , region.v0
			
		};
		
		int[] indices = {	
			0 , 1 , 2 ,
			0 , 2 , 3
		};
		
		vert = verts;
		ind = indices;
	}
	
	
	public int createMesh() {
		
		
		//make buffers and feed arrays 
		var vertBuffer = BufferUtils.createFloatBuffer(vert.length);
		vertBuffer.put(vert).flip();
		
		var indBuffer = BufferUtils.createIntBuffer(ind.length);
		indBuffer.put(ind).flip();
		
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
