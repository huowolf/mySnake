package com.huowolf.view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GameMenu extends JMenuBar{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenu colorMenu,speeceMenu,mapMenu,abortMenu;
	private JMenuItem item1,item2,item3,item4;
	private JMenuItem spItem1,spItem2,spItem3,spItem4;
	private JMenuItem mapItem1,mapItem2,mapItem3;
	private JMenuItem abItem;
	
	
	public JMenu getColorMenu() {
		return colorMenu;
	}



	public JMenu getSpeeceMenu() {
		return speeceMenu;
	}



	public JMenu getMapMenu() {
		return mapMenu;
	}



	public JMenu getAbortMenu() {
		return abortMenu;
	}



	public JMenuItem getItem1() {
		return item1;
	}



	public JMenuItem getItem2() {
		return item2;
	}



	public JMenuItem getItem3() {
		return item3;
	}



	public JMenuItem getItem4() {
		return item4;
	}



	public JMenuItem getSpItem1() {
		return spItem1;
	}



	public JMenuItem getSpItem2() {
		return spItem2;
	}



	public JMenuItem getSpItem3() {
		return spItem3;
	}



	public JMenuItem getSpItem4() {
		return spItem4;
	}



	public JMenuItem getMapItem1() {
		return mapItem1;
	}



	public JMenuItem getMapItem2() {
		return mapItem2;
	}



	public JMenuItem getMapItem3() {
		return mapItem3;
	}



	public JMenuItem getAbItem() {
		return abItem;
	}



	public GameMenu() {
		
		colorMenu = new JMenu("������ɫ");
		add(colorMenu);
		
		speeceMenu = new JMenu("�����ٶ�");
		add(speeceMenu);
		
		mapMenu = new JMenu("��ͼ");
		add(mapMenu);
		
		abortMenu = new JMenu("����");
		add(abortMenu);
		
		item1 = new JMenuItem("������ɫ");
		item2 = new JMenuItem("ʳ����ɫ");
		item3 = new JMenuItem("��ͷ��ɫ");
		item4 = new JMenuItem("������ɫ");
		
		colorMenu.add(item1);
		colorMenu.add(item2);
		colorMenu.add(item3);
		colorMenu.add(item3);
		colorMenu.add(item4);
		
		spItem1 = new JMenuItem("ɢ��");
		spItem2 = new JMenuItem("����");
		spItem3 = new JMenuItem("����");
		spItem4 = new JMenuItem("���");
		
		speeceMenu.add(spItem1);
		speeceMenu.add(spItem2);
		speeceMenu.add(spItem3);
		speeceMenu.add(spItem4);
		
		mapItem1 = new JMenuItem("��ͼһ");
		mapItem2 = new JMenuItem("��ͼ��");
		mapItem3 = new JMenuItem("��ͼ��");
		
		mapMenu.add(mapItem1);
		mapMenu.add(mapItem2);
		mapMenu.add(mapItem3);
		
		//String s = "huowolf����http://blog.csdn.net/huolang_vip";
		abItem = new JMenuItem("ʹ��˵��");
		abortMenu.add(abItem);
	}
	
}
