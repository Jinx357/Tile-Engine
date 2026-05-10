package com.kira.game.world.map;



public class TileMap {
	
	private int mapHeight;
	private int mapWidth;
	private int tileSize;
	
	private int[][] tiles;
	
	public TileMap(int mapHeight , int mapWidth , int tileSize) {
		
		this.mapHeight = mapHeight;
		this.mapWidth = mapWidth;
		this.tileSize = tileSize;
		
		this.tiles = new int [mapHeight][mapWidth];
	}
	
	public void setTile(int h , int w , int tile) {
		
		tiles[h][w] = tile;
	}
	
	public void setTiles(int[][] tiles) {
		
		this.tiles = tiles;
	}
	
	public int screenToTile(float coord) {
		
		return (int)(coord/tileSize);
	}
	
	public int[][] getMap() {return tiles;}
	public int getMapHeight() {return mapHeight;}
	public int getMapWidth() {return mapWidth;}
	public int getTileSize() {return tileSize;}
}