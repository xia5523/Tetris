package my_blocks;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Stack;

/**
 * 键盘事件类
 * 
 * @author 夏忍
 * 
 */
public class KeyListenImpl extends KeyAdapter {
	/**
	 * 键盘事件是否启用
	 */
	public static boolean isKeyEventAble = false;

	MainJPanel blockPanel = null;
	Stack<BlockShape> stack = new Stack<BlockShape>();

	public KeyListenImpl(MainJPanel blockPanel) {
		this.blockPanel = blockPanel;
	}

	/**
	 * 按下键盘事件
	 */
	public void keyPressed(KeyEvent e) {
		if (isKeyEventAble) {
			if (e.getKeyCode() == KeyEvent.VK_W
					|| e.getKeyCode() == KeyEvent.VK_UP) {
				/**
				 * 如果还是不能够变形，则将其变化三次，将其还原
				 */
				if (!blockPanel.block.judgeChange()) {
					blockPanel.block.shapeNumChange = blockPanel.block
							.changeShape();
					blockPanel.block.shapeNumChange = blockPanel.block
							.changeShape();
					blockPanel.block.shapeNumChange = blockPanel.block
							.changeShape();
				}
			} else if (e.getKeyCode() == KeyEvent.VK_S
					|| e.getKeyCode() == KeyEvent.VK_DOWN) {
				/**
				 * 如果能够向下移动，就向下移动一下
				 */
				if (blockPanel.block.judgeDown()) {
					blockPanel.block.pblockPoint.y += 1;
				} else if (blockPanel.block.getpBlock().getY() < 60) {
					blockPanel.getMainGame().reStart(
							"Ha ha, you lose, whether or not to start?", 0);
				}
			} else if (e.getKeyCode() == KeyEvent.VK_A
					|| e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (blockPanel.block.judgeLeft()) {
					blockPanel.block.pblockPoint.x -= 1;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_D
					|| e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if (blockPanel.block.judgeRight()) {
					blockPanel.block.pblockPoint.x += 1;
				}
			}
			blockPanel.block.setPblockPoint(blockPanel.block.pblockPoint);
			blockPanel.repaint();
		}
		if (e.getKeyCode() == 27) {// Esc
			blockPanel.getMainGame().exit();
		} else if (e.getKeyCode() == KeyEvent.VK_R) {
			blockPanel.getMainGame().reStart(
					"Are you sure to restart the game ?", 1);
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			blockPanel.getMainGame().operState(
					blockPanel.getMainGame().getLblStartGame().isEnabled(),
					blockPanel.getMainGame().getLblThread().isEnabled());
		}
	}
}
