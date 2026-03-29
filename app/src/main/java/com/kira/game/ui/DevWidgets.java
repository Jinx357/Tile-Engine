package com.kira;


import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;
import static imgui.ImGui.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.ImFontConfig;
import imgui.flag.ImGuiConfigFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;

public class DevWidgets {
	
	private final ImGuiImplGlfw imGuiGlfw = new ImGuiImplGlfw();
	private final ImGuiImplGl3 imGuiGl3 = new ImGuiImplGl3();
	
	public void init(long pWindow) {
		
		
		glfwMakeContextCurrent(pWindow);
		
		ImGui.createContext();
		ImGuiIO io = ImGui.getIO();
		io.setIniFilename(null);
		
		io.addConfigFlags(ImGuiConfigFlags.DockingEnable);
		
		//var fontConfig = new ImFontConfig();
		//fontConfig.setRasterizerMultiply(1.0f);
		//fontConfig.setPixelSnapH(true);
		
		io.getFonts().addFontDefault();
		
		
		
		imGuiGlfw.init(pWindow , true);
		imGuiGl3.init("#version 330");
		
		io.getFonts().build();
		
		imGuiGl3.createFontsTexture();
	}
	
	public void newFrame() {
	
	 imGuiGlfw.newFrame();
	 ImGui.newFrame();
	}
	
	public void render() {
		
		ImGui.render();
		imGuiGl3.renderDrawData(ImGui.getDrawData());
	}
	
	public void dispose() {
		
		imGuiGl3.shutdown();
		imGuiGlfw.shutdown();
		ImGui.destroyContext();
	}
	
	public void update() {
		
		ImGui.begin("FUCK YEAH!");
		ImGui.text("FREEEEEEDUM");
		
		if(ImGui.button("OIL")) System.out.println("USA IS COMING");
		
		ImGui.end();
	}
}