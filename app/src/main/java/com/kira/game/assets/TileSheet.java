package com.kira.game.assets;

import com.kira.game.graphics.resources.SpriteRegion;

public class TileSheet {
	
	private final int TILE = 16;
	private final int GAP = 1;
	private final int STRIDE = TILE + GAP;
	private int columns = 11;
	private int rows = 18;
	
	private int atlasWidth;
	private int atlasHeight;
	
	public TileSheet() {
		
		this.atlasWidth = columns * TILE + (columns - 1) * GAP;
		this.atlasHeight = rows * TILE + (rows - 1) * GAP;
	}
	
	public SpriteRegion getSprite(int row , int col) {
		
		int x = col * STRIDE;
		int y = row * STRIDE;
		
		return new SpriteRegion(atlasWidth , atlasHeight , x , y , TILE , TILE);
	}
	
	@Deprecated
	public SpriteRegion getSprite(int tileIndex) {
		
		int col = (tileIndex - 1) / columns;
		int row = (tileIndex - 1) % columns;
		
		return getSprite(row  , col);
	}
}