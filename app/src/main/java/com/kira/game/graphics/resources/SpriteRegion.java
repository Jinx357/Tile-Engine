package com.kira.game.graphics.resources;

//NOTE : THIS CLASS IS PROBLY TEMPORARY

public class SpriteRegion {
	
	public float u0 , v0;
	public float u1 , v1;
	
	public int width;
	public int height;
	
	
	public SpriteRegion(int atlasWidth , int atlasHeight , int x , int y , int w , int h) {
		
		float invW = 1f / atlasWidth;
		float invH = 1f / atlasHeight;
		float inset =  0.6f;
		
		this.u0 = (x + inset) * invW;
		this.v0 = (y + h) * invH;
		this.u1 = (x + w - inset) * invW;
		this.v1 = (y) * invH;
		
		
		this.width = w;
		this.height = h;
	}
}