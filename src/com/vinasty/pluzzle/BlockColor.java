package com.vinasty.pluzzle;

import org.andengine.util.color.Color;


public enum BlockColor {
	BLUE,
	RED,
	GREEN,
	ORANGE,
	PURPLE;
	
	public static BlockColor fromIndex(int index) {
		switch(index) {
		case 0:
			return BLUE;
		case 1: 
			return RED;
		case 2:
			return GREEN;
		case 3:
			return ORANGE;
		case 4:
			return PURPLE;
		}
		throw new RuntimeException("invalid block color index");		
	}
	
	public Color getColor() {
		switch(this) {
		case BLUE:
			return new Color(0.22f, 0.27f, 0.83f);
		case RED:
			return new Color(0.86f, 0.22f, 0.22f);
		case GREEN:
			return new Color(0.25f, 0.76f, 0.2f);
		case ORANGE:
			return new Color(0.89f, 0.64f, 0.25f);
		case PURPLE:
			return new Color(0.72f, 0.31f, 0.87f);
		}
		return Color.WHITE;
	}
	
	public int getIndex() {
		switch(this) {
		case BLUE:
			return 0;
		case RED:
			return 1;
		case GREEN:
			return 2;
		case ORANGE:
			return 3;
		case PURPLE:
			return 4;
		default:
			return 0;
		}
	}
	

}
