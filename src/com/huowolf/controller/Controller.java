package com.huowolf.controller;

//�����������������ı仯
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

import com.huowolf.entities.Food;
import com.huowolf.entities.Ground;
import com.huowolf.entities.Snake;
import com.huowolf.listener.SnakeListener;
import com.huowolf.view.BottonPanel;
import com.huowolf.view.GameMenu;
import com.huowolf.view.GamePanel;

public class Controller extends KeyAdapter implements SnakeListener{
	private Snake snake;
	private Food food;
	private Ground ground;
	private GamePanel gamePanel;
	private GameMenu gameMenu;
	private BottonPanel bottonPanel;
	
	
	public Controller(Snake snake, Food food, Ground ground,GamePanel gamePanel,GameMenu gameMenu,BottonPanel bottonPanel) {
		this.snake = snake;
		this.food = food;
		this.ground = ground;
		this.gamePanel = gamePanel;
		this.gameMenu = gameMenu;
		this.bottonPanel = bottonPanel;
		
		init();
	}
	
	
	//�������ĳ�ʼ������Ҫ��ʼ���Կؼ��ļ���
	public void init() {
		bottonPanel.getStartButton().addActionListener(new startHandler());
		bottonPanel.getPauseButton().addActionListener(new pauseHandler());
		bottonPanel.getEndButton().addActionListener(new endHandler());
		
		gameMenu.getItem1().addActionListener(new Item1Handler());
		gameMenu.getItem2().addActionListener(new Item2Handler());
		gameMenu.getItem3().addActionListener(new Item3Handler());
		gameMenu.getItem4().addActionListener(new Item4Handler());
		
		gameMenu.getSpItem1().addActionListener(new spItem1Handler());
		gameMenu.getSpItem2().addActionListener(new spItem2Handler());
		gameMenu.getSpItem3().addActionListener(new spItem3Handler());
		gameMenu.getSpItem4().addActionListener(new spItem4Handler());
		
		gameMenu.getMapItem1().addActionListener(new mapItem1Handler());
		gameMenu.getMapItem2().addActionListener(new mapItem2Handler());
		gameMenu.getMapItem3().addActionListener(new mapItem3Handler());
		
		gameMenu.getAbItem().addActionListener(new abortItemHandler());
		
		bottonPanel.getStartButton().setEnabled(true);
	}

	//��ȡ������
	public Snake getSnake() {
		return snake;
	}
	
	public Ground getGround() {
		return ground;
	}
	
	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public GameMenu getGameMenu() {
		return gameMenu;
	}
	
	public BottonPanel getBottonPanel() {
		return bottonPanel;
	}
	
	
	//���̰����ļ���
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:			
				snake.changeDirection(Snake.UP);
				break;
			case KeyEvent.VK_DOWN:				
				snake.changeDirection(Snake.DOWN);
				break;
			case KeyEvent.VK_LEFT:
				snake.changeDirection(Snake.LEFT);
				break;
			case KeyEvent.VK_RIGHT:
				snake.changeDirection(Snake.RIGHT);
				break;
			case KeyEvent.VK_W:
				snake.speedUp();
				break;
			case KeyEvent.VK_S:
				snake.speedDown();
				break;
			default:
				break;
		}
	}

	
	//ʵ�����ƶ��Ľӿڣ��������ƶ����̷����ĸ�������
	@Override
	public void snakeMoved(Snake snake) {
		//ÿ�ƶ�һ�Σ��͸���һ�����
		gamePanel.display(snake, food, ground);			
		
		if(food.isFoodEated(snake)) {
			snake.eatFood();
			food.newFood(ground.getPoint());
			
			//���µ÷���ʾ���	
			bottonPanel.repaint();
			setScore();		
		}
		
		if(ground.isGroundEated(snake)) {
			snake.die();
			bottonPanel.getStartButton().setEnabled(true);
		}
		
		if(snake.isEatBody()) {
			snake.die();
			bottonPanel.getStartButton().setEnabled(true);
		}
		
		
	}
	
	
	//
	public void setScore() {
		int score = snake.getFoodCount() ;
		bottonPanel.setScore(score);
	}
	

	
	// ��ʼһ������Ϸ	
	public void newGame() {
		ground.clear();
		
		switch (ground.getMapType()) {
			case 1:
				ground.generateRocks1();
				break;
			case 2:
				ground.generateRocks2();
				break;
			case 3:
				ground.generateRocks3();
				break;
		}
		
		food.newFood(ground.getPoint());	
		bottonPanel.setScore(0);		
		
		bottonPanel.repaint();
	}

	

	
	//��ʼ��Ϸ��ť�ļ���
	class startHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {	
			gamePanel.requestFocus(true);	//!!ʹ��Ϸ�������ý���
			snake.clear();
			snake.init();		
			snake.begin();
			newGame();
			bottonPanel.getStartButton().setEnabled(false);
		}
		
	}
	
	
	//��ͣ��ť�ļ���
	class pauseHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			gamePanel.requestFocus(true);
			snake.changePause();

			if(e.getActionCommand()=="��ͣ��Ϸ")
				bottonPanel.getPauseButton().setText("������Ϸ");
			else {
				bottonPanel.getPauseButton().setText("��ͣ��Ϸ");
			}
		}
		
	}
	

	//������Ϸ��ť�ļ���
	class endHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			snake.die();
			bottonPanel.getStartButton().setEnabled(true);
		}
		
	}
	
	
	//�˵�������ť�ļ���
	class Item1Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Color color = JColorChooser.showDialog(gamePanel, "��ѡ����ɫ", Color.BLACK);	
			gamePanel.backgroundColor = color;
			
		}
		
	}
	
	
	class Item2Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Color color = JColorChooser.showDialog(gamePanel, "��ѡ����ɫ", Color.BLACK);	
			food.setFoodColor(color);
		}
		
	}
	
	
	class Item3Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Color color = JColorChooser.showDialog(gamePanel, "��ѡ����ɫ", Color.BLACK);	
			snake.setHeadColor(color);
		}
		
	}
	
	class Item4Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Color color = JColorChooser.showDialog(gamePanel, "��ѡ����ɫ", Color.BLACK);	
			snake.setBodyColor(color);
		}
		
	}
	
	class spItem1Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			snake.setSleepTime(600);
			
		}
		
	}
	
	class spItem2Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			snake.setSleepTime(350);
			
		}	
	}
	
	
	class spItem3Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			snake.setSleepTime(150);
		}	
	}
	
	class spItem4Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			snake.setSleepTime(80);
			
		}	
	}
	
	
	class mapItem1Handler	implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			ground.setMapType(1);
			
		}
		
	}
	
	class mapItem2Handler	implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			ground.setMapType(2);
			
		}
		
	}
	
	class mapItem3Handler	implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			ground.setMapType(3);
			
		}
		
	}
	
	class abortItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			StringBuffer sb= new StringBuffer();
			sb.append("��������Ʒ���\n");
			sb.append("w����s���ֱ����ʹ����١�����\n");
			sb.append("huowolf��������\n");
			sb.append("���ͣ�blog.csdn.net/huolang_vip\n");
			String message = sb.toString();
			JOptionPane.showMessageDialog(null, message, "ʹ��˵��",JOptionPane.INFORMATION_MESSAGE);
			
		}
		
	}
	
}
