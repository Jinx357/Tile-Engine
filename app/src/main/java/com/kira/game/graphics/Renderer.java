package com.kira.game.graphics;

import java.util.List;
import java.util.ArrayList;

import org.lwjgl.opengl.GL;
import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import static com.kira.game.assets.ShaderAssetsManager.*;
import static com.kira.game.assets.TextureAssetsManager.*;
import com.kira.game.assets.TextureType.*;
import com.kira.game.assets.ShaderType.*;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;

import com.kira.game.graphics.ShaderC;
import com.kira.game.graphics.TextureC;
import com.kira.game.graphics.Mesh;

import com.kira.game.graphics.RenderCommand;
import com.kira.game.graphics.RenderQueue;

import static com.kira.game.input.Input.*;

import com.kira.game.ecs.EntityRegistry;
import com.kira.game.components.RenderableComponent;
import com.kira.game.components.TransformComponent;
import com.kira.game.components.CameraComponent;

import com.kira.game.assets.TextureAssetsManager;
import com.kira.game.assets.TextureType;

//ADDING
public class Renderer {
	
	private TextureC texture;
	
	private boolean DEBUG_MODE;
	
	private EntityRegistry registry;
	
	private TransformComponent t;
	private Matrix4f viewMat;
	private List<Integer> bundle;
	
	private float uTime;
	
	private List<Batch> batches;
	
	//eptmw-1120 : fixed
	//esdri-0023 : ongoing
   public Renderer(EntityRegistry registry) {
	   
	   this.registry = registry;
   }
   
   public void setDebugMode(boolean mode) {
	   
	   DEBUG_MODE = mode;
   }
   
   public void loadViewMatrix(Matrix4f viewMat) {
	   
	   this.viewMat = viewMat;
   }
   
 
   
   public void render(RenderQueue queue) {
	   
	   bundle = new ArrayList<>(this.registry.view(RenderableComponent.class , TransformComponent.class));
	   
	   FloatBuffer fb = BufferUtils.createFloatBuffer(16);
	   FloatBuffer fb2 = BufferUtils.createFloatBuffer(16);
	   
	   
	   //todo
	   
	   //add batching
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   for(RenderCommand cmd : queue.getRenderCommands()) {
		   
		   
		   fb.clear();
		   cmd.t.transformMatrix.get(fb);
		   
		   fb2.clear();
		   viewMat.get(fb2);
		   
		   uTime = (float)glfwGetTime();
		   
		   glUniformMatrix4fv(cmd.r.material.shader.getUniformTransformationLocation() , false , fb);
		   glUniformMatrix4fv(cmd.r.material.shader.getUniformViewLocation() , false , fb2);
		   glUniform1f(cmd.r.material.shader.getUniformTimeLocation() , uTime);
		   
		   
		   cmd.r.material.apply();
		   glBindVertexArray(cmd.r.vao);{
			
		  
		   
		   glDrawElements(GL_TRIANGLES , 6 , GL_UNSIGNED_INT , 0);
		   
		  
		     }glBindVertexArray(0);
	     
	   }
	   
   }
   
   
   public void clear() {
	   
	   glClear(GL_COLOR_BUFFER_BIT);
   }
	
}