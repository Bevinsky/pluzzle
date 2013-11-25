package com.vinasty.pluzzle;

import java.util.ArrayList;

import org.andengine.entity.Entity;
import org.andengine.entity.modifier.EntityModifier;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;

import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

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
		Log.d("containstest", "coords:" + pX + "," + pY);
		return pX >= this.getX() && pX < this.getX()+mWidth*BLOCK_SIZE &&
			   pY >= this.getY() && pY < this.getY()+mHeight*BLOCK_SIZE;
	}
	
	
	boolean pressed = false;
	float mouseX = 0;
	float mouseY = 0;
	
	public void onPress(float x, float y) {
		Log.d("mouse", "onPress: " + x + "," + y);
		mouseX = x;
		mouseY = y;
		
		//Log.d("hittest", hitTest(x, y).toString());
	}
	
	public void onRelease(float x, float y) {
		//Log.d("mouse", "onRelease: " + x + "," + y);
		
	}
	
	public void onMove(float x, float y) {
		//Log.d("mouse", "onMove: " + x + "," + y);
		mouseX = x;
		mouseY = y;
	}
	
	public Point hitTest(float x, float y) {		
		for(int xi = 0;xi < mWidth;xi++) {
			GridColumn col = grid.get(xi);
			for(int yi = 0; yi < col.size(); yi++) {
				if( x >= getXForColumn(xi) &&
					x < getXForColumn(xi)+BLOCK_SIZE &&
					y >= getYForRow(yi) &&
					y < getYForRow(yi)+BLOCK_SIZE &&
					col.get(yi).velocity == 0) {
					
					return new Point(xi, yi);
				}
			}			
		}
		return new Point(-1, -1);
	}
	
	
	
	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {
		//Log.d("containstest", "oAT:" + pTouchAreaLocalX + "," + pTouchAreaLocalY);
		if(pSceneTouchEvent.isActionDown())
			addBlock((int)(Math.random()*7), GlobalRandom.getRandomColor(), GlobalRandom.getRandomBlockNumber());
		
		if(pSceneTouchEvent.isActionDown() && !pressed) {
			pressed = true;
			onPress(pTouchAreaLocalX, pTouchAreaLocalY);
		}
		if((pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionOutside()) && pressed) {
			pressed = false;
			onRelease(pTouchAreaLocalX, pTouchAreaLocalY);
		}
		if(pSceneTouchEvent.isActionMove() && pressed) {
			onMove(pTouchAreaLocalX, pTouchAreaLocalY);
		}
		
		
		return false;
	}
	
	
	

}
