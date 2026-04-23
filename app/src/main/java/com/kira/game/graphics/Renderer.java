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
	//TODO: refactor
	
	private TransformComponent t;
	private Matrix4f viewMat;
	private List<Integer> bundle;
	
	//eptmw-1120 : fixed
	//esdri-0023 : ongoing
   public Renderer(EntityRegistry registry) {
	   
	   this.texture = new TextureC(TextureAssetsManager.getTexture(TextureType.TEST_TEXTURE));
	  
	   this.registry = registry;
   }
   
   public void setDebugMode(boolean mode) {
	   
	   DEBUG_MODE = mode;
   }
   
   public void loadViewMatrix(Matrix4f viewMat) {
	   
	   this.viewMat = viewMat;
   }
   
 
   
   public void render(RenderQueue queue) {
	   
	   ShaderC activeShader;
	  
	   
	   bundle = new ArrayList<>(this.registry.view(RenderableComponent.class , TransformComponent.class));
	   
	   FloatBuffer fb = BufferUtils.createFloatBuffer(16);
	   FloatBuffer fb2 = BufferUtils.createFloatBuffer(16);
	   
	   for(RenderCommand cmd : queue.getRenderCommands()) {
		   
		   // shaders
		   glUseProgram(cmd.r.shader);
		   
		   
		   
		   fb.clear();
		   cmd.t.transformMatrix.get(fb);
		   
		   fb2.clear();
		   viewMat.get(fb2);
		   
		   glUniformMatrix4fv(cmd.r.shaderC.getUniformTransformationLocation() , false , fb);
		   glUniformMatrix4fv(cmd.r.shaderC.getUniformViewLocation() , false , fb2);
		   glUniform1f(cmd.r.shaderC.getUniformTintLocation() , cmd.r.colorE); 
		   glUniform1i(cmd.r.shaderC.getUniformTextureLocation() , 0);
		   
		   glActiveTexture(GL_TEXTURE0);
		   texture.bind();{
		   glBindVertexArray(cmd.r.vao);{
			
		  
		   
		   glDrawElements(GL_TRIANGLES , 6 , GL_UNSIGNED_INT , 0);
		   
		  
		     }glBindVertexArray(0);
	        }texture.unbind();
	   }
	   
   }
   
   
   public void clear() {
	   
	   glClear(GL_COLOR_BUFFER_BIT);
   }
	
}