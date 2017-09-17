package com.zhaoziqian.sweeper.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

public class GamePanel extends Panel {
	
	private Point change = null;

	public static final int ITEM_RADUS = 25;

	public static final int ITEM_BORDER = 2;

	public static final Image BLOCK = Toolkit.getDefaultToolkit().getImage("image/swreep/block.png")
			.getScaledInstance(ITEM_RADUS, ITEM_RADUS, Image.SCALE_DEFAULT);
	public static final Image BOOM = Toolkit.getDefaultToolkit().getImage("image/swreep/boom.png")
			.getScaledInstance(ITEM_RADUS, ITEM_RADUS, Image.SCALE_DEFAULT);
	public static final Image IS_BOOM = Toolkit.getDefaultToolkit().getImage("image/swreep/isBoom.png")
			.getScaledInstance(ITEM_RADUS, ITEM_RADUS, Image.SCALE_DEFAULT);
	public static final Image FLAG = Toolkit.getDefaultToolkit().getImage("image/swreep/flag.png")
			.getScaledInstance(ITEM_RADUS, ITEM_RADUS, Image.SCALE_DEFAULT);
	public static final Image ZERO = Toolkit.getDefaultToolkit().getImage("image/swreep/zero.png")
			.getScaledInstance(ITEM_RADUS, ITEM_RADUS, Image.SCALE_DEFAULT);
	public static final Image ONE = Toolkit.getDefaultToolkit().getImage("image/swreep/one.png")
			.getScaledInstance(ITEM_RADUS, ITEM_RADUS, Image.SCALE_DEFAULT);
	public static final Image TWO = Toolkit.getDefaultToolkit().getImage("image/swreep/tow.png")
			.getScaledInstance(ITEM_RADUS, ITEM_RADUS, Image.SCALE_DEFAULT);
	public static final Image THREE = Toolkit.getDefaultToolkit().getImage("image/swreep/three.png")
			.getScaledInstance(ITEM_RADUS, ITEM_RADUS, Image.SCALE_DEFAULT);
	public static final Image FOUR = Toolkit.getDefaultToolkit().getImage("image/swreep/four.png")
			.getScaledInstance(ITEM_RADUS, ITEM_RADUS, Image.SCALE_DEFAULT);
	public static final Image FIVE = Toolkit.getDefaultToolkit().getImage("image/swreep/five.png")
			.getScaledInstance(ITEM_RADUS, ITEM_RADUS, Image.SCALE_DEFAULT);
	public static final Image SIX = Toolkit.getDefaultToolkit().getImage("image/swreep/six.png")
			.getScaledInstance(ITEM_RADUS, ITEM_RADUS, Image.SCALE_DEFAULT);
	public static final Image SEVEN = Toolkit.getDefaultToolkit().getImage("image/swreep/seven.png")
			.getScaledInstance(ITEM_RADUS, ITEM_RADUS, Image.SCALE_DEFAULT);
	public static final Image EIGHT = Toolkit.getDefaultToolkit().getImage("image/swreep/eight.png")
			.getScaledInstance(ITEM_RADUS, ITEM_RADUS, Image.SCALE_DEFAULT);

	public static final Image block = new ImageIcon("image/swreep/block.png").getImage();
	private int[][] map;
	private int[][] reMap;

	public GamePanel(int[][] map) {
		this.map = map;
		this.reMap = new int[map.length][map[0].length];
//		for (int[] is : reMap) {
//			for (int i : is) {
//				i = 999;
//			}
//		}
		for (int i = 0; i < reMap.length; i++) {
			for (int j = 0; j < reMap[0].length; j++) {
				reMap[i][j] = 999;
			}
		}
		initListener();
	}

	public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {
		this.map = map;
	}
	
	
	
