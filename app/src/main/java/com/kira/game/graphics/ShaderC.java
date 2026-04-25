package com.kira.game.graphics;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;


import org.lwjgl.opengl.GL;
import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import static org.lwjgl.system.MemoryUtil.NULL;

//REFACTORING
public class ShaderC {
	
	private int sProgram;
	
	private final int DEBUG_SHADERS = 0;
	private final int DEFAULT_SHADERS = 1;
	
	  //TODO : refactor
	private int uTintLocation;
	private int uTextureLocation;
    private int uTransformLocation;
	private int uViewLocation;
	private int uProjectionLocation;
	private int uTimeLocation;
	
	public ShaderC(String vertexShaderPath , String pixelShaderPath) {
		
		try {
		compileAndLinkShaders(readShaderSource(vertexShaderPath) , readShaderSource(pixelShaderPath));
		}catch(IOException | URISyntaxException e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
	}
	
	//getters
	public int getShaderProgram() {return sProgram;}
	public int getUniformTransformationLocation() {return uTransformLocation;}
	public int getUniformTextureLocation() {return uTextureLocation;}
	public int getUniformViewLocation() {return uViewLocation;}
	public int getUniformTintLocation() {return uTintLocation;}
	public int getUniformTimeLocation() {return uTimeLocation;}
	public int getUniformProjectionLocation() {return uProjectionLocation;}
	
	// god knows how this works it just does , DO NOT TOUCH
	private String readShaderSource(String path) throws IOException , URISyntaxException {
	
		var url = ShaderC.class.getResource(path);
		if(url == null) throw new IOException("cant find shader at : " + path);
		
		return Files.readString(Paths.get(url.toURI()));
	}
	
	private void compileAndLinkShaders(String vertexShaderData , String pixelShaderData) {
		
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
		glValidateProgram(sProg);
		//uniforms
	   //TODO: refactor
	    uTextureLocation = glGetUniformLocation(sProg , "tex");
		uTintLocation = glGetUniformLocation(sProg , "tint");
	    uTransformLocation = glGetUniformLocation(sProg , "t");
		uViewLocation = glGetUniformLocation(sProg , "v");
		uProjectionLocation = glGetUniformLocation(sProg , "p");
		uTimeLocation = glGetUniformLocation(sProg , "uTime");
		//if(uTransformLocation == -1) throw new RuntimeException("err uni");
		
		// we dont need these anymore , compilation is done
		glDeleteShader(vShader);
		glDeleteShader(pShader);
		
		
		//errors
		
		if(glGetShaderi(vShader , GL_COMPILE_STATUS) == GL_FALSE) System.err.println("vertex: "+glGetShaderInfoLog(vShader));
		if(glGetShaderi(pShader , GL_COMPILE_STATUS) == GL_FALSE) System.err.println("Pixel: "+glGetShaderInfoLog(pShader));
		String log = glGetProgramInfoLog(sProg);
		if(glGetProgrami(sProg  , GL_LINK_STATUS) == GL_FALSE)    System.err.println("Shader Program :" + log);	
	}
	
}