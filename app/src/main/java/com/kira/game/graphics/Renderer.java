package com.kira.game.graphics;

import org.lwjgl.opengl.GL;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import com.kira.game.assets.ShaderAssetsManager;

import com.kira.game.graphics.ShaderC;
import com.kira.game.graphics.Mesh;

import static com.kira.game.physics.Movement.*;;

//ADDING
public class Renderer {
	
	private  String DEFAULT_VERTEX_SHADER_PATH;
	private  String DEFAULT_PIXEL_SHADER_PATH;
	
	private ShaderC shader;
	private Mesh mesh;
	
	
	//TODO: refactor
	//-->private ShaderC shaderc;
	//-->private int sProg;
	//-->private int vao;
 
   public Renderer() {
	   
	   /* 
			0--> vertex
			1--> pixel
	   */
	   DEFAULT_VERTEX_SHADER_PATH = ShaderAssetsManager.getDefaultShader(0);
	   DEFAULT_PIXEL_SHADER_PATH  = ShaderAssetsManager.getDefaultShader(1);
	   
	   shader = new ShaderC(DEFAULT_VERTEX_SHADER_PATH , DEFAULT_PIXEL_SHADER_PATH);
	   
	   
	   mesh = new Mesh();
   }
   
   public void render() {
	   
	   glUseProgram(shader.getShaderProgram());
	   
	   //uniforms here-------
	   glUniformMatrix4fv(shader.getUniformTransformationLocation() , false , getTransformationBuffer());
	   
	   glBindVertexArray(mesh.getVertexArrayObject());
	   //render here--------- 
	   
	   glDrawElements(GL_TRIANGLES , 6 , GL_UNSIGNED_INT , 0L);
	   
	   //--------------------
	   glBindVertexArray(0);
   }
   
   
   public void clear() {
	   
	   glClear(GL_COLOR_BUFFER_BIT);
   }
	
}