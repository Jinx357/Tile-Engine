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
	
	private EntityRegistry registry;
	
	//private  List<Integer> bundle;
	//TODO: refactor
	
	private TransformComponent t;
 //eptmw-1120
   public Renderer(EntityRegistry registry) {
	   
	   this.shader = new ShaderC(getShader(DEFAULT_VERTEX_SHADER) ,  getShader(DEFAULT_PIXEL_SHADER));
	   this.debugShader = new ShaderC(getShader(DEBUG_VERTEX_SHADER) , getShader(DEBUG_PIXEL_SHADER));
	   
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
	  
	 /*  bundle = new ArrayList<>(registry.view(TransformComponent.class , RenderableComponent.class));
	   
	   for(int entity : bundle){
		   
		   t = registry.getComponent(entity , TransformComponent.class);
		   //System.out.println(this.t.transformMatrix);
	   glUniformMatrix4fv(shader.getUniformTransformationLocation() , false , this.t.transformBuffer );}
	 
	   */float[] test = new float[16];
	   
	
	   TransformComponent t;
	   RenderableComponent r;
	   List<Integer> bundle = new ArrayList<>(this.registry.view(RenderableComponent.class , TransformComponent.class));
	   
	   for(int entity : bundle) {
		   
		   r = this.registry.getComponent(entity , RenderableComponent.class);
		   t = this.registry.getComponent(entity , TransformComponent.class);
		  
		   
			System.out.println("r "+entity + " _>" + t);
		    System.out.println("r->" + System.identityHashCode(t));
		   
		   if(t==null)throw new RuntimeException("asa");
		      //System.out.println(t.transformMatrix.getClass());
		        // System.out.println(fb.getClass());
			 //System.out.println(fb.isReadOnly());
		   //fb.clear();
		   
		      FloatBuffer fb = BufferUtils.createFloatBuffer(16);
			  
			  Matrix4f m = new Matrix4f().identity().translate(1 , 2 , 3);
			  
			  fb.clear();
			  m.get(fb);
			  
			//  System.out.println(m.getClass().getName());
			 
			  //this proves the matrix is not the matrix we think it is
			//  System.out.println("pos = " + fb.position());
		   
		   
		   //this debug proves buffer usage is the issue
		  // t.transformMatrix.get(test);
		   //t.transformMatrix.get(fb);
		   //for(float f : test){
		     // System.out.println(f + " ");
		   //}		

		   //this debug proves that the matrix is infact not writing to the buffer
		  // System.out.println("pos after .get() " + fb.position());
		  //    System.out.println("lim after .get() " + fb.limit());
		   //fb.flip(); 
		   
		   
		   //this debug tells that there is a buffer state issue so like yes opengl hates us
		   //System.out.println(fb.remaining());
		 // while(fb.hasRemaining()) {
			//  System.out.println(fb.get() + " fb");
		  //}
		   
		   
		   
		   glUniformMatrix4fv(activeShader.getUniformTransformationLocation() , false , fb);
		   
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