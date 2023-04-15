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

import com.mazer.gaming.sprites.Enemy;
import com.mazer.gaming.sprites.Player;
import com.mazer.gaming.utils.GameConstants;

public class Board extends JPanel implements GameConstants {
	/**
	 * @param
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage bg ;
	private Player player;
	private Enemy  enemy;
	private Timer timer;
	
	public Board(){
		player = new Player(); //player class constructor
		enemy = new Enemy();
		loadBG();                 //background load
		setFocusable(true);
		bindEvents();//starts recording key's activity
		GameLoop();
		
	}
	public void GameLoop() {
		timer = new Timer(130, new ActionListener() {
			
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
				if(e.getKeyCode()==KeyEvent.VK_A) {
					player.setSpeed(-5);
					player.move();
//					

				}
				else if(e.getKeyCode()==KeyEvent.VK_D) {
					player.setSpeed(5);
					player.move();
//					repaint();
				}
				else if (e.getKeyCode()==KeyEvent.VK_SPACE) {
					player.jump();
					player.setCurrentMove(JUMP);
					
				}
				else if(e.getKeyCode()==KeyEvent.VK_X) {
						player.setCurrentMove(KICK);
				}
				else if(e.getKeyCode()==KeyEvent.VK_Q) {
					player.setCurrentMove(PUNCH);
			}
				else if(e.getKeyCode()==KeyEvent.VK_M) {
					player.setCurrentMove(MPUNCH);
					player.setSpeed(10);
					player.move();
			}
				else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
					enemy.setCurrentMove(WALK);
					enemy.setSpeed(-15);
					enemy.move();
				}
					else if(e.getKeyCode()==KeyEvent.VK_L) {
						enemy.setCurrentMove(KICK);
						enemy.setSpeed(-90);
//						enemy.showKick();
				
			}
					else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
						enemy.setCurrentMove(WALK);
						enemy.setSpeed(15);
						enemy.move();
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
		player.paintSprite(pen);//paints player
		enemy.paintSprite(pen);
	}
	private void paintBG(Graphics pen) {
		pen.drawImage(bg,0,0, GWIDTH, GHEIGHT, null);
	}
}
