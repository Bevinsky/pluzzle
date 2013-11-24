package com.vinasty.pluzzle;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;

public class GameScene extends Scene {
	
	MainActivity ac;
	GameGrid gameGrid;

	public GameScene(MainActivity ac) {
		this.ac = ac;
		
		attachChild(new Sprite(0,0,ac.regionMap.get("gameBG"), ac.getVertexBufferObjectManager()));
		
		gameGrid = new GameGrid(ac, 7, 12);
		gameGrid.setX(45);
		gameGrid.setY(155);
		registerTouchArea(gameGrid);
		
		attachChild(gameGrid);
		
	}

}
