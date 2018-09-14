package my_blocks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JOptionPane;

/**
 * 方块的样子
 * 
 * @author 夏忍
 * 
 */
public class BlockShape {

	public static final int[][][] SHAPE_BLOCKS = {
	// I
			{ { 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{ 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 },
					{ 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{ 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 }, },
			// S
			{ { 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{ 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, },
					{ 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{ 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, }, },
			// Z
			{ { 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{ 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, },
					{ 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{ 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, }, },
			// O
			{ { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{ 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{ 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{ 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, }, },
			// L
			{ { 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{ 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, },
					{ 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{ 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, } },
			// J
			{ { 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{ 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, },
					{ 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{ 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, } },
			// E
			{ { 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{ 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, },
					{ 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
					{ 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, } } };
	/**
	 * 绝对坐标
	 */
	private Point pBlock = null;
	/**
	 * 格子坐标
	 */
	public Point pblockPoint = null;
	/**
	 * 方格的宽度
	 */
	public static final int BLOCK_WIDTH = 20;
	/**
	 * 线程类向下移动
	 */
	private BlockThread blockThread = null;
	/**
	 * 方块类型
	 */
	protected int shapeNum = 0;
	/**
	 * 方块下一个类型
	 */
	public static int shapeNumNext = 0;
	/**
	 * 这个类型改变后的类型
	 */
	int shapeNumChange = 0;
	/**
	 * 判断是否摧毁线程
	 */
	private boolean breakThread = false;
	/**
	 * 这个表示是判断是否是第一个图形
	 */
	public static boolean ifFirstBlock = true;

	/**
	 * 乘放图形的容器的宽度(格子数)
	 */
	public static final int BLOCK_NUM = 4;

	/**
	 * 图形的颜色
	 */
	public static final Color BLOCK_COLOR = Color.YELLOW;
	/**
	 * 掉落后的颜色
	 */
	public static final Color BLOCK_COLOR1 = Color.WHITE;
	/**
	 * 下一个图形的预定义样子时的颜色
	 */
	public static final Color BLOCK_COLOR_NEXT = Color.CYAN;

	/**
	 * 面板类的引用
	 */
	private MainJPanel panel = null;

	public Point getpBlock() {
		return pBlock;
	}

	/**
	 * 根据格子坐标得到绝对坐标
	 * 
	 * @param pBlock
	 */
	public void setpBlock(Point pblockPoint) {
		this.pBlock.x = pblockPoint.x * BLOCK_WIDTH;
		this.pBlock.y = pblockPoint.y * BLOCK_WIDTH;
	}

	public Point getPblockPoint() {
		return pblockPoint;
	}

	public void setPblockPoint(Point pblockPoint) {
		this.pblockPoint = pblockPoint;
		setpBlock(pblockPoint);
	}

	/**
	 * 创建方块类对象的时候，顺便创建线程
	 * 
	 * @param g
	 */
	public BlockShape(Point pblockPoint, MainJPanel panel) {
		/**
		 * 根据其是否是第一次图形，预定义下一个图形的样子
		 */
		if (ifFirstBlock) {
			BlockShape.shapeNumNext = (int) (Math.random() * SHAPE_BLOCKS.length);
			shapeNum = (int) (Math.random() * SHAPE_BLOCKS.length);
			ifFirstBlock = false;
		} else {
			shapeNum = BlockShape.shapeNumNext;
			BlockShape.shapeNumNext = (int) (Math.random() * SHAPE_BLOCKS.length);
		}
		this.pblockPoint = pblockPoint;
		pBlock = new Point(pblockPoint);
		this.panel = panel;
		blockThread = new BlockThread();
	}

	/**
	 * 根据当前的形状得到该图形的高度
	 * 
	 * @param falg
	 *            falg == true表示判断当前形状的高度，falg==false表示判断得到下一个形状的高度
	 * @return 高度
	 */
	protected int getBlockHeight(boolean falg) {
		int height = 0;
		int[] arr = null;
		if (falg) {
			arr = SHAPE_BLOCKS[shapeNum][shapeNumChange];
		} else {
			arr = SHAPE_BLOCKS[shapeNum][changeShape()];

		}
		int i;
		for (i = arr.length - 1; i >= 0; i--) {
			if (arr[i] == 1) {
				break;
			}
		}
		height = i / BLOCK_NUM + 1;
		return height;
	}

	/**
	 * 求宽度
	 * 
	 * @param falg
	 * @return
	 */
	protected int getBlockWidth(boolean falg) {
		int width = 0;
		int[] arr = null;
		if (falg) {
			arr = SHAPE_BLOCKS[shapeNum][shapeNumChange];
		} else {
			arr = SHAPE_BLOCKS[shapeNum][changeShape()];
		}
		int[][] widthArr = getDoubleArr(arr);
		int count = 0;
		for (int i = 0; i < widthArr.length; i++) {
			for (int j = widthArr[0].length - 1; j >= 0; j--) {
				if (widthArr[i][j] == 1 && j >= count) {
					count = j;
					break;
				}
			}
		}
		width = count + 1;
		return width;
	}

	/**
	 * 画自己 lineNum是行数 lieNum是列数
	 * 
	 * @param g
	 */
	protected void drawBlock(Graphics g) {
		g.setColor(BLOCK_COLOR);
		for (int i = 0; i < SHAPE_BLOCKS[0][0].length; i++) {
			if (SHAPE_BLOCKS[shapeNum][shapeNumChange][i] == 1) {
				int lineNum = i / 4;
				int lieNum = i % 4;
				g
						.fill3DRect(pBlock.x + BLOCK_WIDTH * lieNum, pBlock.y
								+ BLOCK_WIDTH * lineNum, BLOCK_WIDTH,
								BLOCK_WIDTH, true);
			}
		}
	}

	/**
	 * 得到下一个形状数字的方法
	 * 
	 * @return 数字
	 */
	protected int changeShape() {
		int numChange = shapeNumChange + 1;
		if (numChange == 4) {
			numChange = 0;
		}
		return numChange;
	}

	/**
	 * 将当前的16位的一维数组改为4 * 4 的二维数组
	 * 
	 * @param arr
	 * @return
	 */
	private int[][] getDoubleArr(int[] arr) {
		int[][] widthArr = new int[BLOCK_NUM][BLOCK_NUM];
		for (int i = 0, j = 0, k = 0; i < arr.length; i++) {
			widthArr[j][k] = arr[i];
			k++;
			if (k == 4) {
				k = 0;
				j++;
			}
		}
		return widthArr;

	}

	/**
	 * 判断是否能够移动
	 * 
	 * @return
	 */
	public boolean isMove() {
		boolean falg = true;
		return falg;
	}

	/**
	 * 当返回值为false时，将不能向左移动
	 * 
	 * @return
	 */
	public boolean judgeLeft() {
		boolean falg = true;
		if (pblockPoint.x <= 0) {
			falg = false;
		} else {
			int[] arr = SHAPE_BLOCKS[shapeNum][shapeNumChange];
			int[][] widthArr = getDoubleArr(arr);
			for (int y = pblockPoint.y, i = 0; i < getBlockHeight(true); y++, i++) {
				for (int x = pblockPoint.x, j = 0; j < getBlockWidth(true); x++, j++) {
					if (x >= panel.panelBlockArr.length
							|| y >= panel.panelBlockArr[0].length || x < 0
							|| y < 0) {
						return false;
					}
					if (x - 1 >= 0 && panel.panelBlockArr[x - 1][y] == 1
							&& widthArr[i][j] == 1) {
						falg = false;
					}
				}
			}
		}
		return falg;
	}

	/**
	 * 返回true代表能否移动
	 * 
	 * @return
	 */
	public boolean judgeRight() {
		boolean falg = true;
		if (pblockPoint.x + getBlockWidth(true) >= panel.panelBlockArr.length) {
			falg = false;
		} else {
			int[] arr = SHAPE_BLOCKS[shapeNum][shapeNumChange];
			int[][] widthArr = getDoubleArr(arr);
			for (int y = pblockPoint.y, i = 0; i < getBlockHeight(true); y++, i++) {
				for (int x = pblockPoint.x, j = 0; j < getBlockWidth(true); x++, j++) {
					// 如果超过边界，就不能够移动
					if (x >= panel.panelBlockArr.length
							|| y >= panel.panelBlockArr[0].length || x < 0
							|| y < 0) {
						return false;
					}

					if (x + 1 < panel.panelBlockArr.length
							&& panel.panelBlockArr[x + 1][y] == 1
							&& widthArr[i][j] == 1) {
						falg = false;
					}
				}
			}
		}
		return falg;
	}

	/**
	 * 返回true代表能够移动
	 * 
	 * @return
	 */
	public boolean judgeDown() {
		// 到达到底部的时候
		if (pblockPoint.y + getBlockHeight(true) >= MainJPanel.PANEL_BLOCK_HEIGHT) {
			return false;
		}
		// 如果该图形下面的一个数字是1，也就是下面也有图形，那也要终止移动
		int[] arr = SHAPE_BLOCKS[shapeNum][shapeNumChange];
		int[][] widthArr = getDoubleArr(arr);
		for (int y = pblockPoint.y, i = 0; i < getBlockHeight(true); y++, i++) {
			for (int x = pblockPoint.x, j = 0; j < getBlockWidth(true); x++, j++) {
				if (y + 1 < panel.panelBlockArr[0].length
						&& panel.panelBlockArr[x][y + 1] == 1
						&& widthArr[i][j] == 1) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean judgeChange() {
		boolean falg = false;
		/*
		 * 之前要判断是否可以变形，然后再变形
		 */
		int myCount = getBlockCount() + panel.getCount();
		shapeNumChange = changeShape();
		int[][] saveArray = new int[panel.panelBlockArr.length][panel.panelBlockArr[0].length];
		for (int i = 0; i < panel.panelBlockArr.length; i++) {
			for (int j = 0; j < panel.panelBlockArr[0].length; j++) {
				saveArray[i][j] = panel.panelBlockArr[i][j];
			}
		}
		compareArray(SHAPE_BLOCKS[shapeNum][shapeNumChange],
				panel.panelBlockArr, pblockPoint);
		if(myCount == panel.getCount()){
			falg = true;
		}
		panel.panelBlockArr = saveArray;
		return falg;
	}

	/**
	 * 得到当前图形所站的格子总数
	 */
	private int getBlockCount() {
		int count = 0;
		int[] array = SHAPE_BLOCKS[shapeNum][shapeNumChange];
		for (int i = 0; i < array.length; i++) {
			if (array[i] == 1) {
				count++;
			}
		}
		return count;
	}

	/**
	 * 返回true代表可以移动，或变换
	 * 
	 * @return
	 */
	public boolean judgeAll() {

		return judgeLeft() && judgeRight() && judgeDown();
	}

	/**
	 * 
	 * @param arr
	 *            当前形状的数组
	 * @param panelArr
	 *            面板格子的数组
	 * @param p
	 *            当前图形的左上角坐标
	 */
	public void compareArray(int[] arr, int[][] panelArr, Point p) {
		int[][] blockArr = getDoubleArr(arr);
		for (int y = p.y, i = 0; i < getBlockHeight(true); y++, i++) {
			for (int x = p.x, j = 0; j < getBlockWidth(true); x++, j++) {
				if(x >= MainJPanel.PANEL_BLOCK_WIDTH || y >= MainJPanel.PANEL_BLOCK_HEIGHT){
					return;
				}
				panelArr[x][y] = blockArr[i][j] | panelArr[x][y];
			}
		}
	}

	/**
	 * 线程启动
	 */
	public void threadStart() {
		blockThread.start();
	}

	/**
	 * 线程挂起
	 */
	@SuppressWarnings("deprecation")
	public void threadSuspend() {
		blockThread.suspend();
	}

	/**
	 * 线程重新启动
	 */
	@SuppressWarnings("deprecation")
	public void threadResume() {
		blockThread.resume();
	}

	/**
	 * 摧毁线程
	 */
	public void threadBreak() {
		this.breakThread = true;
	}

	/**
	 * 线程
	 * 
	 * @author Administrator
	 * 
	 */
	public class BlockThread extends Thread {

		public BlockThread() {

		}

		public void run() {
			while (true) {
				try {
					Thread.sleep(Rank.RANK_ARRAY[Rank.getRank() - 1][1]);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (!judgeDown()) {
					compareArray(SHAPE_BLOCKS[shapeNum][shapeNumChange],
							panel.panelBlockArr, pblockPoint);
					breakThread = true;
				}
				if (breakThread) {
					if (BlockShape.this.pBlock.getY() < 60) {
						BlockShape.this.panel.getMainGame().reStart(
								"Ha ha, you lose, whether or not to start?", 0);
						break;
					}
					panel.createBlock();
					break;
				}
				pblockPoint.y += 1;
				setPblockPoint(pblockPoint);
				panel.judgeLine();
				panel.repaint();
			}
		}
	}

	public MainJPanel getPanel() {
		return panel;
	}

}
