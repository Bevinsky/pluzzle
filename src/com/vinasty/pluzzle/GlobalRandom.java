package com.vinasty.pluzzle;

import java.util.Random;

public class GlobalRandom {
	
	static Random rand = new Random();
	
	public static int[][] score_table = new int[][] {
			//	  1   2   3   4   5   6   7   8
				{ 0,  0,  0,  0,  0,  0,  0,  0  },	// 1 match
				{ 0,  10, 10, 10, 10, 10, 10, 10 },	// 2 matches
				{ 15, 15, 10, 10, 10, 15, 15, 20 },	// 3 matches
				{ 15, 10, 10, 10, 15, 15, 25, 0  },	// 4 matches
				{ 15, 5 , 15, 15, 15, 30, 0,  0  },	// 5 matches
				{ 20, 15, 15, 15, 30, 0 , 0 , 0  },	// 6 matches
				{ 20, 15, 15, 35, 0 , 0 , 0 , 0  },	// 7 matches
				{ 20, 15, 35, 0 , 0 , 0 , 0 , 0  },	// 8 matches
				{ 20, 40, 0 , 0 , 0 , 0 , 0 , 0  },	// 9 matches
				{ 100,0 , 0 , 0 , 0 , 0 , 0 , 0  }	// 10 matches
			};
	
	public static int[] number_bias = new int[] {
			4, // 1
			7,
			5,
			4,
			2,
			1,
			2,
			1  // 8
	};
	
	
	public static int bias_limit = 1;
	public static int[] color_bias = new int[] {
		20,
		20,
		20,
		20,
		20
	};
	
	public static float[] block_timers = new float[] {
		3.5f,
		2.5f,
		2.0f
	};

	public static int[] chance_widthwave = new int[] {
		20,
		50,
		80
	};
	
	public static int[] chance_randomwave = new int[] {
		2,
		6,
		10
	};
	
	public static float[] chance_randomwave_slope = new float[] {
		1f,
		1.10f,
		1.3f
	};
	
	public static BlockColor getRandomColor() {
		
		int random = getRandomIndexForDistribution(color_bias);
		bias_limit--;
		
		if(bias_limit == 0) {
			bias_limit = rand.nextInt(30+15)+15;
			int one = rand.nextInt(5);
			int two = rand.nextInt(5);
			
			int boost = 20;
			int norm = 20;
			
			if(one == two) {
				boost = 40;
				norm = 15;
			}
			else {
				boost = 30;
				norm = 13;
			}
			
			for(int i = 0;i<color_bias.length;i++)
				color_bias[i] = norm;
			
			color_bias[one] = boost;
			color_bias[two] = boost;
			
		}
		return BlockColor.fromIndex(random);
	}
	
	public static int getRandomBlockNumber() {
		return getRandomIndexForDistribution(number_bias)+1;
	}

	
	public static int getRandomIndexForDistribution(int[] dist) {
		int totalsum = 0;
		for(int x : dist)
			totalsum += x;
		
		int sum = 0;
		int index = 0;
		int random = rand.nextInt(totalsum);
		
		for(int i = 0;i < dist.length; i++) {
			sum += dist[i];
			if(random < sum) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	
	
}
