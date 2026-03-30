package com.kira.game.core;

//import org.lwjgl.glfw.GLFWErrorCallback;
//import org.lwjgl.opengl.GL;

//import static org.lwjgl.glfw.GLFW.*;
//import static org.lwjgl.opengl.GL30.*;
//import static org.lwjgl.system.MemoryUtil.NULL;

import com.kira.game.Game;

//REFACTORING
public class App {
	
	void main() {
		
		Game game = new Game();
		game.run();
	}
	

	
/*	
	private void gameLoop() {
		
		while(!glfwWindowShouldClose(pWindow)) {
			glfwPollEvents();
			kblPollInputs();
			
			glClear(GL_COLOR_BUFFER_BIT);
			
			debug.newFrame();
			debug.update();
			
			glUseProgram(sProg);
			
			  currentTime = window.getCurrentTime();
		      deltaTime = currentTime - previousTime;
			  previousTime = currentTime;
			  
			 
			 
			
			glUniform1f(uTimeLocation , (float)glfwGetTime());
			glUniformMatrix4fv(uTransformLocation , false , Movement.trBuffer);
			
			glBindVertexArray(vao);
			
			glDrawElements(GL_TRIANGLES , 6 , GL_UNSIGNED_INT , 0L);
			
			glBindVertexArray(0);
			
			debug.render();
			
			glfwSwapBuffers(pWindow);
		}
	}
	
	private void cleanup() {
		
		debug.dispose();
		window.dispose();
	}
	
	*/
}
