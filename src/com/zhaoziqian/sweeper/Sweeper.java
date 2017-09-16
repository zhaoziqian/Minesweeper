package com.zhaoziqian.sweeper;

import com.zhaoziqian.sweeper.logic.CreateMap;

public class Sweeper {

	
	public static void main(String[] args) {
		
		CreateMap cMap = new CreateMap();
		
		int[][] map = cMap.getMap(9, 9, 30);
		
		for (int[] is : map) {
			for (int i : is) {
				System.out.print(i + "  ");
			}
			System.out.println();
		}
		
	}
}
