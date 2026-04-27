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
		
		this.u0 = (x - 1f) * invW;
		this.v0 = (y - 1f) * invH;
		this.u1 = (x + w - 1f) * invW;
		this.v1 = (y + h - 1f) * invH;
		
		
		this.width = w;
		this.height = h;
	}
}