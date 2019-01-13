package com.huowolf.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import com.huowolf.listener.SnakeListener;
import com.huowolf.util.Global;


public class Snake {

	//������ĳ���
	public static final int UP = 1;
	public static final int DOWN = -1;
	public static final int LEFT = 2;
	public static final int RIGHT = -2;
	
	//��������
	private Set<SnakeListener> listeners = new HashSet<SnakeListener>();
	//�洢�ߵ�����ṹ
	private LinkedList<Point> body = new LinkedList<Point>();
	
	private boolean life;					//�Ƿ����
	private boolean pause;					//�Ƿ���ͣ��Ϸ
	private int oldDirection,newDirection;	//�£��ɷ�������루����һ���ƶ�ʱ���ڵ���Ч����
	private Point oldTail;					//�ɵ�β�ͣ��ڳԵ�ʳ���ʱ��ʹ�ã�
	private int foodCount = 0;				//�Ե�ʳ�������
	private Color headColor;
	private Color bodyColor;
	private int sleepTime;
	
	public boolean isLife() {
			return life;
	}
	public int getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}


	public void setHeadColor(Color headColor) {
		this.headColor = headColor;
	}


	public void setBodyColor(Color bodyColor) {
		this.bodyColor = bodyColor;
	}


	public void init() {
		int x = Global.WIDTH/2;
		int y = Global.HEIGHT/2;
		for(int i=0;i<3;i++) {
			body.addLast(new Point(x--,y));
		}
		
		oldDirection = newDirection = RIGHT;
		foodCount = 0;	
		life = true;
		pause = false;
		
		if(sleepTime==0) {
			sleepTime = 300;
		}
	}
	
	
	//����ߵĽڵ�ķ�����������ʼһ���µ���Ϸ��
	public void clear() {
		body.clear();
	}
	

	public void setLife(boolean life) {
		this.life = life;
	}


	public boolean getPause() {
		return pause;
	}	
	
	public void setPause(boolean pause) {
		this.pause = pause;
	}
	
	
	//�����ı�pause������״̬
	public void changePause() {
		pause = !pause;
	}

	
	//�������ķ���
	public void die() {
		life = false;
	}
	
	
	//���ƶ��ķ���
	public void move() {
		if(!(oldDirection + newDirection==0)) {
			oldDirection = newDirection ;
		}
		
		//ȥβ
		oldTail = body.removeLast();
		int x = body.getFirst().x;
		int y = body.getFirst().y;
		
		switch(oldDirection) {
			case UP:
				y--;
				if(y<0) {
					y = Global.HEIGHT -1 ;
				}
				break;
			case DOWN:
				y++;
				if(y >= Global.HEIGHT) {
					y = 0;
				}
				break;
			case LEFT:
				x--;
				if(x<0) {
					x = Global.WIDTH-1;
				}
				break;
			case RIGHT:
				x++;
				if(x >= Global.WIDTH) {
					x = 0;
				}
				break;
		}
		
		Point newHead = new Point(x, y);
		//��ͷ
		body.addFirst(newHead);
	}
	
	
	//�ı䷽��
	public void changeDirection(int direction) {
			newDirection = direction;		
	}
	
	
	//��ʳ��
	public void eatFood() {		
		body.addLast(oldTail);
		foodCount++;
	}
	
	
	//��ȡ�Ե���ʳ������
	public int getFoodCount() {
		return foodCount;
	}
	
	
	//���Ƿ�Ե����Լ�������
	public boolean isEatBody() {
		for(int i=1;i<body.size();i++) {
			if(body.get(i).equals(this.getHead())) 
				return true;
		}
		
		return false;	
	}
	
	
	//��ȡ������ͷ�Ľڵ�
	public Point getHead() {
		return body.getFirst();
	}
	
	
	//��ʾ�Լ�
	public void drawMe(Graphics g) {
		if(bodyColor==null) {
			g.setColor(new Color(0x3333FF));
		} else {
			g.setColor(bodyColor);
		}
		
		for(Point p : body) {
			
			g.fillRoundRect(p.x*Global.CELL_SIZE, p.y*Global.CELL_SIZE,
					Global.CELL_SIZE, Global.CELL_SIZE, 15,12 );
		}
		drawHead(g);
	}
	
	//����ͷ
	public void drawHead(Graphics g) {
		if(headColor==null)
			g.setColor(Color.YELLOW);
		else {
			g.setColor(headColor);
		}
		
		g.fillRoundRect(getHead().x * Global.CELL_SIZE, getHead().y* Global.CELL_SIZE, 
				Global.CELL_SIZE, Global.CELL_SIZE, 15,12);
	}
	
	
	//�������ƶ����߳��ڲ���
	private class SnakeDriver implements Runnable {
		public void run() {
			while(life) {
				if(pause==false) {
					move();			
					for(SnakeListener l : listeners)
						l.snakeMoved(Snake.this);
				}
					
				try {	
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	//�����̵߳ķ���
	public void begin() {
		new Thread(new SnakeDriver()).start();
		
	}

	
	//��Ӽ�����
	public void addSnakeListener(SnakeListener l) {
		if(l != null) {
			listeners.add(l);
		}
	}

	//����
	public void speedUp() {
		if(sleepTime>50) {
			sleepTime-=20;
		}
	}
	
	//����
	public void speedDown() {
		if(sleepTime<700) {
			sleepTime+=20;
		}
	}

}
