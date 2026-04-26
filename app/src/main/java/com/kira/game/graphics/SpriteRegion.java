package com.kira.game.graphics;

//NOTE : THIS CLASS IS PROBLY TEMPORARY

public class SpriteRegion {
	
	public float u0 , v0;
	public float u1 , v1;
	
	public int width;
	public int height;
	
	public SpriteRegion(int atlasWidth , int atlasHeight , int x , int y , int w , int h) {
		
		this.u0 = (float) x / atlasWidth;
		this.v0 = (float) y / atlasHeight;
		this.u1 = (float) (x + w) / atlasHeight;
		this.v1 = (float) (y + h) / atlasWidth;
		
		
		this.width = w;
		this.height = h;
	}
}