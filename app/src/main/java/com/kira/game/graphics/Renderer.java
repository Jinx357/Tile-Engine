package com.kira.game.graphics;

import java.util.List;
import java.util.ArrayList;

import org.lwjgl.opengl.GL;
import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import static com.kira.game.assets.ShaderAssetsManager.*;
import static com.kira.game.assets.ShaderType.*;

import com.kira.game.graphics.ShaderC;
import com.kira.game.graphics.Mesh;

import static com.kira.game.input.Input.*;

import com.kira.game.ecs.EntityRegistry;
import com.kira.game.components.RenderableComponent;

//ADDING
public class Renderer {
	
	private  String DEFAULT_VERTEX_SHADER_PATH;
	private  String DEFAULT_PIXEL_SHADER_PATH;
	
	private ShaderC shader;
	private ShaderC debugShader;
	
	private boolean DEBUG_MODE;
	//TODO: refactor
	
 //eptmw-1120
   public Renderer() {
	   
	   shader = new ShaderC(getShader(DEFAULT_VERTEX_SHADER) ,  getShader(DEFAULT_PIXEL_SHADER));
	   debugShader = new ShaderC(getShader(DEBUG_VERTEX_SHADER) , getShader(DEBUG_PIXEL_SHADER));
	   
	   DEBUG_MODE = false;
   }
   
   public void setDebugMode(boolean mode) {
	   
	   DEBUG_MODE = mode;
   }
   
   public void draw(EntityRegistry registry) {
	   
	   List<Integer> bundle = new ArrayList<>(registry.view(RenderableComponent.class));
	   
	   for(int entity : bundle) {
		   
		   RenderableComponent r = registry.getComponent(entity , RenderableComponent.class);
		   
	   }
   }
   
   public void render(EntityRegistry registry) {
	   
	   if(DEBUG_MODE) glUseProgram(debugShader.getShaderProgram());
	   else glUseProgram(shader.getShaderProgram());
	   
	   //uniforms here-------
	  //glUniformMatrix4fv(shader.getUniformTransformationLocation() , false , entity.getTransformBuffer());
	  // glUniform1f(shader.getUniformTimeLocation() , (float)glfwGetTime());
	   
	   
	  // glBindVertexArray(entity.getVao());
	   //render here--------- 
	   
	   RenderableComponent r;
	     
	   List<Integer> bundle = new ArrayList<>(registry.view(RenderableComponent.class));
	   
	   for(int entity : bundle) {
		   
		   r = registry.getComponent(entity , RenderableComponent.class);
		   
		   glBindVertexArray(r.vao);
		   
		   glDrawElements(GL_TRIANGLES , 6 , GL_UNSIGNED_INT , 0L);
		   
		   glBindVertexArray(0);
	   }
	  // glDrawElements(GL_TRIANGLES , 6 , GL_UNSIGNED_INT , 0L);
	   
	   //--------------------
	  // glBindVertexArray(0);
	   
   }
   
   
   public void clear() {
	   
	   glClear(GL_COLOR_BUFFER_BIT);
   }
	
}