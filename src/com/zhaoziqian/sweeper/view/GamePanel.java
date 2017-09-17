package com.zhaoziqian.sweeper.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends Panel {

	public static final int ITEM_RADUS = 25;

	public static final int ITEM_BORDER = 3;

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

	private int[][] map;

	public GamePanel(int[][] map) {
		this.map = map;
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
				System.out.println(e.getPoint());
				
				for (int i = 0; i < map.length; i++) {
					for (int j = 0; j < map[0].length; j++) {
						
						int x = 10 + j * ITEM_RADUS - 1;
						int y = 10 + i * ITEM_RADUS - 1;
						
						Rectangle rectangle = new Rectangle(x, y, ITEM_RADUS, ITEM_RADUS);
						
						if(rectangle.contains(e.getPoint())) System.out.println(map[i][j]);
						
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

		// int mapWidth = map[0].length * (ITEM_RADUS + ITEM_BORDER) +
		// ITEM_BORDER;
		// int mapHeight = map.length * (ITEM_RADUS + ITEM_BORDER) +
		// ITEM_BORDER;

		BasicStroke stroke = new BasicStroke(ITEM_BORDER);

		g2.setStroke(stroke);

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {

				int x = 10 + j * ITEM_RADUS - 1;
				int y = 10 + i * ITEM_RADUS - 1;
				g2.drawRect(x, y, ITEM_RADUS, ITEM_RADUS);

				Image image = null;

				switch (map[i][j]) {
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

				}

				g2.drawImage(image, x, y, this);

			}
		}

	}

}
