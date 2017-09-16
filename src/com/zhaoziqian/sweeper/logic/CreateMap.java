package com.zhaoziqian.sweeper.logic;

import java.util.Random;

/**
 * 
* @ClassName: CreateMap
* @Description: 用来生成游戏初始地图的类
* @author zhaoziqian
* @date 2017年9月16日 下午4:50:58
*
 */
public class CreateMap {

	
	/*
	 * 扫雷游戏中的地图都是矩形，在其中放置地雷
	 * 1、选择游戏地图大小
	 * 2、设置地雷数
	 * 3、生成空白地图
	 * 4、在空白地图上布置地雷
	 * 5、在地雷周围设置数字提示
	 * 6、结束生成
	 * 
	 * 那么由上，生成地图是不是可以有工厂方法？
	 */
	
	/**
	 * 游戏地图
	 * 
	 * 9 	- 表示地雷
	 * 1至8 	- 地图提示信息
	 * 0 	- 空白地域
	 * -9   - 表示标记
	 */
	private int[][] map ;
	
	private int width;
	
	private int height;
	/**
	 * 地雷数
	 */
	private int boomCount;
	
	
	
	public int[][] getMap(Integer width , Integer height , int boomCount){
		
		this.createBlankMap(width, height);
		this.setBoomCount(boomCount);
		this.setBoom();
		this.setTipNum();
		return this.map;
		
	}
	
	
	/**
	 * 
	* @Title: createBlankMap 
	* @Description: 得到空白的地图
	* @param @param width 地图的宽
	* @param @param height 地图的高 
	* @return void    返回类型 
	* @throws
	 */
	private void createBlankMap(Integer width , Integer height){
		
		if (width == null || width == 0 || height == null || height == 0) {
			width = 25;
			height = 25;
		}
		this.width = width;
		this.height = height;
		this.map = new int[width][height];
		
		// 初始化0到地图上
		for (int[] is : map) {
			for (int i : is) {
				i = 0;
			}
		}
	}
	
	private void setBoomCount(int count){
		this.boomCount = count;
	}
	
	private void setBoom(){
		// 生成随机数对象
		Random random = new Random();
		
		int count = this.boomCount;

		while(count > 0){
			int x = random.nextInt(this.width);
			int y = random.nextInt(this.height);
			
			// 排布地雷
			if (this.map[x][y] != 9) {
				this.map[x][y] = 9;
				count-- ;
			}
		}
	}
	
	private void setTipNum(){
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if(this.map[i][j] == 9) continue;
				this.map[i][j] = this.checkItemBoomForMap(j, i);
				
			}
		}
		
		
	}
	
	/**
	 * 
	* @Title: checkItemBoomForMap 
	* @Description: 检查当前格子周围有几颗炸弹 
	* @param @param x 格子的横坐标 列
	* @param @param y 格子的纵坐标 行
	* @param @return  返回该格子周围（最多8个）包含的炸弹数
	* @return int  返回类型 
	* @throws
	 */
	private int checkItemBoomForMap(int x , int y){
		
		int itemBoomCount = 0;
		
		// 需要考虑地图边界
		for (int i = -1 ; i < 2; i++) {
			for (int j = -1 ; j < 2; j++) {
				if (i == 0 && j == 0) continue;
				
				if(y + i < 0 || y + i >= height) continue;
				
				if(x + j < 0 || x + j >= width) continue;
				
				if(this.map[y + i][x + j] == 9) itemBoomCount++;
				
			}
		}
		
		return itemBoomCount;
	}
	
	
}
