package my_blocks;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * 程序入口，窗体主类
 * 
 * @author 夏忍
 * 
 */
@SuppressWarnings("serial")
public class MainGame extends JFrame {

	public static boolean isStartGame = false;

	/**
	 * 界面设置
	 */
	// 面板设置
	public MainJPanel panelMain = new MainJPanel(this);

	public static MainGame mainGame;

	// 开始游戏时用时
	private long startTime = 0;

	// 菜单设置
	private JMenuBar menubar = new JMenuBar();
	private JMenu jMenuFile = new javax.swing.JMenu();
	private JMenuItem jMenuFileItemRestart = new JMenuItem();
	private JMenuItem jMenuFileItemHelp = new JMenuItem();
	private JMenuItem jMenuFileItemAbout = new JMenuItem();
	private JMenuItem jMenuFileItemExit = new JMenuItem();

	private JLabel lblStartGame = new JLabel("开始游戏");
	private JLabel lblThread = new JLabel("暂停");
	private JLabel lblTitle = new JLabel("欢迎来到俄罗斯方块游戏");
	private JLabel lblCurrent = new JLabel("00:00");
	private JLabel lblUseTime = new JLabel("0 s");

	public void initialize() {
		jMenuFile.add(jMenuFileItemRestart);
		jMenuFile.add(jMenuFileItemHelp);
		jMenuFile.add(jMenuFileItemAbout);
		jMenuFile.add(jMenuFileItemExit);
		menubar.add(jMenuFile);

		this.add(lblStartGame);
		this.add(lblThread);
		this.add(lblTitle);
		this.add(lblCurrent);
		this.add(lblUseTime);

		this.setJMenuBar(menubar);
		this.add(panelMain);

	}

	// 窗体初始化
	public MainGame() {
		initialize();
		jMenuFileItemRestart.setText("Restart (R)");
		jMenuFileItemHelp.setText("HELP");
		jMenuFileItemAbout.setText("About");
		jMenuFileItemExit.setText("Exit (Esc)");
		jMenuFile.setText("File");

		lblStartGame.setFont(new Font("楷体", 0, 20));
		lblStartGame.setBounds(340, 100, 200, 50);
		lblThread.setFont(new Font("楷体", 0, 20));
		lblThread.setBounds(340, 200, 100, 50);
		lblThread.setEnabled(false);
		lblTitle.setFont(new Font("楷体", Font.BOLD, 20));
		lblTitle.setBounds(50, 5, 400, 50);
		lblCurrent.setBounds(340, 300, 100, 50);
		lblUseTime.setBounds(340, 400, 200, 50);

		panelMain.setLocation(10, 70);

		panelMain.setLayout(null);
		panelMain.setBackground(Color.BLACK);

		this.setLayout(null);
		this.setBounds(300, 10, 450, 650);
		setLocationRelativeTo(getOwner());
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelMain.setFocusable(true);

		jMenuFileItemRestart.addActionListener((new ActionListen()));
		jMenuFileItemHelp.addActionListener((new ActionListen()));
		jMenuFileItemAbout.addActionListener(new ActionListen());
		jMenuFileItemExit.addActionListener(new ActionListen());
		lblStartGame.addMouseListener(new MouseEventListener());
		lblThread.addMouseListener(new MouseEventListener());

	}

	public static void main(String[] args) {
		mainGame = new MainGame();
	}

	/**
	 * 鼠标事件
	 * 
	 * @author Administrator
	 * 
	 */
	private class MouseEventListener extends MouseAdapter {

		@Override
		public void mouseEntered(MouseEvent e) {
			super.mouseEntered(e);
			if (e.getSource() instanceof JLabel) {
				JLabel label = (JLabel) e.getSource();
				label.setFont(new Font("楷体", Font.ITALIC, 20));
			}

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			if (e.getSource() == lblStartGame && lblStartGame.isEnabled()) {
				operState(lblStartGame.isEnabled(),lblThread.isEnabled());
				
			} else if (e.getSource() == lblThread && lblThread.isEnabled()) {
				operState(false,lblThread.isEnabled());
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {

			super.mouseExited(e);
			if (e.getSource() instanceof JLabel) {
				JLabel label = (JLabel) e.getSource();
				label.setFont(new Font("楷体", 0, 20));
			}
		}

	}
	/**
	 * 根据状态操作
	 * @param operGame
	 * @param operThread
	 */
	public void operState(boolean operGame,boolean operThread){
		if(operGame){//如果游戏按钮是开着的
			startTime = System.currentTimeMillis();
			new TimeRun().start();
			isStartGame = true;
			KeyListenImpl.isKeyEventAble = true;
			panelMain.createBlock();
			lblThread.setEnabled(true);
			lblStartGame.setEnabled(false);
		}else if(operThread){
			if ("暂停".equals(lblThread.getText())) {
				panelMain.threadSuspend();
				lblThread.setText("继续");
			} else {
				panelMain.threadResume();
				lblThread.setText("暂停");
			}
		}
	}

	@SuppressWarnings("unused")
	private class TimeRun extends Thread {
		public void run() {
			while (true) {
				Calendar rightNow = Calendar.getInstance();
				Date da = rightNow.getTime();
				SimpleDateFormat myFmt = new SimpleDateFormat("HH:mm:ss");
				lblCurrent.setText(myFmt.format(da));
				long sub = (System.currentTimeMillis() - startTime) / 1000;
				if (sub > 60) {
					lblUseTime.setText(sub / 60 + " min " + sub % 60 + " s");
				} else {
					lblUseTime.setText(sub + " s");
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		public String getNowTime() {
			return "";
		}

	}

	private class ActionListen implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jMenuFileItemHelp) {
				JOptionPane.showMessageDialog(null, ""
						+ "上、下、左、右 分别是 W|UP、S|DOWN、A|LEFT、D|RIGHT",
						"About Author", JOptionPane.DEFAULT_OPTION);
			} else if (e.getSource() == jMenuFileItemAbout) {
				JOptionPane
						.showMessageDialog(
								null,
								""
										+ "\r\n"
										+ "           The game is made by"
										+ "\r\n"
										+ "                                              --  XiaRen"
										+ "\r\n"
										+ "                                         On July 28, 2012"
										+ "", "About Author",
								JOptionPane.DEFAULT_OPTION);
			} else if (e.getSource() == jMenuFileItemExit) {
				exit();
			} else if (e.getSource() == jMenuFileItemRestart) {
				reStart("Are you sure to restart the game ?",1);
			}
		}
	}

	public void exit() {
		int result = JOptionPane.showConfirmDialog(null,
				"Are you sure to leave the game ?", "EXIT GAME", 0);
		if (result == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public void reStart(String message,int key) {
		int result = JOptionPane.showConfirmDialog(null, message,
				"RESTART GAME", 0);
		if (result == JOptionPane.YES_OPTION) {
			BlockShape.shapeNumNext = 0;
			BlockShape.ifFirstBlock = true;
			KeyListenImpl.isKeyEventAble = false;
			Rank.scores = 0;
			Rank.rank = 1;
			MainGame.isStartGame = false;
			mainGame.dispose();
			mainGame = new MainGame();
		}else if(key == 0){
			System.exit(0);
		}
		
	}

	public JLabel getLblStartGame() {
		return lblStartGame;
	}

	public JLabel getLblThread() {
		return lblThread;
	}

}
