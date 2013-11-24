package com.vinasty.pluzzle;

import java.util.ArrayList;

import org.andengine.entity.Entity;
import org.andengine.entity.modifier.EntityModifier;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;

public class GameGrid extends Entity implements ITouchArea {
	
	public static final int BLOCK_SIZE = 90;
	
	private MainActivity ac;
	private ArrayList<GridColumn> grid;
	private int mHeight;
	private int mWidth;
	
	public GameGrid(MainActivity ac, int width, int height) {
		this.ac = ac;
		this.mHeight = height;
		this.mWidth = width;
		grid = new ArrayList<GridColumn>(width);
		for(int i = 0; i < width; i++) {
			grid.add(new GridColumn(height));
			
		}
		
		//attachChild(new Sprite(0,0,90*8, 90*13, ac.regionMap.get("gridBG"), ac.getVertexBufferObjectManager()));
		
		
	}
	
	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		for(int x = 0; x < mWidth; x++) {
			GridColumn col = grid.get(x);
			for(int y = 0;y<col.size();y++) {
				BlockSprite bl = col.get(y);
				if(bl.getY() < getYForRow(y)) {
					if(bl.velocity == 0)
						bl.velocity = 30;
					bl.velocity = (float)Math.min(bl.velocity + 15*pSecondsElapsed, BLOCK_SIZE*1.5);
					bl.setY(bl.getY() + bl.velocity);
				}
				else {
					bl.velocity = 0;
				}
				bl.setY(Math.min(getYForRow(y), bl.getY()));
				
				
				
			}
			
			
			
		}
		
		
		super.onManagedUpdate(pSecondsElapsed);
	}
	
	
	public boolean addBlock(int column, BlockColor color, int number) {
		if(grid.get(column).size() == mHeight) {
			return false;
		}
		
		
		BlockSprite bs = new BlockSprite(ac,color, number);
		
		bs.setX(getXForColumn(column));
		bs.setY(-BLOCK_SIZE);
		
		attachChild(bs);
		grid.get(column).add(bs);
		
		return true;
	}

	public float getYForRow(int row) {
		return mHeight*BLOCK_SIZE - BLOCK_SIZE - BLOCK_SIZE*row;
	}
	
	public float getXForColumn(int col) {
		return col*BLOCK_SIZE;
	}

	@Override
	public boolean contains(float pX, float pY) {
		
		return true;
	}

	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {
		if(pSceneTouchEvent.isActionDown())
			addBlock((int)(Math.random()*7), GlobalRandom.getRandomColor(), GlobalRandom.getRandomBlockNumber());
		return false;
	}
	
	
	

}
