package com.cenfotec.damas.main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Main {
	private static List<String[]> Row = new ArrayList<String[]>();
	private static String[] ColNames  = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
	private static String fichaBlanco = "https://res.cloudinary.com/testingapps/image/upload/v1551240020/blanca-02_imhu5t.png";
	private static String fichaNegra = "https://res.cloudinary.com/testingapps/image/upload/v1551240020/negra-03_k4wtj9.png";
	private static JPanel panel = new JPanel();
	private static String ficha = "";
	private static String movimiento = "";
	private static String jugador = "";
	private static String fichaJugador = "";
	
    public static void main(String[] a) throws MalformedURLException {
        JFrame window = new JFrame();
        window.getContentPane().setBackground(Color.WHITE);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        
        initGame();
        panel.setLayout(new GridLayout(10,10));
        window.add(panel);
        window.setSize(720, 700);
        window.setVisible(true);
    }
    
    public static void initGame() throws MalformedURLException {
    	int countCell = 0;
    	for (int e = 0; e <= 9; e++) {
    		String[] Col  = new String[10];
	    	for (int i = 0; i <= 9; i++) {
	    		JButton CellButton = new JButton();
	    		String id = e + ColNames[i];
	    		if ((countCell % 2 != 0) && (e < 4)) {
	    			id = id + "-Jugador01";
	    			CellButton.setIcon(new ImageIcon(new URL(fichaBlanco)));
	    		} else if ((countCell % 2 != 0) && (e > 5)) {
	    			id = id + "-Jugador02";
	    			CellButton.setIcon(new ImageIcon(new URL(fichaNegra)));
	    		}
	    		countCell++;
	    		
	    		CellButton.setOpaque(true);
	    		CellButton.setBorder(new LineBorder(Color.BLACK));
	    		CellButton.setBackground(Color.WHITE);
	    		CellButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    		if (countCell % 2 == 0) {
	    			id = id + "-Negro";
	    			CellButton.setBackground(Color.LIGHT_GRAY);
	    		} else {
	    			id = id + "-Blanco"; 
	    		}
	    		Col[i] = id;
	    		CellButton.setActionCommand(id);
	    		CellButton.addActionListener(new ActionListener() {
	    			@Override
					public void actionPerformed(ActionEvent e) {
	    				try {
							clickEvent(e);
						} catch (MalformedURLException e1) {
							e1.printStackTrace();
						}
					}
	    		});
	    		panel.add(CellButton);
	    	}
	    	countCell++;
	    	Row.add(Col);
    	}
    }
    
    public static void clickEvent(ActionEvent elem) throws MalformedURLException {
    	String action = elem.getActionCommand();
    	if (action.contains("Jugador01") || action.contains("Jugador02")) {
    		ficha = action;
    		fichaJugador = action.contains("Jugador01") ? fichaBlanco : fichaNegra;
    		jugador = ficha.contains("Jugador01") ? "-Jugador01" : "-Jugador02";
    	} else if (action.contains("Negro") && ficha != "") {
    		movimiento = action;
    	}
    	
    	if ((ficha != "" && movimiento != "") && (ficha != movimiento)) {
    		//checkMovimiento();
    		getCell();
    		fichaJugador = "";
    		ficha = "";
    		movimiento = "";
    	}
	}
    
    public static void checkMovimiento() throws MalformedURLException {
    	Component[] comp = panel.getComponents();
        for (int i = 0;i<comp.length;i++) {
            if (comp[i] instanceof JButton) {
            	String action = ((JButton)comp[i]).getActionCommand();
            	
            	if (movimiento != "" && action == movimiento && fichaJugador != "") {
            		((JButton)comp[i]).setIcon(new ImageIcon(new URL(fichaJugador)));
            		((JButton)comp[i]).setActionCommand(action+jugador);
            	}
            	if (ficha != "" && action == ficha) {
            		((JButton)comp[i]).setIcon(null);
            		String newAction = action.replace(jugador, "");
            		((JButton)comp[i]).setActionCommand(newAction);
            	}
            }
        }
	}
    
    
    public static void getCell() throws MalformedURLException {
    	for (int i = 0; i < Row.size(); i++) {
    		String[] stringCell = Row.get(i);
			
    		for (int j = 0; j < stringCell.length; j++) {
				String prevCell = stringCell[j];
				if (prevCell == movimiento) {
					Row.get(i)[j] = movimiento+jugador;
				}
				
				if (prevCell == ficha) {
					Row.get(i)[j] = ficha.replace(jugador, "");
				}
			}
		}
    	checkMovimiento();
	}
    
}

