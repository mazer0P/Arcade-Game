package com.mazer.gaming.screens;

import javax.swing.JFrame;

import com.mazer.gaming.utils.GameConstants;

public class GameFrame  extends JFrame implements GameConstants{

	GameFrame()
	{
		setTitle(Title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(GWIDTH,GHEIGHT);
		setLocationRelativeTo(null);
		Board board = new Board();
		add(board);
		setVisible(true);
		setResizable(false);
	}
	public static void main(String[] args) {
		
GameFrame frame = new GameFrame();
	}

}
