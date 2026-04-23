package com.kira.game.graphics;

import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;

import java.io.InputStream;
import java.io.IOException;

import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

public class TextureC {
	
	private final int id;
	private final int width;
	private final int height;
	
	public TextureC(String filePath){
	
	
		ByteBuffer buffer = getImage(filePath);

	
		try(MemoryStack stack = MemoryStack.stackPush()) {
			
			IntBuffer widthBuffer = stack.mallocInt(1);
			IntBuffer heightBuffer = stack.mallocInt(1);
			IntBuffer comp = stack.mallocInt(1);
			
			STBImage.stbi_set_flip_vertically_on_load(true);
			 
			ByteBuffer image = STBImage.stbi_load_from_memory(buffer , widthBuffer , heightBuffer , comp , STBImage.STBI_rgb_alpha);
			
			if(image == null) throw new RuntimeException("TEXTUREC : load failed -> " + STBImage.stbi_failure_reason());
			
			this.width = widthBuffer.get(0);
			this.height = heightBuffer.get(0);
			this.id = glGenTextures();
			
			glBindTexture(GL_TEXTURE_2D , id);
			
			glTexParameteri(GL_TEXTURE_2D , GL_TEXTURE_MIN_FILTER , GL_LINEAR);
			glTexParameteri(GL_TEXTURE_2D , GL_TEXTURE_MAG_FILTER , GL_LINEAR);
			glTexParameteri(GL_TEXTURE_2D , GL_TEXTURE_WRAP_S , GL_REPEAT);
			glTexParameteri(GL_TEXTURE_2D , GL_TEXTURE_WRAP_T , GL_REPEAT);

			
			
			glTexImage2D(GL_TEXTURE_2D , 0 , GL_RGBA , width , height , 0 , GL_RGBA , GL_UNSIGNED_BYTE , image);
			
			STBImage.stbi_image_free(image);
			
			glBindTexture(GL_TEXTURE_2D , 0);
		}
	}
	
	private ByteBuffer getImage(String filePath) {
		
		ByteBuffer buf = null;
		
		try{
		InputStream str = getClass().getResourceAsStream(filePath);
		if(str == null) throw new RuntimeException("texture not found!");
		
		byte[] imageBytes = str.readAllBytes();
		
		buf = BufferUtils.createByteBuffer(imageBytes.length);
		
		buf.put(imageBytes);
		buf.flip();
		
		
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		return buf;
	}
	
	public void bind() { glBindTexture(GL_TEXTURE_2D , id);}
	public void unbind() { glBindTexture(GL_TEXTURE_2D , 0);}
	public void delete() { glDeleteTextures(id);}
	
	public int getWidth() { return width;}
	public int getHeight() { return height;}
}