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

import com.zhaoziqian.sweeper.logic.CreateMap;

public class GamePanel extends Panel {

	private Point change = null;

	private CreateMap cMap = new CreateMap();

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
	private static int[][] map;
	private static int[][] reMap;

	public GamePanel(int[][] map) {
		this.map = map;
		this.reMap = new int[map.length][map[0].length];
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
		this.reMap = new int[map.length][map[0].length];
		for (int i = 0; i < reMap.length; i++) {
			for (int j = 0; j < reMap[0].length; j++) {
				reMap[i][j] = 999;
			}
		}
		GamePanel.this.repaint();
	}

	public void initListener() {

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {

				for (int i = 0; i < map.length; i++) {
					for (int j = 0; j < map[0].length; j++) {

						int x = 10 + j * ITEM_RADUS - ITEM_BORDER;
						int y = 10 + i * ITEM_RADUS - ITEM_BORDER;

						Rectangle rec = new Rectangle(x, y, ITEM_RADUS, ITEM_RADUS);

						if (rec.contains(e.getPoint())) {
							// if(e.getModifiersEx() == e.BUTTON1_DOWN_MASK +
							// e.BUTTON3_DOWN_MASK)
							switch (e.getButton()) {
							case MouseEvent.BUTTON1:
								if (reMap[i][j] == -9) {
									return;
								}

								reMap[i][j] = map[i][j];
								if (reMap[i][j] == 9) {
									reMap[i][j] = -1;
								}
								GamePanel.wave(j, i);
								break;
							case MouseEvent.BUTTON2:
								// 需要考虑地图边界
								for (int k = -1; k < 2; k++) {
									for (int n = -1; n < 2; n++) {

										if (i == 0 && j == 0)
											continue;

										if (i + k < 0 || i + k >= reMap.length)
											continue;

										if (j + n < 0 || j + n >= reMap[0].length)
											continue;

										if (reMap[i + k][j + n] == -9)
											continue;

										reMap[i + k][j + n] = map[i + k][j + n];

										if (reMap[i + k][j + n] == 9) {
											reMap[i + k][j + n] = -1;
										}
									}
								}
								break;
							case MouseEvent.BUTTON3:
								if (reMap[i][j] == -9) {
									reMap[i][j] = 999;
								} else if (reMap[i][j] == 999) {
									reMap[i][j] = -9;
								}
								break;

							default:
								break;
							}

						}

						GamePanel.this.repaint();

					}
				}
			}
		});
	}

	private static void wave(int x, int y) {
		
		if ((map[y][x] >= 1 && map[y][x] <= 8) || map[y][x] == -9) {
			return;
		}
		
		// 只用判断上下左右四个方向
		
		// 上方可行
		if (y - 1 > 0 && map[y - 1][x] != -9 && reMap[y - 1][x] == 999 ){
			reMap[y - 1][x] = map[y - 1][x];
			if (map[y - 1][x] == 0) wave(x ,y - 1);
		}
		// 右方可行
		if (x + 1 < map[0].length && map[y][x + 1] != -9 && reMap[y][x + 1] == 999) {
			reMap[y][x + 1] = map[y][x + 1];
			if (map[y][x + 1] == 0) wave(x + 1 ,y);
		}
		// 下方可行
		if (y + 1 <map.length && map[y + 1][x] != -9 && reMap[y + 1][x] == 999){
			reMap[y + 1][x] = map[y + 1][x];
			if (map[y + 1][x] == 0) wave(x ,y + 1);
		}
		// 左方可行
		if (x - 1 > 0 && map[y][x - 1] != -9 && reMap[y][x - 1] == 999) {
			reMap[y][x - 1] = map[y][x - 1];
			if (map[y][x - 1] == 0) wave(x - 1 ,y);
		}
		
		
//		// 需要考虑地图边界
//		for (int i = -1; i < 2; i++) {
//			for (int j = -1; j < 2; j++) {
//				if (i == 0 && j == 0) continue;
//
//				if (y + i < 0 || y + i >= map.length) continue;
//
//				if (x + j < 0 || x + j >= map[0].length) continue;
//				
////				if (map[y + i][x + j] != 0 ) {
////					continue;
////				}
//				if (map[y + i][x + j] >= 1 && map[y + i][x + j] <= 8) {
//					reMap[y + i][x + j] = map[y + i][x + j];
//				}
//				if (map[y + i][x + j] == 0) {
//					reMap[y + i][x + j] = map[y + i][x + j];
//					if (reMap[y + i][x + j] != 999) {
//						continue;
//					}
//					wave(x + j,y + i);
//				}
//				
////				if (map[y + i][x + j] == 0) {
////					reMap[y + i][x + j] = 0;
////					wave(y + i,x + j);
////				}
//
//			}
//		}
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

				// Rectangle rec = new Rectangle(x, y, ITEM_RADUS, ITEM_RADUS);

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

	public void newMap(int width, int height, int boomCount) {

		this.map = cMap.getMap(width, height, boomCount);
		this.reMap = new int[map.length][map[0].length];
		for (int i = 0; i < reMap.length; i++) {
			for (int j = 0; j < reMap[0].length; j++) {
				reMap[i][j] = 999;
			}
		}
		GamePanel.this.repaint();
	}

}
