package com.kira.game.graphics;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;


import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import static org.lwjgl.system.MemoryUtil.NULL;

//REFACTORING
class ShaderC {
	
	private int sProgram;
	
	
	  //TODO : refactor
	 //-->public int uTimeLocation;
    //-->public int uTransformLocation;
	
	public ShaderC(String vertexShaderPath , String pixelShaderPath) {
		
		setShaders(vertexShaderPath , pixelShaderData);
	}
	
	public int getShaderProgram() {
		
		return sProgram;
	}
	
	public void setShaders(String vertexFile , String pixelFile) {
		
		
		//hold the contents of the files
		String vertexShaderData;
		String pixelShaderData;
		
		try{
			//read and put source into string
			vertexShaderData = readShaderSource(vertexFile);
			pixelShaderData = readShaderSource(pixelFile);
			
			//compile string data
			compileShaders(vertexShaderData , pixelShaderData);
		} 
		catch(IOException | URISyntaxException e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
	}
	
	// god knows how this works it just does , DO NOT TOUCH
	private String readShaderSource(String path) throws IOException , URISyntaxException {
	
		var url = ShaderC.class.getResource(path);
		if(url == null) throw new IOException("cant find shader at : " + path);
		
		return Files.readString(Paths.get(url.toURI()));
	}
	
	private void compileShaders(String vertexShaderData , String pixelShaderData) {
		
		// generate handles for types
		int vShader = glCreateShader(GL_VERTEX_SHADER);
		int pShader = glCreateShader(GL_FRAGMENT_SHADER);
		
		//bind handle to shader
		glShaderSource(vShader , vertexShaderData);
		glCompileShader(vShader);
		glShaderSource(pShader , pixelShaderData);
		glCompileShader(pShader);
		
		//generate handle for shader program
		int sProg = glCreateProgram();
		sProgram = sProg;
		
		//attach shaders to program
		glAttachShader(sProg , vShader);
		glAttachShader(sProg , pShader);
		
		//link the program
		glLinkProgram(sProg);
		
		//uniforms
	   //TODO: refactor
	  //-->uTimeLocation = glGetUniformLocation(sProg , "uTime");
	 //-->uTransformLocation = glGetUniformLocation(sProg , "uTransform");
	
		
		// we dont need these anymore , compilation is done
		glDeleteShader(vShader);
		glDeleteShader(pShader);
		
		
		//errors
		
		if(glGetShaderi(vShader , GL_COMPILE_STATUS) == GL_FALSE) System.err.println("vertex: "+glGetShaderInfoLog(vShader));
		if(glGetShaderi(pShader , GL_COMPILE_STATUS) == GL_FALSE) System.err.println("Pixel: "+glGetShaderInfoLog(pShader));
		if(glGetProgrami(sProg  , GL_LINK_STATUS) == GL_FALSE)    System.err.println("Shader Program :" +glGetShaderInfoLog(sProg));	
	}
	
}