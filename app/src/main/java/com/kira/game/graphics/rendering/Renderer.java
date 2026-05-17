package com.kira.game.graphics.rendering;

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

import com.kira.game.graphics.resources.ShaderC;
import com.kira.game.graphics.resources.TextureC;
import com.kira.game.graphics.resources.Mesh;
import com.kira.game.graphics.resources.SpriteRegion;

import com.kira.game.graphics.rendering.RenderCommand;
import com.kira.game.graphics.rendering.RenderQueue;


import static com.kira.game.input.Input.*;

import com.kira.game.ecs.EntityRegistry;
import com.kira.game.components.RenderableComponent;
import com.kira.game.components.TransformComponent;
import com.kira.game.components.CameraComponent;

import com.kira.game.assets.TextureAssetsManager;
import com.kira.game.assets.TextureType;
import com.kira.game.assets.TileSheet;

import com.kira.game.world.map.TileMap;

//ADDING
public class Renderer {
	
	private TextureC texture;
	
	private boolean DEBUG_MODE;
	
	private EntityRegistry registry;
	//private TileMap map;
	
	private TransformComponent t;
	private Matrix4f viewMat;
	private Matrix4f projMat;
	private List<Integer> bundle;
	
	private float uTime;
	
	private List<Batch> batches;
	private List<RenderCommand> commands;
	
	private FloatBuffer fb; //ecs mvp
	private FloatBuffer fb2; // map mvp
	private FloatBuffer fb3; // ui
	private FloatBuffer fb4; //free
	
	private int mapVao;
	private int mapW;
	private int mapH;
	
	
	
	//eptmw-1120 : fixed
	//esdri-0023 : fixed
   public Renderer(EntityRegistry registry) {
	   
	   this.registry = registry;
	   
	   this.fb  = BufferUtils.createFloatBuffer(16);
	   this.fb2 = BufferUtils.createFloatBuffer(16);
	   this.fb3 = BufferUtils.createFloatBuffer(16);
   }
   
    public Renderer(EntityRegistry registry , TileMap map) {
	   
	   this.registry = registry;
	   //this.map = map;
	   
	   this.fb  = BufferUtils.createFloatBuffer(16);
	   this.fb2 = BufferUtils.createFloatBuffer(16);
	   this.fb3 = BufferUtils.createFloatBuffer(16);
	   this.fb4 = BufferUtils.createFloatBuffer(16);
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
   
   
   //ecs renderer
   public void render(RenderQueue queue) {
	   
	   bundle = new ArrayList<>(this.registry.view(RenderableComponent.class , TransformComponent.class));
	   
	 
	   
	  
	   //added state batching
	   
	   //TODO: ADD PROPER BATCHING
	   queue.sortRenderCommandChain(2);
	   commands = queue.getRenderCommands(2);
	   
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
		   fb.clear();
		   viewMat.get(fb);
		   glUniformMatrix4fv(cmd.r.material.shader.getUniformViewLocation() , false , fb);
		   
		   //PROJECTION
		   fb.clear();
		   projMat.get(fb);
		   glUniformMatrix4fv(cmd.r.material.shader.getUniformProjectionLocation() , false , fb);
		   
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
   
   //map / world renderer
   public void render(ShaderC shader , TextureC texture , int mapVertexArrayObject , int height , int width , int tileCount) {
	   
			//shader
			glUseProgram(shader.getShaderProgram());
			
			//texture
			glActiveTexture(GL_TEXTURE0);
			texture.bind();
			
			//transform
		   fb2.clear();
		   new Matrix4f().get(fb2);
		   glUniformMatrix4fv(shader.getUniformTransformationLocation() , false , fb2);
			
			//view
		   fb2.clear();
		   viewMat.get(fb2);
		   glUniformMatrix4fv(shader.getUniformViewLocation() , false , fb2);
		   
		   //projection
		   fb2.clear();
		   projMat.get(fb2);
		   glUniformMatrix4fv(shader.getUniformProjectionLocation() , false , fb2);
		   
	   glBindVertexArray(mapVertexArrayObject);
	   
	   glDrawElements(GL_TRIANGLES , tileCount * 6 , GL_UNSIGNED_INT , 0);
	   
	   glBindVertexArray(0);
   }
   //ui renderer TODO:IMPLEMENT LATER	
   public void renderUserInterface(){}
   
   public void clear() {
	   
	   glClearColor(1f , 1f , 1f , 1f);
	   glClear(GL_COLOR_BUFFER_BIT);
   }
	
}