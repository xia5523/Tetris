package my_blocks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JOptionPane;

/**
 * ���������
 * 
 * @author ����
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
	 * ��������
	 */
	private Point pBlock = null;
	/**
	 * ��������
	 */
	public Point pblockPoint = null;
	/**
	 * ����Ŀ��
	 */
	public static final int BLOCK_WIDTH = 20;
	/**
	 * �߳��������ƶ�
	 */
	private BlockThread blockThread = null;
	/**
	 * ��������
	 */
	protected int shapeNum = 0;
	/**
	 * ������һ������
	 */
	public static int shapeNumNext = 0;
	/**
	 * ������͸ı�������
	 */
	int shapeNumChange = 0;
	/**
	 * �ж��Ƿ�ݻ��߳�
	 */
	private boolean breakThread = false;
	/**
	 * �����ʾ���ж��Ƿ��ǵ�һ��ͼ��
	 */
	public static boolean ifFirstBlock = true;

	/**
	 * �˷�ͼ�ε������Ŀ��(������)
	 */
	public static final int BLOCK_NUM = 4;

	/**
	 * ͼ�ε���ɫ
	 */
	public static final Color BLOCK_COLOR = Color.YELLOW;
	/**
	 * ��������ɫ
	 */
	public static final Color BLOCK_COLOR1 = Color.WHITE;
	/**
	 * ��һ��ͼ�ε�Ԥ��������ʱ����ɫ
	 */
	public static final Color BLOCK_COLOR_NEXT = Color.CYAN;

	/**
	 * ����������
	 */
	private MainJPanel panel = null;

	public Point getpBlock() {
		return pBlock;
	}

	/**
	 * ���ݸ�������õ���������
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
	 * ��������������ʱ��˳�㴴���߳�
	 * 
	 * @param g
	 */
	public BlockShape(Point pblockPoint, MainJPanel panel) {
		/**
		 * �������Ƿ��ǵ�һ��ͼ�Σ�Ԥ������һ��ͼ�ε�����
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
	 * ���ݵ�ǰ����״�õ���ͼ�εĸ߶�
	 * 
	 * @param falg
	 *            falg == true��ʾ�жϵ�ǰ��״�ĸ߶ȣ�falg==false��ʾ�жϵõ���һ����״�ĸ߶�
	 * @return �߶�
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
	 * ����
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
	 * ���Լ� lineNum������ lieNum������
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
	 * �õ���һ����״���ֵķ���
	 * 
	 * @return ����
	 */
	protected int changeShape() {
		int numChange = shapeNumChange + 1;
		if (numChange == 4) {
			numChange = 0;
		}
		return numChange;
	}

	/**
	 * ����ǰ��16λ��һά�����Ϊ4 * 4 �Ķ�ά����
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
	 * �ж��Ƿ��ܹ��ƶ�
	 * 
	 * @return
	 */
	public boolean isMove() {
		boolean falg = true;
		return falg;
	}

	/**
	 * ������ֵΪfalseʱ�������������ƶ�
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
	 * ����true�����ܷ��ƶ�
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
					// ��������߽磬�Ͳ��ܹ��ƶ�
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
	 * ����true�����ܹ��ƶ�
	 * 
	 * @return
	 */
	public boolean judgeDown() {
		// ���ﵽ�ײ���ʱ��
		if (pblockPoint.y + getBlockHeight(true) >= MainJPanel.PANEL_BLOCK_HEIGHT) {
			return false;
		}
		// �����ͼ�������һ��������1��Ҳ��������Ҳ��ͼ�Σ���ҲҪ��ֹ�ƶ�
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
		 * ֮ǰҪ�ж��Ƿ���Ա��Σ�Ȼ���ٱ���
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
	 * �õ���ǰͼ����վ�ĸ�������
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
	 * ����true��������ƶ�����任
	 * 
	 * @return
	 */
	public boolean judgeAll() {

		return judgeLeft() && judgeRight() && judgeDown();
	}

	/**
	 * 
	 * @param arr
	 *            ��ǰ��״������
	 * @param panelArr
	 *            �����ӵ�����
	 * @param p
	 *            ��ǰͼ�ε����Ͻ�����
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
	 * �߳�����
	 */
	public void threadStart() {
		blockThread.start();
	}

	/**
	 * �̹߳���
	 */
	@SuppressWarnings("deprecation")
	public void threadSuspend() {
		blockThread.suspend();
	}

	/**
	 * �߳���������
	 */
	@SuppressWarnings("deprecation")
	public void threadResume() {
		blockThread.resume();
	}

	/**
	 * �ݻ��߳�
	 */
	public void threadBreak() {
		this.breakThread = true;
	}

	/**
	 * �߳�
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
