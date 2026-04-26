package com.kira.game.assets;

import com.kira.game.graphics.SpriteRegion;

public class TileSheet {
	
	private int tileSize = 16;
	private int spacing = 1;
	private int columns = 18;
	private int rows = 11;
	
	private int atlasWidth;
	private int atlasHeight;
	
	public TileSheet() {
		
		this.atlasWidth = columns * (tileSize + spacing) + spacing;
		this.atlasHeight = rows * (tileSize + spacing) + spacing;
	}
	
	public SpriteRegion getSprite(int col , int row) {
		
		int x = spacing + col * (tileSize + spacing);
		int y = spacing + row * (tileSize + spacing);
		
		return new SpriteRegion(atlasWidth , atlasHeight , x , y , tileSize , tileSize);
	}
	
	public SpriteRegion getSprite(int tileIndex) {
		
		int col = tileIndex % columns;
		int row = tileIndex / columns;
		
		return getSprite(col , row);
	}
}