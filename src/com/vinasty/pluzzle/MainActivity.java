package com.vinasty.pluzzle;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.opengl.texture.render.RenderTexture;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends SimpleBaseGameActivity {
	
	public HashMap<String, ITextureRegion> regionMap;
	
	
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        regionMap = new HashMap<String, ITextureRegion>();
    }

	@Override
	public EngineOptions onCreateEngineOptions() {
		// TODO Auto-generated method stub
		final Camera camera = new Camera(0, 0, 720, 1280);
		return new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED, new RatioResolutionPolicy(720, 1280), camera);
	}

	@Override
	protected void onCreateResources() {
		// TODO Auto-generated method stub
		try {
			ITexture blockTex = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
				
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/blocks.png");
				}
			});
			
			ITexture gridBackgroundTexture = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
				
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/gamegridbg.png");
				}
			});
			
			ITexture gameBg = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
				
				@Override
				public InputStream open() throws IOException {
					return getAssets().open("gfx/gamebg.png");
				}
			});
			
			blockTex.load();
			gridBackgroundTexture.load();
			gameBg.load();
			
			regionMap.put("whiteBlock", TextureRegionFactory.extractFromTexture(blockTex, 0, 0, 90, 90));
			regionMap.put("blockOverlay", TextureRegionFactory.extractFromTexture(blockTex, 0, 180, 90, 90));
			
			regionMap.put("oneNumber", TextureRegionFactory.extractFromTexture(blockTex, 90, 0, 90, 90));
			regionMap.put("twoNumber", TextureRegionFactory.extractFromTexture(blockTex, 180, 0, 90, 90));
			regionMap.put("threeNumber", TextureRegionFactory.extractFromTexture(blockTex, 270, 0, 90, 90));
			regionMap.put("fourNumber", TextureRegionFactory.extractFromTexture(blockTex, 360, 0, 90, 90));
			
			regionMap.put("fiveNumber", TextureRegionFactory.extractFromTexture(blockTex, 0, 90, 90, 90));			
			regionMap.put("sixNumber", TextureRegionFactory.extractFromTexture(blockTex, 90, 90, 90, 90));
			regionMap.put("sevenNumber", TextureRegionFactory.extractFromTexture(blockTex, 180, 90, 90, 90));
			regionMap.put("eightNumber", TextureRegionFactory.extractFromTexture(blockTex, 270, 90, 90, 90));
			regionMap.put("nineNumber", TextureRegionFactory.extractFromTexture(blockTex, 360, 90, 90, 90));
			
			regionMap.put("gridBG", TextureRegionFactory.extractFromTexture(gridBackgroundTexture));
			regionMap.put("gameBG", TextureRegionFactory.extractFromTexture(gameBg));
			
		}
		catch(IOException e1) {
			Debug.e(e1);
			
		}
	}

	@Override
	protected Scene onCreateScene() {
		GameScene gs = new GameScene(this);
		
		return gs;
		
//		Scene s = new Scene();
//		BlockSprite bs = new BlockSprite(this, BlockColor.RED, 4) {
//			@Override
//			protected void onManagedUpdate(float pSecondsElapsed) {
//				// TODO Auto-generated method stub
//				float aX = 50;
//				float aY = 45;
//				float xOff = (float)(Math.random()*8-4);
//				float yOff = (float)(Math.random()*8-4);
//				this.setX(aX + xOff);
//				this.setY(aY + yOff);
//				super.onManagedUpdate(pSecondsElapsed);
//			}
//		};
//		GameGrid gg = new GameGrid(this, 7, 12);
//		s.attachChild(gg);
//		
//		gg.attachChild(bs);
//		s.registerTouchArea(bs);
//		return s;
	}
}
