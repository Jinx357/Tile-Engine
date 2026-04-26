package com.kira.game.graphics.resources;

//NOTE : THIS CLASS IS PROBLY TEMPORARY

public class SpriteRegion {
	
	public float u0 , v0;
	public float u1 , v1;
	
	public int width;
	public int height;
	private final float margin = 1f;
	private final float rMargin = 2f;
	
	public SpriteRegion(int atlasWidth , int atlasHeight , int x , int y , int w , int h) {
		
		this.u0 = (float) (x - margin) / atlasWidth;//l
		this.v1 = (float) (y - margin) / atlasHeight;//b
		this.u1 = (float) (x + w - margin) / atlasWidth;//r
		this.v0 = (float) (y + h - margin) / atlasHeight;//t
		
		
		this.width = w;
		this.height = h;
	}
}