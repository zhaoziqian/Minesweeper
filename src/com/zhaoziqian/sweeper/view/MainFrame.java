package com.zhaoziqian.sweeper.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.zhaoziqian.sweeper.logic.CreateMap;

import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JButton;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {

	/**
	 * 生成初始化地图的类
	 */
	private CreateMap cMap = new CreateMap();
	/**
	 * 存放本次游戏根地图的数组
	 */
	private static int[][] rootMap;
	/**
	 * 存放本次游戏界面地图的数组
	 */
	private static int[][] operateMap;
	/**
	 * 地雷数
	 */
	private static int boomCount = 10;

	public static final Image BLOCK = Toolkit.getDefaultToolkit().getImage("image/swreep/block.png")
			.getScaledInstance(GamePanel.ITEM_RADUS, GamePanel.ITEM_RADUS, Image.SCALE_DEFAULT);
	public static final Image FLAG = Toolkit.getDefaultToolkit().getImage("image/swreep/flag.png")
			.getScaledInstance(GamePanel.ITEM_RADUS, GamePanel.ITEM_RADUS, Image.SCALE_DEFAULT);

	private JPanel contentPane;

	private JLayeredPane control;

	private Panel gameMap;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initFrame() {

		rootMap = cMap.getMap(null, null, boomCount);
		operateMap = rootMap.clone();

		int frameWidth = 10 + 10 + (GamePanel.ITEM_RADUS + GamePanel.ITEM_BORDER) * rootMap[0].length
				+ GamePanel.ITEM_BORDER;

		int frameHeight = 50 + 30 + 30 + (GamePanel.ITEM_RADUS + GamePanel.ITEM_BORDER) * rootMap.length
				+ GamePanel.ITEM_BORDER;

		setTitle("扫雷");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, frameWidth, frameHeight);

		// 加盖一层操作层

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("菜单");
		menuBar.add(menu);

		JMenuItem menuItem = new JMenuItem("重新开始");
		menu.add(menuItem);

		JSeparator separator = new JSeparator();
		menu.add(separator);

		JCheckBoxMenuItem checkBoxMenuItem = new JCheckBoxMenuItem("初级");
		menu.add(checkBoxMenuItem);

		JCheckBoxMenuItem checkBoxMenuItem_1 = new JCheckBoxMenuItem("中级");
		menu.add(checkBoxMenuItem_1);

		JCheckBoxMenuItem checkBoxMenuItem_2 = new JCheckBoxMenuItem("高级");
		menu.add(checkBoxMenuItem_2);

		JSeparator separator_1 = new JSeparator();
		menu.add(separator_1);

		JCheckBoxMenuItem checkBoxMenuItem_3 = new JCheckBoxMenuItem("自定义");
		menu.add(checkBoxMenuItem_3);

		JSeparator separator_2 = new JSeparator();
		menu.add(separator_2);

		JMenuItem menuItem_5 = new JMenuItem("退出");
		menu.add(menuItem_5);

		JMenu menu_1 = new JMenu("关于");
		menuBar.add(menu_1);

		JMenuItem mntmNewMenuItem = new JMenuItem("作者");
		menu_1.add(mntmNewMenuItem);

		JMenuItem menuItem_1 = new JMenuItem("帮助");
		menu_1.add(menuItem_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JButton btnNewButton = new JButton("重新开始");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				System.out.println(111111);
				((GamePanel) gameMap).newMap(rootMap[0].length, rootMap.length, boomCount);
				;
			}
		});
		contentPane.add(btnNewButton, BorderLayout.NORTH);

		gameMap = new GamePanel(rootMap);
		contentPane.add(gameMap, BorderLayout.CENTER);
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setResizable(false);
		initFrame();
		
		for (int[] is : rootMap) {
			for (int i : is) {
				System.out.print(i + "  ");
			}
			System.out.println();
		}
	}

}
