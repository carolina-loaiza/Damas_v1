package com.cenfotec.damas.main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

public class Main {
	private static List<String[]> Row = new ArrayList<String[]>();
	private static String[] ColNames  = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
	static ImageIcon iconWhite = new ImageIcon("Users/macbook/Documents/Damas_v1/blanca-02.png");
	static ImageIcon iconBlack = new ImageIcon("Users/macbook/Documents/Damas_v1/negra-03.png");
	
    public static void main(String[] a) throws MalformedURLException {
        JFrame window = new JFrame();
        window.getContentPane().setBackground(Color.WHITE);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        
        JPanel panel = new JPanel();
        initGame(panel);
        panel.setLayout(new GridLayout(10,10));
        window.add(panel);
        window.setSize(900, 740);
        window.setVisible(true);
    } 
    
    public static void initGame(JPanel panel) throws MalformedURLException {
    	int countCell = 0;
    	for (int e = 0; e <= 9; e++) {
    		String[] Col  = new String[10];
	    	for (int i = 0; i <= 9; i++) {
	    		//System.out.print("");
	    		Col[i] = e + ColNames[i];
	    		String id = Col[i];
	    		if ((countCell % 2 != 0) && (e < 4)) {
	    			id = id + "-Blanca";
	    		} else if ((countCell % 2 != 0) && (e > 5)) {
	    			id = id + "-Negra";
	    		}
	    		countCell++;
	    		JButton CellButton = new JButton(new ImageIcon(new URL("https://www.rgagnon.com/images/gumby.gif")));
	    		CellButton.setOpaque(true);
	    		CellButton.setBorder(new LineBorder(Color.BLACK));
	    		CellButton.setBackground(Color.WHITE);
	    		if (countCell % 2 == 0) {
	    			CellButton.setBackground(Color.LIGHT_GRAY);
	    		}
	    		CellButton.setPreferredSize(new Dimension(70, 70));
	    		CellButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    		CellButton.setActionCommand(id);
	    		CellButton.addActionListener(new ActionListener() {
	    			@Override
					public void actionPerformed(ActionEvent e) {
	    				String action = e.getActionCommand();
	    				clickEvent(action, "");
					}
	    		});
	    		panel.add(CellButton);
	    		//panel.add("ok");
	    	}
	    	countCell++;
	    	Row.add(Col);
    	}
    }
    
    public static void clickEvent(String position1, String position2) {
    	System.out.print("----------");
    	System.out.print(position1);
	}
    
    
}

