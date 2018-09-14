package my_blocks;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
/**
 * �����
 * @author ����
 *
 */
@SuppressWarnings("serial")
public class MainJPanel extends JPanel {

	/**
	 * ����л��ͼ��
	 */
	protected BlockShape block = null;

	/**
	 * �߽�����
	 */
	private Border border = null;
	/**
	 * ��ά���飬����ģ������еĶ�ά����
	 */
	public int[][] panelBlockArr = null;

	private MainGame mainGame;
	/**
	 * ���ĳ��Ϳ�
	 */
	public static final int PANEL_BLOCK_WIDTH = 16;
	public static final int PANEL_BLOCK_HEIGHT = 25;

	/**
	 * �Ǿ�̬����죬�ڹ��캯��֮ǰִ�е�
	 */
	{
		border = new EtchedBorder(EtchedBorder.RAISED, Color.white, new Color(
				148, 145, 140));
		// �����������ֱ���16��25
		panelBlockArr = new int[PANEL_BLOCK_WIDTH][PANEL_BLOCK_HEIGHT];
	}

	/**
	 * ���췽��
	 */
	public MainJPanel(MainGame mainGame) {
		// �������7:10
		this.setSize(BlockShape.BLOCK_WIDTH * panelBlockArr.length,
				BlockShape.BLOCK_WIDTH * panelBlockArr[0].length);
		this.setBorder(border);
		this.addKeyListener(new KeyListenImpl(this));
		this.mainGame = mainGame;
	}

	/**
	 * ����ͼ�Ρ���ʹ������
	 */
	public void createBlock() {
		block = new BlockShape(new Point(3,3), this);
		block.threadStart();
	}
	/**
	 * �������
	 */
	public void threadSuspend(){
		KeyListenImpl.isKeyEventAble = false;
		block.threadSuspend();
	}
	/**
	 * ������������
	 */
	public void threadResume(){
		KeyListenImpl.isKeyEventAble = true;
		block.threadResume();
	}

	/**
	 * ���ݸ����еĶ�ά���飬����ͼ��
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
	 * ������һ��ͼ�ε�����
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
	 * ���ַ�
	 * @param g
	 */
	public void drawString(Graphics g) {
		g.setFont(new Font("����", 0, 20));
		g.drawString("next", BlockShape.BLOCK_WIDTH * (PANEL_BLOCK_WIDTH - 3),
				BlockShape.BLOCK_WIDTH);
		g.drawString("�� ��", BlockShape.BLOCK_WIDTH * (PANEL_BLOCK_WIDTH - 9),
				BlockShape.BLOCK_WIDTH);
		g.drawString("" + Rank.scores, BlockShape.BLOCK_WIDTH * (PANEL_BLOCK_WIDTH - 9) + 10,
				BlockShape.BLOCK_WIDTH * 2);
		g.drawString("�ȼ�", BlockShape.BLOCK_WIDTH,
				BlockShape.BLOCK_WIDTH);
		g.drawString("" + Rank.getRank(), BlockShape.BLOCK_WIDTH,
				BlockShape.BLOCK_WIDTH * 2);
	}

	/**
	 * ����ͼ��
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
	 * �ж�ĳһ���Ƿ�ȫ��ռ�����ӣ����ȫ��ռ�����������٣������Ԫ�������ƶ�һ��
	 */
	public void judgeLine() {
		for (int i = 0; i < panelBlockArr[0].length; i++) {
			for (int j = 0; j < panelBlockArr.length; j++) {
				if (panelBlockArr[j][i] == 0) {
					break;
				} else if (j == panelBlockArr.length - 1) {//
					// ���ֱ�����һλ����û����ѭ��Ҳ���ǣ�һֱ����1�Ļ�����������һ��
					for (int k = 0; k < panelBlockArr.length; k++) {
						panelBlockArr[k][i] = 0;
					}
					// ��һ�������ȫ�������ƶ�һ��
					for (int m = i; m > 0; m--) {
						for (int n = 0; n < panelBlockArr.length; n++) {
							panelBlockArr[n][m] = panelBlockArr[n][m - 1];
						}
					}
					/*
					 * ��100��
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
	 * �õ���ǰ�����վ�ĸ�������
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
