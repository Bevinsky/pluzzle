package com.vinasty.pluzzle;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.vbo.ISpriteVertexBufferObject;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.shader.ShaderProgram;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.DrawType;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import android.location.Address;

public class BlockSprite extends Sprite {
	
	MainActivity ac;
	public float velocity = 0;
	private Sprite whiteSprite;
	
	public BlockSprite(MainActivity ac, BlockColor c, int number) {
		super(0, 0, 90, 90, ac.regionMap.get("whiteBlock"), ac.getVertexBufferObjectManager());
		attachChild(new Sprite(0,0,90,90,ac.regionMap.get("blockOverlay"), ac.getVertexBufferObjectManager()));
		attachChild(new Sprite(0,0,90,90,ac.regionMap.get(getNumberTextureName(number)), ac.getVertexBufferObjectManager()));
		
		whiteSprite = new Sprite(0, 0, 90, 90,ac.regionMap.get("whiteBlock"), ac.getVertexBufferObjectManager());
		whiteSprite.setAlpha(0f);
		attachChild(whiteSprite);
		this.setColor(c.getColor());
	}
	
	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
			float pTouchAreaLocalX, float pTouchAreaLocalY) {
		// TODO Auto-generated method stub
		
		if(pSceneTouchEvent.isActionDown())
			whiteSprite.setAlpha(0.5f);
		if(pSceneTouchEvent.isActionUp())
			whiteSprite.setAlpha(0.0f);
		return super
				.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
	}
	
	public static String getNumberTextureName(int number) {
		switch(number) {
		case 1:
			return "oneNumber";
		case 2:
			return "twoNumber";
		case 3:
			return "threeNumber";
		case 4:
			return "fourNumber";
		case 5:
			return "fiveNumber";
		case 6:
			return "sixNumber";
		case 7:
			return "sevenNumber";
		case 8:
			return "eightNumber";
		case 9:
			return "nineNumber";
		default:
			return "oneNumber";
		}
		
	}

}
