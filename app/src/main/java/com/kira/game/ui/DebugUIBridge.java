package com.kira.game.ui;

import java.util.concurrent.ConcurrentLinkedQueue;
import com.kira.game.Game;

public class DebugUIBridge {
	
	private Game pGame;
	
	public final ConcurrentLinkedQueue<Runnable> commandQueue = new ConcurrentLinkedQueue<>();
	
	public DebugUIBridge(Game pGame) {
			
		this.pGame = pGame;
	}
	
	public void spawn(int thing , float x , float y) {
		System.out.println("SPAWING " + thing);
		
		commandQueue.add( () -> {
			System.out.println("package delivered , spawn for : " + thing);
			pGame.spawnE(x , y);
		});
	}
	
	@Deprecated
	public DebugUIBridge getBridge() {return this;}
}