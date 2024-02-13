package com.mazer.gaming.screens;

import java.awt.Color;
import java.awt.Font;
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
import com.mazer.gaming.sprites.Power;
import com.mazer.gaming.utils.GameConstants;

import jaco.mp3.player.MP3Player;

public class Board extends JPanel implements GameConstants {
	/**
	 * @param
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage bg ;
	private Player player;
	private Enemy  enemy;
	private Timer timer;
	public MP3Player playback;
	private Power PlayerPower;
	private Power EnemyPower;
	private boolean gameover;

	
	
	private void playSound() {
		playback = new MP3Player(Board.class.getResource("trial.mp3"));
		playback.play();
	}
	private void showDeath() {
		System.out.println("andr toh aya");
		if(EnemyPower.getHealth()<1) {
			System.out.println("assigned");
			enemy.setCurrentMove(DEATH);
		}
	}
	
	public boolean  isCollide() {
	int xDist= Math.abs(player.getX()-enemy.getX());
	int yDist= Math.abs(player.getY()-enemy.getY());
	int maxH= Math.max(player.getH(), enemy.getH());
	int maxW= Math.max(player.getW(), enemy.getW());
	return xDist<=(maxW-40) && yDist<=(maxH-40);
	}
	
	
	private void Collision() {
		if(isCollide()) {
			if(player.isAttacking()) {
				player.setCollide(true);
				enemy.setCurrentMove(DAMAGE);
				EnemyPower.setHealth();
				player.setAttacking(false);
			}
			if(enemy.isAttacking()) {
				enemy.setCollide(true);
				player.setCurrentMove(DAMAGE);
				PlayerPower.setHealth();
				enemy.setAttacking(false);
			}
		}
		else {
			player.setCollide(false);
			player.setSpeed(speed);
			enemy.setCollide(false);
			enemy.setSpeed(speed);
		}
	}
	private boolean isGameOver() {
		if(EnemyPower.getHealth()<0 || PlayerPower.getHealth()<0) {
			enemy.setCurrentMove(DEATH);
			gameover= true;
			}
		return gameover;
	}
	
	private void loadPower() {
		PlayerPower= new Power(30,"Goku");
		EnemyPower= new Power(GWIDTH-550,"King");
	}
	
	private void printFullPower(Graphics g) {
		PlayerPower.printRectangle(g);
		EnemyPower.printRectangle(g);
		}
	private void printGameOver(Graphics pen) {
		if(gameover) {
		pen.setColor(Color.BLACK);
		pen.setFont(new Font("Times",Font.BOLD, 50));
		pen.drawString("Game Over", GWIDTH/2-100,GHEIGHT/2-100);
		}
	}
	public Board(){
		player = new Player(); //player class constructor
		enemy = new Enemy();
		loadBG();                 //background load
		setFocusable(true);
		bindEvents();//starts recording key's activity
		GameLoop();
		playSound();
		loadPower();
	}
	public void GameLoop() {
		timer = new Timer(120, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
				isGameOver();
				player.fall();
				enemy.fall();
				Collision();
				
			}
		});
		timer.start();
	}
	 
	public void bindEvents() {
		this.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
				player.setSpeed(0);
				player.setCurrentMove(REST);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_A) {
					player.setSpeed(-speed);
					player.move();
				}
				else if(e.getKeyCode()==KeyEvent.VK_D) {
					player.setSpeed(speed);
					player.move();
				}
				else if (e.getKeyCode()==KeyEvent.VK_SPACE) {
					player.jump();
					player.setCurrentMove(JUMP);
				}
				else if(e.getKeyCode()==KeyEvent.VK_X) {
						player.setCurrentMove(KICK);
						player.setAttacking(true);
				}
				else if(e.getKeyCode()==KeyEvent.VK_Q) {
					player.setCurrentMove(PUNCH);
					player.setAttacking(true);
			}
				else if(e.getKeyCode()==KeyEvent.VK_M) {
					player.setCurrentMove(MPUNCH);
					player.setSpeed(10);
					player.move();
					player.setAttacking(true);
			}
				else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
					enemy.setCurrentMove(WALK);
					enemy.setSpeed(-15);
					enemy.move();
				}
				else if(e.getKeyCode()==KeyEvent.VK_UP) {
					enemy.jump();
					enemy.setCurrentMove(JUMP);
				}
					else if(e.getKeyCode()==KeyEvent.VK_L) {
						enemy.setCurrentMove(KICK);
						enemy.setSpeed(-90);
						enemy.setAttacking(true);
//						enemy.papahu();
			}
					else if(e.getKeyCode()==KeyEvent.VK_P) {
						enemy.setCurrentMove(PUNCH);
						enemy.setAttacking(true);
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
		super.paintComponent(pen);
		paintBG(pen);               //paints background
		player.paintSprite(pen);//paints player
		enemy.paintSprite(pen);
		printFullPower(pen);
		if(gameover) {
			showDeath();
			System.out.print('d');
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();}
			timer.stop();
			printGameOver(pen);}
			}


	private void paintBG(Graphics pen) {
		pen.drawImage(bg,0,0, GWIDTH, GHEIGHT, null);
	}
	
}
