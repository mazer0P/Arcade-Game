          package com.mazer.gaming.screens;

import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import com.mazer.gaming.sprites.Player;
import com.mazer.gaming.utils.GameConstants;
//import  sun.audio.*;  
import  java.io.*;
public class Board extends JPanel implements GameConstants {
	BufferedImage bg ;
	private Player player;
	private Timer timer;
	
	public Board(){
		player = new Player();    //player class constructor
		loadBG();                 //background load
		setFocusable(true);
		bindEvents();//starts recording key's activity
		GameLoop();
		
	}
	public void GameLoop() {
		timer = new Timer(100, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
				player.fall();
			}
		});
		timer.start();
	}
	public void bindEvents() {
		this.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyTyped(KeyEvent e) {
//				if(e.getKeyCode()==KeyEvent.VK_K){
//				player.setCurrentMove(KICK);
//			}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				player.setSpeed(0);
				player.setCurrentMove(REST);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_LEFT) {
					player.setSpeed(-5);
					player.move();
//					

				}
				else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
					player.setSpeed(5);
					player.move();
//					repaint();
				}
				else if (e.getKeyCode()==KeyEvent.VK_SPACE) {
					player.jump();   
				}
				else if(e.getKeyCode()==KeyEvent.VK_D) {
						player.setCurrentMove(KICK);
				}
				else if(e.getKeyCode()==KeyEvent.VK_A) {
					player.setCurrentMove(PUNCH);
			}
				else if(e.getKeyCode()==KeyEvent.VK_M) {
					player.setCurrentMove(MPUNCH);
					player.setSpeed(10);
					player.move();
			}
				
			}
		});
	}
	private void loadBG() {
		try {
			bg = ImageIO.read(Board.class.getResource("gamebackground.png"));   //loads background image
		
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "OOps");
			System.exit(0);
		}
	}
	
	@Override
	public void paintComponent(Graphics pen) {
		super.paintComponent(pen);  //removes every content on board
		paintBG(pen);               //paints background
		player.paintPlayer(pen);    //paints player
	}
	private void paintBG(Graphics pen) {
		pen.drawImage(bg,0,0, GWIDTH, GHEIGHT, null);
	}
//	public void sound()
//	{
//		InputStream in = new FileInputStream();
//		// Create an AudioStream object from the input stream.
//		AudioStream as = new AudioStream(in);         
//		// Use the static class member "player" from class AudioPlayer to play
//		// clip.
//		AudioPlayer.player.start(as);            
//		// Similarly, to stop the audio.
//		AudioPlayer.player.stop(as);
//	}
}
