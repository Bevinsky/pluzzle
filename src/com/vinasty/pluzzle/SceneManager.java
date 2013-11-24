package com.vinasty.pluzzle;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;

public class SceneManager {
	
	
	private MainActivity activity;
	private Engine engine;
	private Camera camera;
	
	public SceneManager(MainActivity a, Engine e, Camera c) {
		this.activity = a;
		this.engine = e;
		this.camera = c;
	}
	
	
	
}
