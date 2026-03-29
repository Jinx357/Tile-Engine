package com.kira.game.graphics;

//ADDING
class Renderer {
	
	private final String DEFAULT_VERTEX_SHADER_PATH;
	private final String DEFAULT_PIXEL_SHADER_PATH;
	
	private ShaderC shader;
	private Mesh mesh;
	
	//TODO: refactor
	//-->private ShaderC shaderc;
	//-->private int sProg;
	//-->private int vao;
 
   public Renderer() {
	   
	   /* 
			0--> vertex
			1--> pixel
	   */
	   DEFAULT_VERTEX_SHADER_PATH = ShaderAssetsManager.getDefaultShader(0);
	   DEFAULT_PIXEL_SHADER_PATH  = ShaderAssetsManager.getDefaultShader(1);
	   
	   shader = new ShaderC(DEFAULT_VERTEX_SHADER_PATH , DEFAULT_PIXEL_SHADER_PATH);
	   mesh = new Mesh();
   }
   
   public void render() {
	   
	   glUseProgram(shader.getShaderProgram());
	   
	   glBindVertexArray(mesh.getVertexArrayObject());
	   //render here--------- 
	   
	   
	   
	   
	   //--------------------
	   glBindVertexArray(0)
   }
   
   public void clear() {
	   
	   glClear(GL_COLOR_BUFFER_BIT);
   }
	
}