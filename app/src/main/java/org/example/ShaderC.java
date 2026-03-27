package org.example;

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


class ShaderC {
	
	public int sProgram;
	
	public String readShaderSrc(String path) throws IOException , URISyntaxException {
	
		var url = ShaderC.class.getResource(path);
		
		if(url == null) throw new IOException("cant find shader at : " + path);
		
		return Files.readString(Paths.get(url.toURI()));
	}
	
	private void compileShaders(String vShadDat , String pShadDat) {
		
		int vShader = glCreateShader(GL_VERTEX_SHADER);
		int pShader = glCreateShader(GL_FRAGMENT_SHADER);
		
		glShaderSource(vShader , vShadDat);
		glCompileShader(vShader);
		glShaderSource(pShader , pShadDat);
		glCompileShader(pShader);
		
		int sProg = glCreateProgram();
		sProgram = sProg;
		
		//uniforms

		
		glAttachShader(sProg , vShader);
		glAttachShader(sProg , pShader);
		
		glLinkProgram(sProg);
		
		glDeleteShader(vShader);
		glDeleteShader(pShader);
		
		
		//errors
		
		if(glGetShaderi(vShader , GL_COMPILE_STATUS) == GL_FALSE) System.err.println(glGetShaderInfoLog(vShader));
		if(glGetShaderi(pShader , GL_COMPILE_STATUS) == GL_FALSE) System.err.println(glGetShaderInfoLog(pShader));
		if(glGetProgrami(sProg , GL_LINK_STATUS) == GL_FALSE) System.err.println(glGetShaderInfoLog(sProg));
		
		
	}
	
	public void setShaders(String vertexFile , String pixelFile) {
		
		String vShadDat;
		String pShadDat;
		
		try{
			vShadDat = readShaderSrc(vertexFile);
			pShadDat = readShaderSrc(pixelFile);
			compileShaders(vShadDat , pShadDat);
		} 
		catch(IOException | URISyntaxException e) {
			System.err.println(e);
			e.printStackTrace();
		}
		
	}
}