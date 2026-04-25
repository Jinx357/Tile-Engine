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
	private Matrix4f projMat;
	private List<Integer> bundle;
	
	private float uTime;
	
	private List<Batch> batches;
	private List<RenderCommand> commands;
	
	//eptmw-1120 : fixed
	//esdri-0023 : fixed
   public Renderer(EntityRegistry registry) {
	   
	   this.registry = registry;
   }
   
   public void setDebugMode(boolean mode) {
	   
	   DEBUG_MODE = mode;
   }
   
   public void loadViewMatrix(Matrix4f viewMat) {
	   
	   this.viewMat = viewMat;
   }
   
   public void loadProjectionMatrix(Matrix4f projMat) {
	   
	   this.projMat = projMat;
   }
   
	   FloatBuffer fb = BufferUtils.createFloatBuffer(16);
	   FloatBuffer fb2 = BufferUtils.createFloatBuffer(16);
	   FloatBuffer fb3 = BufferUtils.createFloatBuffer(16);
   
   public void render(RenderQueue queue) {
	   
	   bundle = new ArrayList<>(this.registry.view(RenderableComponent.class , TransformComponent.class));
	   
	 
	   
	  
	   //added state batching
	   
	   //TODO: ADD PROPER BATCHING
	   queue.sortRenderCommandChain();
	   commands = queue.getRenderCommands();
	   
	   batches = new ArrayList<>();
	   Batch currentRenderBatch = null;
	   
	   
	   for(RenderCommand cmd : commands) {
		   
		   if(currentRenderBatch == null || cmd.r.material != currentRenderBatch.material)
		   {
			   currentRenderBatch = new Batch(cmd.r.material);
			   batches.add(currentRenderBatch);
		   }
		   currentRenderBatch.add(cmd);
	   }
	   
	   
	   
	   for(Batch batch : batches) {
		   
		   batch.material.apply();
		   
		   for(RenderCommand cmd : batch.commands) {
			  
			//MODEL
		   fb.clear();
		   cmd.t.transformMatrix.get(fb);
		   glUniformMatrix4fv(cmd.r.material.shader.getUniformTransformationLocation() , false , fb);
		   
		   //VIEW
		   fb2.clear();
		   viewMat.get(fb2);
		   glUniformMatrix4fv(cmd.r.material.shader.getUniformViewLocation() , false , fb2);
		   
		   //PROJECTION
		   fb3.clear();
		   projMat.get(fb3);
		   glUniformMatrix4fv(cmd.r.material.shader.getUniformProjectionLocation() , false , fb3);
		   
		   //TIME
		   uTime = (float)glfwGetTime();
		   glUniform1f(cmd.r.material.shader.getUniformTimeLocation() , uTime);
		   
			glBindVertexArray(cmd.r.vao);
			{
				glDrawElements(GL_TRIANGLES , 6 , GL_UNSIGNED_INT , 0);
			}
			glBindVertexArray(0);
			
		   }
	   }
	  
   }
   
   
   public void clear() {
	   
	   glClear(GL_COLOR_BUFFER_BIT);
   }
	
}