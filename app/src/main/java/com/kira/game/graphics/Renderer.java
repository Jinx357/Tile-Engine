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
import static com.kira.game.assets.TextureType.*;
import static com.kira.game.assets.ShaderType.*;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;

import com.kira.game.graphics.ShaderC;
import com.kira.game.graphics.TextureC;
import com.kira.game.graphics.Mesh;

import static com.kira.game.input.Input.*;

import com.kira.game.ecs.EntityRegistry;
import com.kira.game.components.RenderableComponent;
import com.kira.game.components.TransformComponent;

//ADDING
public class Renderer {
	
	private ShaderC shader;
	private ShaderC debugShader;
	
	private TextureC texture;
	
	private boolean DEBUG_MODE;
	
	private EntityRegistry registry;
	
	//private  List<Integer> bundle;
	//TODO: refactor
	
	private TransformComponent t;
 //eptmw-1120
   public Renderer(EntityRegistry registry) {
	   
	   this.shader = new ShaderC(getShader(DEFAULT_VERTEX_SHADER) ,  getShader(DEFAULT_PIXEL_SHADER));
	   this.debugShader = new ShaderC(getShader(DEBUG_VERTEX_SHADER) , getShader(DEBUG_PIXEL_SHADER));
	   
	   this.texture = new TextureC(getTexture(TEST_TEXTURE));
	   
	   this.DEBUG_MODE = false;
	   
	   this.registry = registry;
   }
   
   public void setDebugMode(boolean mode) {
	   
	   DEBUG_MODE = mode;
   }
   
 
   
   public void render() {
	   
	     ShaderC activeShader;
	   
	   if(DEBUG_MODE){
		   activeShader = debugShader;
	   	   glUseProgram(debugShader.getShaderProgram());
	   }
	   else {
		   activeShader = shader;
		   glUseProgram(shader.getShaderProgram());
	   }
	   glActiveTexture(GL_TEXTURE0);
	
	   TransformComponent t;
	   RenderableComponent r;
	   List<Integer> bundle = new ArrayList<>(this.registry.view(RenderableComponent.class , TransformComponent.class));
	   FloatBuffer fb = BufferUtils.createFloatBuffer(16);
	   
	   for(int entity : bundle) {
		   
		   r = this.registry.getComponent(entity , RenderableComponent.class);
		   t = this.registry.getComponent(entity , TransformComponent.class);
		  
		   
			//System.out.println("r "+entity + " _>" + t);
		    //System.out.println("r->" + System.identityHashCode(t));
		   
		   if(t==null)throw new RuntimeException("asa");
		   
			  fb.clear();
			  t.transformMatrix.get(fb);
			  //fb.flip();
			  
			  
			    //System.out.println(fb.hasRemaining());
			  //while(fb.hasRemaining()) {
				  
				 // System.out.println(fb.get());
			//  }
			  //fb.rewind();
		   texture.bind(); 
		   glUniformMatrix4fv(activeShader.getUniformTransformationLocation() , false , fb);
		   glUniform1i(activeShader.getUniformTextureLocation() , 0);
		   
		   glBindVertexArray(r.vao);
		   {
			
		  
		   
		   glDrawElements(GL_TRIANGLES , 6 , GL_UNSIGNED_INT , 0);
		   
		   texture.unbind();
		   }glBindVertexArray(0);
	   }
	   
   }
   
   
   public void clear() {
	   
	   glClear(GL_COLOR_BUFFER_BIT);
   }
	
}