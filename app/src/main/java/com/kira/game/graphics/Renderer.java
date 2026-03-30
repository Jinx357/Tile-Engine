package com.kira.game.graphics;

import org.lwjgl.opengl.GL;
import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import static com.kira.game.assets.ShaderAssetsManager.*;

import com.kira.game.graphics.ShaderC;
import com.kira.game.graphics.Mesh;

import static com.kira.game.physics.Movement.*;
import static com.kira.game.input.Input.*;

//ADDING
public class Renderer {
	
	private  String DEFAULT_VERTEX_SHADER_PATH;
	private  String DEFAULT_PIXEL_SHADER_PATH;
	
	private ShaderC shader;
	private ShaderC debugShader;
	private Mesh mesh;
	
	private boolean DEBUG_MODE;
	//TODO: refactor
	//-->private ShaderC shaderc;
	//-->private int sProg;
	//-->private int vao;
 
   public Renderer() {
	   
	   shader = new ShaderC(getShader(DEFAULT_VERTEX_SHADER) ,  getShader(DEFAULT_PIXEL_SHADER));
	   debugShader = new ShaderC(getShader(DEBUG_VERTEX_SHADER) , getShader(DEBUG_PIXEL_SHADER));
	   
	   mesh = new Mesh();
	   
	   DEBUG_MODE = false;
   }
   
   public void setDebugMode(boolean mode) {
	   
	   DEBUG_MODE = mode;
   }
   
   public void render() {
	   
	   if(DEBUG_MODE) glUseProgram(debugShader.getShaderProgram());
	   else glUseProgram(shader.getShaderProgram());
	   
	   //uniforms here-------
	   glUniformMatrix4fv(shader.getUniformTransformationLocation() , false , getTransformationBuffer());
	   glUniform1f(shader.getUniformTimeLocation() , (float)glfwGetTime());
	   
	   
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