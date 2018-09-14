package my_blocks;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
/**
 * 主面板
 * @author 夏忍
 *
 */
@SuppressWarnings("serial")
public class MainJPanel extends JPanel {

	/**
	 * 面板中活动的图形
	 */
	protected BlockShape block = null;

	/**
	 * 边界设置
	 */
	private Border border = null;
	/**
	 * 二维数组，用来模拟免得中的二维格子
	 */
	public int[][] panelBlockArr = null;

	private MainGame mainGame;
	/**
	 * 面板的长和宽
	 */
	public static final int PANEL_BLOCK_WIDTH = 16;
	public static final int PANEL_BLOCK_HEIGHT = 25;

	/**
	 * 非静态代码快，在构造函数之前执行的
	 */
	{
		border = new EtchedBorder(EtchedBorder.RAISED, Color.white, new Color(
				148, 145, 140));
		// 面板宽、长度量分别是16：25
		panelBlockArr = new int[PANEL_BLOCK_WIDTH][PANEL_BLOCK_HEIGHT];
	}

	/**
	 * 构造方法
	 */
	public MainJPanel(MainGame mainGame) {
		// 长宽比例7:10
		this.setSize(BlockShape.BLOCK_WIDTH * panelBlockArr.length,
				BlockShape.BLOCK_WIDTH * panelBlockArr[0].length);
		this.setBorder(border);
		this.addKeyListener(new KeyListenImpl(this));
		this.mainGame = mainGame;
	}

	/**
	 * 创建图形、并使其驱动
	 */
	public void createBlock() {
		block = new BlockShape(new Point(3,3), this);
		block.threadStart();
	}
	/**
	 * 程序挂起
	 */
	public void threadSuspend(){
		KeyListenImpl.isKeyEventAble = false;
		block.threadSuspend();
	}
	/**
	 * 程序重新启动
	 */
	public void threadResume(){
		KeyListenImpl.isKeyEventAble = true;
		block.threadResume();
	}

	/**
	 * 根据该类中的二维数组，绘制图像
	 * 
	 * @param g
	 */
	public void drawArray(Graphics g) {
		g.setColor(BlockShape.BLOCK_COLOR1);
		for (int i = 0; i < panelBlockArr.length; i++) {
			for (int j = 0; j < panelBlockArr[0].length; j++) {
				if (panelBlockArr[i][j] == 1) {
					g.fill3DRect(i * BlockShape.BLOCK_WIDTH, j
							* BlockShape.BLOCK_WIDTH, BlockShape.BLOCK_WIDTH,
							BlockShape.BLOCK_WIDTH, true);
				}
			}
		}
	}

	/**
	 * 绘制下一个图形的样子
	 * 
	 * @param g
	 */
	public void drawNextBlock(Graphics g) {
		g.setColor(BlockShape.BLOCK_COLOR_NEXT);
		for (int i = 0; i < BlockShape.SHAPE_BLOCKS[0][0].length; i++) {
			if (BlockShape.SHAPE_BLOCKS[BlockShape.shapeNumNext][0][i] == 1) {
				int lineNum = i / 4;
				int lieNum = i % 4;
				g.fill3DRect(BlockShape.BLOCK_WIDTH * (PANEL_BLOCK_WIDTH - 3)
						+ (BlockShape.BLOCK_WIDTH / 2) * lieNum,
						BlockShape.BLOCK_WIDTH * 2
								+ (BlockShape.BLOCK_WIDTH / 2) * lineNum,
						BlockShape.BLOCK_WIDTH / 2, BlockShape.BLOCK_WIDTH / 2,
						true);
			}
		}
	}

	/**
	 * 画字符
	 * @param g
	 */
	public void drawString(Graphics g) {
		g.setFont(new Font("楷体", 0, 20));
		g.drawString("next", BlockShape.BLOCK_WIDTH * (PANEL_BLOCK_WIDTH - 3),
				BlockShape.BLOCK_WIDTH);
		g.drawString("分 数", BlockShape.BLOCK_WIDTH * (PANEL_BLOCK_WIDTH - 9),
				BlockShape.BLOCK_WIDTH);
		g.drawString("" + Rank.scores, BlockShape.BLOCK_WIDTH * (PANEL_BLOCK_WIDTH - 9) + 10,
				BlockShape.BLOCK_WIDTH * 2);
		g.drawString("等级", BlockShape.BLOCK_WIDTH,
				BlockShape.BLOCK_WIDTH);
		g.drawString("" + Rank.getRank(), BlockShape.BLOCK_WIDTH,
				BlockShape.BLOCK_WIDTH * 2);
	}

	/**
	 * 绘制图形
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (MainGame.isStartGame) {
			block.drawBlock(g);
			drawArray(g);
			drawNextBlock(g);
			drawString(g);
		}
	}

	/**
	 * 判断某一行是否全都占满格子，如果全都占满，则将其销毁，上面的元素向下移动一格
	 */
	public void judgeLine() {
		for (int i = 0; i < panelBlockArr[0].length; i++) {
			for (int j = 0; j < panelBlockArr.length; j++) {
				if (panelBlockArr[j][i] == 0) {
					break;
				} else if (j == panelBlockArr.length - 1) {//
					// 如果直到最后一位，还没结束循环也就是，一直都是1的话，就消除这一行
					for (int k = 0; k < panelBlockArr.length; k++) {
						panelBlockArr[k][i] = 0;
					}
					// 这一行上面的全都向下移动一格
					for (int m = i; m > 0; m--) {
						for (int n = 0; n < panelBlockArr.length; n++) {
							panelBlockArr[n][m] = panelBlockArr[n][m - 1];
						}
					}
					/*
					 * 加100分
					 */
					Rank.scores += 100;
				}
			}
		}
	}

	public MainGame getMainGame() {
		return mainGame;
	}
	
	/**
	 * 得到当前面板所站的格子总数
	 * @return
	 */
	public int getCount() {
		int count = 0;
		for (int i = 0; i < panelBlockArr.length; i++) {
			for (int j = 0; j < panelBlockArr[0].length; j++) {
				if (panelBlockArr[i][j] == 1) {
					count++;
				}
			}
		}
		return count;
	}
	
}
