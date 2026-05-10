package com.kira.game.assets;

import com.kira.game.graphics.resources.SpriteRegion;

public class TileSheet {
	
	private final int TILE;
	private final int GAP;
	private final int STRIDE;
	private int columns;
	private int rows;
	
	private int atlasWidth;
	private int atlasHeight;
	
	public TileSheet(int tileSize , int gap , int rows , int columns) {
		
		this.TILE = tileSize;
		this.GAP = gap;
		this.rows = rows;
		this.columns = columns;
		this.STRIDE =  this.TILE + this.GAP;
		
		this.atlasWidth = columns * TILE + (columns - 1) * GAP;
		this.atlasHeight = rows * TILE + (rows - 1) * GAP;
	}
	
	public SpriteRegion getSprite(int row , int col) {
		
		int x = col * STRIDE;
		int y = row * STRIDE;
		
	System.out.println("assets[TILESHEET] ->"+ row + " " + col);
		return new SpriteRegion(atlasWidth , atlasHeight , x , y , TILE , TILE);
	}
	
	public SpriteRegion getSprite(int tileIndex) {
		
		int row = (tileIndex - 1) / columns;
		int col = (tileIndex - 1) % columns;
		
		row = (this.rows - 1) - row;
		
		System.out.println("->"+ row + " " + col);
		return getSprite(row  , col);
	}
}