	public void initListener(){
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
				
				for (int i = 0; i < map.length; i++) {
					for (int j = 0; j < map[0].length; j++) {
						
						int x = 10 + j * ITEM_RADUS - ITEM_BORDER;
						int y = 10 + i * ITEM_RADUS - ITEM_BORDER;
						
						Rectangle rec = new Rectangle(x, y, ITEM_RADUS, ITEM_RADUS);
						
						
						if(rec.contains(e.getPoint())){
							
							switch (e.getButton()) {
							case 1:
								if(reMap[i][j] == -9){
									return;
								}
								
								reMap[i][j] = map[i][j];
								if(reMap[i][j] == 9){
									reMap[i][j] = -1;
								}
								break;
							case 2:
								// 需要考虑地图边界
								for (int k = -1 ; k < 2; k++) {
									for (int n = -1 ; n < 2; n++) {
										
										if (i == 0 && j == 0) continue;
										
										if(i + k < 0 || i + k >= reMap.length) continue;
										
										if(j + n < 0 || j + n >= reMap[0].length) continue;
										
										if(reMap[i + k][j + n] == -9) continue;
										
										reMap[i + k][j + n] = map[i + k][j + n];
										
										if(reMap[i + k][j + n] == 9){
											reMap[i + k][j + n] = -1;
										}
									}
								}
								break;
							case 3:
								if(reMap[i][j] != 999){
									return;
								}
								
								if(reMap[i][j] == -9){
									reMap[i][j] = 999;
								}else{
									reMap[i][j] = -9;
								}
								break;

							default:
								break;
							}
							
						}
						
						
//						if(e.getButton() == 1 && rec.contains(e.getPoint()) ) {
//							if(reMap[i][j] == -9){
//								return;
//							}
//							
//							reMap[i][j] = map[i][j];
//							if(reMap[i][j] == 9){
//								reMap[i][j] = -1;
//							}
//						}
//						
//						if(e.getButton() == 3 && rec.contains(e.getPoint())){
//							if(reMap[i][j] == -9){
//								reMap[i][j] = 999;
//							}else{
//								reMap[i][j] = -9;
//							}
//						}
//						if(e.getButton() == 2 && rec.contains(e.getPoint())){
//
//							// 需要考虑地图边界
//							for (int k = -1 ; k < 2; k++) {
//								for (int n = -1 ; n < 2; n++) {
//									if (i == 0 && j == 0) continue;
//									
//									if(i + k < 0 || i + k >= reMap.length) continue;
//									
//									if(j + n < 0 || j + n >= reMap[0].length) continue;
//									
//									reMap[i + k][j + n] = map[i + k][j + n];
//								}
//							}
//						}
						
						

						GamePanel.this.repaint();
						
					}
				}
			}
		});
}
	
	
	

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(new Color(25, 25, 25));

		BasicStroke stroke = new BasicStroke(ITEM_BORDER);

		g2.setStroke(stroke);

		for (int i = 0; i < reMap.length; i++) {
			for (int j = 0; j < reMap[0].length; j++) {

				int x = 10 + j * ITEM_RADUS - ITEM_BORDER;
				int y = 10 + i * ITEM_RADUS - ITEM_BORDER;
				
				g2.drawRect(x, y, ITEM_RADUS, ITEM_RADUS);

				Image image = null;
				
//				Rectangle rec = new Rectangle(x, y, ITEM_RADUS, ITEM_RADUS);
				
					switch (reMap[i][j]) {
					case 0:
						image = ZERO;
						break;
					case 1:
						image = ONE;
						break;
					case 2:
						image = TWO;
						break;
					case 3:
						image = THREE;
						break;
					case 4:
						image = FOUR;
						break;
					case 5:
						image = FIVE;
						break;
					case 6:
						image = SIX;
						break;
					case 7:
						image = SEVEN;
						break;
					case 8:
						image = EIGHT;
						break;
					case 9:
						image = BOOM;
						break;
					case -9:
						image = FLAG;
						break;
					case -1:
						image = IS_BOOM;
						break;
					default:
						image = BLOCK;
						break;
					}
					g2.drawImage(image, x, y, this);

				

			}
		}

	}

}
