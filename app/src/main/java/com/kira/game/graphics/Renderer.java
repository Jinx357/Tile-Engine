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

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;

import com.kira.game.graphics.ShaderC;
import com.kira.game.graphics.Mesh;

import static com.kira.game.input.Input.*;

import com.kira.game.ecs.EntityRegistry;
import com.kira.game.components.RenderableComponent;
import com.kira.game.components.TransformComponent;

//ADDING
public class Renderer {
	
	private  String DEFAULT_VERTEX_SHADER_PATH;
	private  String DEFAULT_PIXEL_SHADER_PATH;
	
	private ShaderC shader;
	private ShaderC debugShader;
	
	private boolean DEBUG_MODE;
	
	//private  List<Integer> bundle;
	//TODO: refactor
	
	private TransformComponent t;
 //eptmw-1120
   public Renderer() {
	   
	   shader = new ShaderC(getShader(DEFAULT_VERTEX_SHADER) ,  getShader(DEFAULT_PIXEL_SHADER));
	   debugShader = new ShaderC(getShader(DEBUG_VERTEX_SHADER) , getShader(DEBUG_PIXEL_SHADER));
	   
	   DEBUG_MODE = false;
   }
   
   public void setDebugMode(boolean mode) {
	   
	   DEBUG_MODE = mode;
   }
   
 
   
   public void render(EntityRegistry registry) {
	   
	   if(DEBUG_MODE) glUseProgram(debugShader.getShaderProgram());
	   else glUseProgram(shader.getShaderProgram());
	   
	  
	 /*  bundle = new ArrayList<>(registry.view(TransformComponent.class , RenderableComponent.class));
	   
	   for(int entity : bundle){
		   
		   t = registry.getComponent(entity , TransformComponent.class);
		   //System.out.println(this.t.transformMatrix);
	   glUniformMatrix4fv(shader.getUniformTransformationLocation() , false , this.t.transformBuffer );}
	 
	   */float[] test = {1f,0f,0f,0f , 0f,1f,0f,0f , 0f,0f,1f,0f , 0f,0f,0f,1f};
	   
	   FloatBuffer fb = BufferUtils.createFloatBuffer(16);
	   TransformComponent t;
	   RenderableComponent r;
	   List<Integer> bundle = new ArrayList<>(registry.view(RenderableComponent.class));
	   
	   for(int entity : bundle) {
		   
		   r = registry.getComponent(entity , RenderableComponent.class);
		   t = registry.getComponent(entity , TransformComponent.class);
		   
		   t.transformMatrix.get(fb);
		   fb.flip();
		   
		   while(fb.hasRemaining()) {
			   
			   float f = fb.get();
			   
			   System.out.println(f);
		   }
		   
		   glUniformMatrix4fv(shader.getUniformTransformationLocation() , false , fb);
		   
		   glBindVertexArray(r.vao);
		   {
		   
		   glDrawElements(GL_TRIANGLES , 6 , GL_UNSIGNED_INT , 0);
		   
		   }glBindVertexArray(0);
	   }
	   
   }
   
   
   public void clear() {
	   
	   glClear(GL_COLOR_BUFFER_BIT);
   }
	
}